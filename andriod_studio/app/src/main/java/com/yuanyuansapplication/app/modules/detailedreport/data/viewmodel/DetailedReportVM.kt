package com.yuanyuansapplication.app.modules.detailedreport.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuanyuansapplication.app.modules.detailedreport.data.model.ComplianceReportModel

class DetailedReportVM : ViewModel() {
    val complianceReportModel: MutableLiveData<ComplianceReportModel> = MutableLiveData(ComplianceReportModel())

    fun setComplianceData(data: List<Pair<String, String>>) {
        complianceReportModel.value?.complianceData = data
        complianceReportModel.notifyObserver()
    }

    fun addManualEntry(question: String, answer: String) {
        val currentData = complianceReportModel.value?.complianceData?.toMutableList() ?: mutableListOf()
        currentData.add(question to answer)
        setComplianceData(currentData)
    }

    // Extension function to notify observers when the data has changed
    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}

//data class ComplianceReportModel(
//    var complianceData: List<Pair<String, String>> = listOf()
//)
data class ComplianceReportModel(
    var txtComplianceReport: String = "Compliance Report",
    var txtOverallstatus: String = "Overall Status",
    var txtDetailedreport: String = "Detailed Report",
    var txtCustomer: String = "Customer Feedback",
    var txtPairother: String = "Pair Other Device",
    var txtSignout: String = "Sign out",
    var complianceData: List<Pair<String, String>> = listOf()
)
