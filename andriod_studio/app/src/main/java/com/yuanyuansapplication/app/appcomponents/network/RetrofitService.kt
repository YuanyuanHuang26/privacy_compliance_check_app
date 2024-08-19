
package com.yuanyuansapplication.app.appcomponents.network
import com.yuanyuansapplication.app.modules.detailedreport.data.viewmodel.ComplianceRequest
import com.yuanyuansapplication.app.modules.detailedreport.data.viewmodel.ComplianceResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {
    @POST("check_compliance")
    fun checkCompliance(@Body request: ComplianceRequest): Call<ComplianceResponse>
}


