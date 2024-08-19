package com.yuanyuansapplication.app.modules.detailedreport.ui
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.utils.JsonUtils
import com.yuanyuansapplication.app.databinding.ActivityDetailedReportBinding
import com.yuanyuansapplication.app.modules.choose.ui.ChooseActivity
import com.yuanyuansapplication.app.modules.customerfeedback.ui.CustomerFeedbackActivity
import com.yuanyuansapplication.app.modules.detailedreport.data.viewmodel.ComplianceResult
import com.yuanyuansapplication.app.modules.detailedreport.data.viewmodel.DetailedReportVM
import com.yuanyuansapplication.app.modules.detailedreport.data.viewmodel.PolicyViewModel
import com.yuanyuansapplication.app.modules.detailedreport.data.viewmodel.RegulationViewModel
import com.yuanyuansapplication.app.modules.home.ui.HomeActivity

class ComplianceReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailedReportBinding
    private val detailedReportVM: DetailedReportVM by viewModels()
    private val policyViewModel: PolicyViewModel by viewModels()
    private val regulationViewModel: RegulationViewModel by viewModels()
    private lateinit var container: ConstraintLayout
//    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        const val TAG: String = "COMPLIANCE_REPORT_ACTIVITY"

        fun getIntent(context: Context, bundle: Bundle?): Intent {
            val destIntent = Intent(context, ComplianceReportActivity::class.java)
            destIntent.putExtra("bundle", bundle)
            return destIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        container = binding.root.findViewById(R.id.container)
        regulationViewModel.preloadData(JsonUtils.loadRegulations(this) ?: emptyList())

        policyViewModel.preloadData(JsonUtils.loadPrivacyPolicy(this) ?: emptyList())

        regulationViewModel.regulations.observe(this, Observer { regulations ->
            regulations?.let {
                if (regulations.isNotEmpty()) {
                    val checklist = regulations[0].questions.map { it.questionText }

                    policyViewModel.policies.observe(this, Observer { policies ->
                        policies?.let {
                            if (policies.isNotEmpty()) {
                                val policyText = policies[0].details.joinToString(separator = "\n") { detail -> detail.text }
                                regulationViewModel.checkCompliance(policyText, checklist)
                            }
                        }
                    })
                }
            }
        })


        regulationViewModel.complianceResult.observe(this, Observer { result ->
            result?.let {
                container.removeAllViews() // Clear existing views

                var previousViewId = addManualQuestions(-1) // Add manual questions first
                previousViewId = addQuestionsWithCategories(result, previousViewId)
                Log.d(TAG, "Compliance result: $result")
            } ?: Log.e(TAG, "Compliance result is null")
        })

        setUpClicks()
    }

    private fun addManualQuestions(previousViewId: Int): Int {
        var lastViewId = previousViewId

        val manualQuestions = listOf(
            "Do you ensure consent choices are displayed equally without using nudging or dark patterns?" to "Yes",
            "Is there a clear and conspicuous 'Do Not Sell Or Share My Personal Information' link easily accessible on your website homepage?" to "No",
        )

        manualQuestions.forEach { (question, answer) ->
            lastViewId = addQuestionAnswerPair(lastViewId, question, answer)
        }

        return lastViewId
    }

    private fun addQuestionsWithCategories(result: List<ComplianceResult>, previousViewId: Int): Int {
        var lastViewId = previousViewId

        val categories = listOf(
            "User Consent and Data Usage" to listOf(
                "Have you implemented a privacy notice with information about data use, consumers’ rights, and user options like consent opt-out?",
                "Have you informed users about their right to know what personal data is collected and how it is used or shared?",
                "Have you informed users about their right to delete personal data that has been collected about them, with exceptions?",
                "Have you informed users about their right to data portability, providing a copy of personal data in a portable and readily usable format?",
                "Have you informed users about their right to non-discrimination for exercising privacy rights?",
                "Do you re-offer opt-in consent to consumers who have opted out, presenting the option to opt-in again after 12 months?",
                "Have you informed users about their right to opt-out of the sale or sharing of their personal data?"
            ),
            "Data Management and Rights" to listOf(
                "Have you set up a system to enable the submission of Data Subject Access Requests (DSARs) allowing consumers to verify their identity and residency?",
                "Have you set up a system to enable submissions for verification requests, explaining why a request could not be verified and allowing consumers to rectify?",
                "Do you keep track of all Data Subject Access Requests (DSARs) and your business responses?"
            ),
            "Privacy Policies and Practices" to listOf(
                "Have you created a comprehensive Privacy Policy informing consumers at or before the point of data collection about how data is collected, retained, and used?",
                "Does your Privacy Policy inform consumers about the categories of personal data collected and the purposes for which it is collected?",
                "Does your Privacy Policy disclose whether collected data is sold to or shared with third parties and identify those third parties?",
                "Does your Privacy Policy inform website visitors of their privacy rights and how to exercise them?"
            ),
            "Children’s Safety" to listOf(
                "Have you obtained consent from a parent or guardian before collecting personal data from children?",
                "Do you obtain explicit consent from the data subject before processing sensitive personal data or personal data from minors between the ages of 13 and 16?",
                "Do you obtain consent from a parent or legal guardian for the collection of personal data from children aged 13 or younger?"
            ),
            "Advertising and Tracking" to listOf(
                "Do you provide a 'Limit The Use of My Sensitive Personal Information' link to enable opt-out?"
            )
        )

        categories.forEach { (category, questions) ->
            lastViewId = addCategoryHeader(lastViewId, category)
            questions.forEach { questionText ->
                val compliance = result.find { it.question == questionText }
                if (compliance != null) {
                    lastViewId = addQuestionAnswerPair(lastViewId, compliance.question, compliance.answer)
                } else {
                    lastViewId = addQuestionAnswerPair(lastViewId, questionText, "No")
                }
            }
        }

        return lastViewId
    }

    private fun addCategoryHeader(previousViewId: Int, category: String): Int {
        val categoryTextView = TextView(this).apply {
            id = View.generateViewId()
            text = category
            layoutParams = ConstraintLayout.LayoutParams(0, WRAP_CONTENT)
            setTextColor(Color.GRAY)
            setTextAppearance(android.R.style.TextAppearance_Medium)
        }

        container.addView(categoryTextView)

        val constraintSet = ConstraintSet()
        constraintSet.clone(container)

        if (previousViewId == -1) {
            constraintSet.connect(categoryTextView.id, ConstraintSet.TOP, container.id, ConstraintSet.TOP, 16)
        } else {
            constraintSet.connect(categoryTextView.id, ConstraintSet.TOP, previousViewId, ConstraintSet.BOTTOM, 16)
        }

        constraintSet.connect(categoryTextView.id, ConstraintSet.START, container.id, ConstraintSet.START, 16)
        constraintSet.connect(categoryTextView.id, ConstraintSet.END, container.id, ConstraintSet.END, 16)

        constraintSet.applyTo(container)

        return categoryTextView.id
    }

    private fun addQuestionAnswerPair(previousViewId: Int, question: String, answer: String): Int {
        val questionTextView = TextView(this).apply {
            id = View.generateViewId()
            text = question
            layoutParams = ConstraintLayout.LayoutParams(0, WRAP_CONTENT)
            setTextColor(Color.BLACK)
        }

        val answerTextView = TextView(this).apply {
            id = View.generateViewId()
            text = answer
            layoutParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

            when (answer.toLowerCase()) {
                "yes" -> setTextColor(getColor(R.color.green))
                "no" -> setTextColor(getColor(R.color.red))
                else -> setTextColor(Color.BLACK)
            }
        }

        container.addView(questionTextView)
        container.addView(answerTextView)

        val constraintSet = ConstraintSet()
        constraintSet.clone(container)

            if (previousViewId == -1) {
                constraintSet.connect(questionTextView.id, ConstraintSet.TOP, container.id, ConstraintSet.TOP, 16)
            } else {
                constraintSet.connect(questionTextView.id, ConstraintSet.TOP, previousViewId, ConstraintSet.BOTTOM, 16)
            }

            constraintSet.connect(answerTextView.id, ConstraintSet.TOP, questionTextView.id, ConstraintSet.TOP)
            constraintSet.connect(answerTextView.id, ConstraintSet.BOTTOM, questionTextView.id, ConstraintSet.BOTTOM)

            constraintSet.connect(questionTextView.id, ConstraintSet.START, container.id, ConstraintSet.START, 16)
            constraintSet.connect(answerTextView.id, ConstraintSet.END, container.id, ConstraintSet.END, 16)

            constraintSet.createHorizontalChainRtl(
                container.id, ConstraintSet.START,
                container.id, ConstraintSet.END,
                intArrayOf(questionTextView.id, answerTextView.id),
                null,
                ConstraintSet.CHAIN_SPREAD
            )

            constraintSet.applyTo(container)

            return questionTextView.id
        }

        private fun setUpClicks() {
            binding.txtCustomer.setOnClickListener {
                val destIntent = CustomerFeedbackActivity.getIntent(this, null)
                destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(destIntent)
            }

            binding.txtDetailedreport.setOnClickListener {
                val destIntent = ComplianceReportActivity.getIntent(this, null)
                destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(destIntent)
            }

            binding.txtPairother.setOnClickListener {
                val destIntent = ChooseActivity.getIntent(this, null)
                destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(destIntent)
            }

            binding.txtOverallstatus.setOnClickListener {
                val destIntent = HomeActivity.getIntent(this, null)
                destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(destIntent)
            }
        }
    }
