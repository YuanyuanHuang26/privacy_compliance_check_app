package com.yuanyuansapplication.app.modules.detailedreport.data.model

data class ComplianceReportModel(
    var txtComplianceReport: String = "Compliance Report",
    var txtSignout: String = "Sign out",
    var txtOverallstatus: String = "Overall Status",
    var txtDetailedreport: String = "Detailed Report",
    var txtCustomer: String = "Customer Feedback",
    var txtPairother: String = "Pair Other",
    var complianceData: List<Pair<String, String>> = listOf()
)
