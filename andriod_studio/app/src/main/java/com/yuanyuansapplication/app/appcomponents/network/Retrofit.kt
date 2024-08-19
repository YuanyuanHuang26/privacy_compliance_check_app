


package com.yuanyuansapplication.app.appcomponents.network
import com.yuanyuansapplication.app.modules.detailedreport.data.viewmodel.ComplianceResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class ComplianceRequest(
    val policy_text: String,
    val checklist: List<String>
)

data class ComplianceResponse(
    val compliance_result: List<ComplianceResult>
)

interface ComplianceApiService {
    @POST("/check_compliance")
    fun checkCompliance(@Body request: ComplianceRequest): Call<ComplianceResponse>
}
