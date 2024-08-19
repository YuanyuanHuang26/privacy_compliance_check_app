package com.yuanyuansapplication.app.modules.customerfeedback.data.model

data class CustomerFeedbackModel(
  var txtCustomerFeedback: String = "Customer Feedback",
  var txtRateExperience: String = "Rate your experience",
  var rating: Float = 0f,
  var txtProvideFeedback: String = "Provide your feedback",
  var hintFeedback: String = "Please provide your feedback here",
  var txtSubmitFeedback: String = "Submit Feedback",
  var txtOverallStatus: String = "Overall Status",
  var txtDetailedReport: String = "Detailed Report",
  var txtCustomerFeedbackLabel: String = "Customer Feedback",
  var txtPairOther: String = "Pair Other",
  var txtSignout: String = "Sign out"
)
