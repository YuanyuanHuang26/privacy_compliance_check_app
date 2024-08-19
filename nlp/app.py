#work with more than 11 questions

# from flask import Flask, request, jsonify
# from transformers import BertTokenizer, BertForSequenceClassification
# import torch
# import logging
# import json
# import os

# app = Flask(__name__)
# logging.basicConfig(level=logging.DEBUG)

# # Load fine-tuned BERT model and tokenizer
# app.logger.info("Loading fine-tuned BERT model and tokenizer")
# tokenizer = BertTokenizer.from_pretrained('./fine-tuned-model')
# model = BertForSequenceClassification.from_pretrained('./fine-tuned-model')
# app.logger.info("Model and tokenizer loaded successfully")

# @app.route('/check_compliance', methods=['POST'])
# def check_compliance():
#     try:
#         data = request.get_json()
#         app.logger.info(f"Received data: {data}")
#         policy_text = data.get('policy_text')
#         checklist = data.get('checklist')

#         if not policy_text or not checklist:
#             app.logger.error("Missing policy_text or checklist in the request")
#             return jsonify({"error": "Missing policy_text or checklist"}), 400
        
#         compliance_results = []
#         for question in checklist:
#             # Concatenate the policy text and question into a single input for the model
#             combined_input = f"{policy_text} {tokenizer.sep_token} {question}"
#             inputs = tokenizer(combined_input, return_tensors="pt", max_length=512, truncation=True, padding="max_length")
#             app.logger.debug(f"Tokenized input: {inputs}")

#             # Get predictions from the model
#             outputs = model(**inputs)
#             prediction = torch.argmax(outputs.logits, dim=1).item()
#             app.logger.debug(f"Model output logits: {outputs.logits}")
#             app.logger.debug(f"Predicted label: {prediction}")

#             # Interpret the prediction
#             result = "yes" if prediction == 1 else ("not mentioned" if prediction == -1 else "no")
#             compliance_results.append({"question": question, "answer": result})

#         result = {
#             "compliance_result": compliance_results
#         }

#         # Write the result to result.json
#         try:
#             file_path = os.path.join(os.getcwd(), 'result1.json')
#             with open(file_path, 'w') as json_file:
#                 json.dump(result, json_file)
#             app.logger.info(f"Results written to {file_path}")
#         except Exception as e:
#             app.logger.error(f"Failed to write result.json: {e}", exc_info=True)

#         return jsonify(result)

#     except Exception as e:
#         app.logger.error(f"Error during compliance check: {e}", exc_info=True)
#         return jsonify({"error": str(e)}), 500

# if __name__ == '__main__':
#     app.run(host='0.0.0.0', port=5001)

###########################latest one 

# from flask import Flask, request, jsonify
# from transformers import BertTokenizer, BertForSequenceClassification
# import torch
# import logging

# app = Flask(__name__)
# logging.basicConfig(level=logging.DEBUG)

# # Load fine-tuned BERT model and tokenizer
# tokenizer = BertTokenizer.from_pretrained('./fine-tuned-model')
# model = BertForSequenceClassification.from_pretrained('./fine-tuned-model')

# @app.route('/check_compliance', methods=['POST'])
# def check_compliance():
#     try:
#         data = request.get_json()
#         app.logger.info(f"Received data: {data}")
#         policy_text = data.get('policy_text')
#         checklist = data.get('checklist')

#         if not policy_text or not checklist:
#             app.logger.error("Missing policy_text or checklist in the request")
#             return jsonify({"error": "Missing policy_text or checklist"}), 400
        
#         compliance_results = []
#         for question in checklist:
#             # Concatenate the policy text and question into a single input for the model
#             combined_input = f"{policy_text} {tokenizer.sep_token} {question}"
#             inputs = tokenizer(combined_input, return_tensors="pt", max_length=512, truncation=True, padding="max_length")

#             # Get predictions from the model
#             outputs = model(**inputs)
#             prediction = torch.argmax(outputs.logits, dim=1).item()

#             # Interpret the prediction
#             result = "yes" if prediction == 1 else ("not mentioned" if prediction == 0 else "no")
#             compliance_results.append({"question": question, "answer": result})

#         result = {
#             "compliance_result": compliance_results
#         }
        
#         return jsonify(result)

#     except Exception as e:
#         app.logger.error(f"Error during compliance check: {e}", exc_info=True)
#         return jsonify({"error": str(e)}), 500

# if __name__ == '__main__':
#     app.run(host='0.0.0.0', port=5001)


# from flask import Flask, request, jsonify
# from transformers import BertTokenizer, BertForSequenceClassification
# import torch
# import logging

# app = Flask(__name__)
# logging.basicConfig(level=logging.DEBUG)

# # Load fine-tuned BERT model and tokenizer
# tokenizer = BertTokenizer.from_pretrained('./fine-tuned-model')
# model = BertForSequenceClassification.from_pretrained('./fine-tuned-model')

# @app.route('/check_compliance', methods=['POST'])
# def check_compliance():
#     try:
#         data = request.get_json()
#         app.logger.info(f"Received data: {data}")
#         policy_text = data.get('policy_text')
#         checklist = data.get('checklist')
        
#         compliance_results = []
#         for question in checklist:
            
#             combined_input = f"{policy_text} {tokenizer.sep_token} {question}"
#             inputs = tokenizer(combined_input, return_tensors="pt", max_length=512, truncation=True, padding="max_length")

            
#             outputs = model(**inputs)
#             prediction = torch.argmax(outputs.logits, dim=1).item()

            
#             result = 1 if prediction == 1 else 0
#             compliance_results.append({"question": question, "answer": result})

#         result = {
#             "compliance_result": compliance_results
#         }
        
#         return jsonify(result)

#     except Exception as e:
#         app.logger.error(f"Error during compliance check: {e}", exc_info=True)
#         return jsonify({"error": str(e)}), 500

# if __name__ == '__main__':
#     app.run(host='0.0.0.0', port=5001)


# # Concatenate the policy text and question into a single input for the model
# # Get predictions from the model
# # Interpret the prediction

from flask import Flask, request, jsonify
from transformers import BertTokenizer, BertForSequenceClassification
import torch
import logging

app = Flask(__name__)
logging.basicConfig(level=logging.DEBUG)

# Load fine-tuned BERT model and tokenizer
tokenizer = BertTokenizer.from_pretrained('./fine-tuned-model')
model = BertForSequenceClassification.from_pretrained('./fine-tuned-model')

@app.route('/check_compliance', methods=['POST'])
def check_compliance():
    try:
        data = request.get_json()
        app.logger.info(f"Received data: {data}")
        policy_text = data.get('policy_text')
        checklist = data.get('checklist')
        
        compliance_results = []
        for question in checklist:
            # Concatenate the policy text and question into a single input for the model
            combined_input = f"{policy_text} {tokenizer.sep_token} {question}"
            inputs = tokenizer(combined_input, return_tensors="pt", max_length=512, truncation=True, padding="max_length")

            # Get predictions from the model
            outputs = model(**inputs)
            prediction = torch.argmax(outputs.logits, dim=1).item()

            # Interpret the prediction
            answer = "Yes" if prediction == 1 else "No"
            compliance_results.append({"question": question, "answer": answer})

        result = {
            "compliance_result": compliance_results
        }
        
        return jsonify(result)

    except Exception as e:
        app.logger.error(f"Error during compliance check: {e}", exc_info=True)
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5001)
