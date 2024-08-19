import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
import torch
import random
from torch.utils.data import DataLoader, Dataset
from transformers import BertTokenizer, BertForSequenceClassification, Trainer, TrainingArguments
import matplotlib.pyplot as plt
import nlpaug.augmenter.word as naw
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score

# Set seeds for reproducibility
random.seed(16)
np.random.seed(16)
torch.manual_seed(16)
if torch.cuda.is_available():
    torch.cuda.manual_seed_all(16)

data = pd.read_csv('checklist_01.csv')

# Split the data into training, validation, and test sets
train_data, temp_data = train_test_split(data, test_size=0.3, random_state=16)
val_data, test_data = train_test_split(temp_data, test_size=0.5, random_state=16)

# Set seeds for nlpaug augmenters
augmenter = naw.SynonymAug(aug_src='wordnet', aug_p=0.3, aug_min=1, aug_max=10)
random_swap_aug = naw.RandomWordAug(action="swap", aug_p=0.3)
random_deletion_aug = naw.RandomWordAug(action="delete", aug_p=0.3)

# Function to augment text using multiple augmenters
def augment_text(text, num_augmentations=5):
    augmented_texts = []
    for _ in range(num_augmentations):
        augmented_texts.append(augmenter.augment(text))
        augmented_texts.append(random_swap_aug.augment(text))
        augmented_texts.append(random_deletion_aug.augment(text))
    return augmented_texts

# Augment the data
def augment_data(data, num_augmentations=5, special_augmentation_subsets=[]):
    augmented_data = data.copy()
    for index, row in data.iterrows():
        if row['checklist'] in special_augmentation_subsets:
            augmented_questions = augment_text(row['question'], num_augmentations * 2)  # Double augmentation for special subsets
        else:
            augmented_questions = augment_text(row['question'], num_augmentations)
        for aug_question in augmented_questions:
            augmented_data = augmented_data._append({'question': aug_question, 'answer': row['answer'], 'checklist': row['checklist'], 'policy_text': row['policy_text']}, ignore_index=True)
    return augmented_data

class CustomDataset(Dataset):
    def __init__(self, data, tokenizer, max_len):
        self.data = data
        self.tokenizer = tokenizer
        self.max_len = max_len

    def __len__(self):
        return len(self.data)

    def __getitem__(self, idx):
        row = self.data.iloc[idx]
        inputs = self.tokenizer.encode_plus(
            row['question'],
            add_special_tokens=True,
            max_length=self.max_len,
            padding='max_length',
            truncation=True,
            return_attention_mask=True,
            return_tensors='pt',
        )
        return {
            'input_ids': inputs['input_ids'].flatten(),
            'attention_mask': inputs['attention_mask'].flatten(),
            'labels': torch.tensor(row['answer'], dtype=torch.long)
        }


# Save the splits, Load the datasets
train_data.to_csv('train.csv', index=False)
val_data.to_csv('val.csv', index=False)
test_data.to_csv('test.csv', index=False)

train_data = pd.read_csv('train.csv')
val_data = pd.read_csv('val.csv')
test_data = pd.read_csv('test.csv')

# Augment the training data, with extra augmentation for 'gdpr'
special_augmentation_subsets = ['gdpr']
augmented_train_data = augment_data(train_data, num_augmentations=10, special_augmentation_subsets=special_augmentation_subsets)


tokenizer = BertTokenizer.from_pretrained('bert-base-uncased')


MAX_LEN = 128
train_dataset = CustomDataset(augmented_train_data, tokenizer, MAX_LEN)
val_dataset = CustomDataset(val_data, tokenizer, MAX_LEN)
test_dataset = CustomDataset(test_data, tokenizer, MAX_LEN)

# Create data loaders
BATCH_SIZE = 32
train_loader = DataLoader(train_dataset, batch_size=BATCH_SIZE, shuffle=True)
val_loader = DataLoader(val_dataset, batch_size=BATCH_SIZE)
test_loader = DataLoader(test_dataset, batch_size=BATCH_SIZE)


model = BertForSequenceClassification.from_pretrained('bert-base-uncased')


training_args = TrainingArguments(
    output_dir='./results',
    num_train_epochs=10, # Fine-tuned 
    per_device_train_batch_size=BATCH_SIZE,
    per_device_eval_batch_size=BATCH_SIZE,
    warmup_steps=1000,  # Increase warmup steps
    weight_decay=0.01, # Fine-tuned 
    logging_dir='./logs',
    logging_steps=10,
    evaluation_strategy='epoch',
    save_strategy='epoch',
    learning_rate=2e-5,  # Adjust learning rate
    load_best_model_at_end=True,
    metric_for_best_model='eval_loss',
    save_total_limit=1,
    gradient_accumulation_steps=2
)

def compute_metrics(pred):
    labels = pred.label_ids
    preds = np.argmax(pred.predictions, axis=1)
    accuracy = accuracy_score(labels, preds)
    precision = precision_score(labels, preds, average='weighted')
    recall = recall_score(labels, preds, average='weighted')
    f1 = f1_score(labels, preds, average='weighted')
    return {
        'accuracy': accuracy,
        'precision': precision,
        'recall': recall,
        'f1': f1,
    }

trainer = Trainer(
    model=model,
    args=training_args,
    train_dataset=train_dataset,
    eval_dataset=val_dataset,
    tokenizer=tokenizer,
    compute_metrics=compute_metrics
)

trainer.train()

logs = trainer.state.log_history
logs_df = pd.DataFrame(logs)
training_loss = logs_df[logs_df['epoch'].notna() & logs_df['eval_loss'].isna()]['loss']
validation_loss = logs_df[logs_df['eval_loss'].notna()]['eval_loss']
epochs = logs_df[logs_df['eval_loss'].notna()]['epoch']

# Plot the training and validation loss
plt.figure(figsize=(10, 6))
plt.plot(training_loss.values, label='Training Loss')
plt.plot(epochs.values, validation_loss.values, label='Validation Loss', marker='o')
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.title('Training and Validation Loss Over Epochs')
plt.legend()
plt.show()

test_results = trainer.evaluate(eval_dataset=test_dataset)
print(test_results)

predictions = trainer.predict(test_dataset)
preds = np.argmax(predictions.predictions, axis=1)

true_labels = test_data['answer']

overall_accuracy = accuracy_score(true_labels, preds)
overall_precision = precision_score(true_labels, preds, average='weighted')
overall_recall = recall_score(true_labels, preds, average='weighted')
overall_f1 = f1_score(true_labels, preds, average='weighted')

print(f"Overall Accuracy: {overall_accuracy}")
print(f"Overall Precision: {overall_precision}")
print(f"Overall Recall: {overall_recall}")
print(f"Overall F1-Score: {overall_f1}")

def compute_subset_metrics(data, labels, preds, subset_name):
    subset_labels = data[labels]
    subset_preds = preds
    accuracy = accuracy_score(subset_labels, subset_preds)
    precision = precision_score(subset_labels, subset_preds, average='weighted')
    recall = recall_score(subset_labels, subset_preds, average='weighted')
    f1 = f1_score(subset_labels, subset_preds, average='weighted')
    return {
        'accuracy': accuracy,
        'precision': precision,
        'recall': recall,
        'f1': f1,
    }

checklists = test_data['checklist'].unique()
for checklist in checklists:
    subset_data = test_data[test_data['checklist'] == checklist]
    subset_preds = preds[test_data['checklist'] == checklist]
    metrics = compute_subset_metrics(subset_data, 'answer', subset_preds, 'checklist')
    print(f"Metrics for checklist {checklist}: {metrics}")
    
model.save_pretrained('./fine-tuned-model')
tokenizer.save_pretrained('./fine-tuned-model')
