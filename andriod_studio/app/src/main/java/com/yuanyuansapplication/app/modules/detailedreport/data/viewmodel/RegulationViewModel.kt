package com.yuanyuansapplication.app.modules.detailedreport.data.viewmodel
import com.yuanyuansapplication.app.appcomponents.database.AppDatabase
import com.yuanyuansapplication.app.modules.detailedreport.data.model.Regulation
import com.yuanyuansapplication.app.appcomponents.network.RetrofitClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegulationViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = AppDatabase.getDatabase(application)
    private val regulationDao = db.regulationDao()

    private val _regulations = MutableLiveData<List<Regulation>>()
    val regulations: LiveData<List<Regulation>> = _regulations

    private val _complianceResult = MutableLiveData<List<ComplianceResult>>()
    val complianceResult: LiveData<List<ComplianceResult>> get() = _complianceResult

    fun preloadData(regulations: List<Regulation>) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    Log.d("RegulationViewModel", "Deleting all regulations from the database")
                    regulationDao.deleteAll()
                    Log.d("RegulationViewModel", "Inserting regulations: $regulations")
                    regulationDao.insertAll(*regulations.toTypedArray())
                    _regulations.postValue(regulations)
                }
            } catch (e: Exception) {
                Log.e("RegulationViewModel", "Error preloading regulations", e)
            }
        }
    }

    fun checkCompliance(policyText: String, checklist: List<String>) {
        val request = ComplianceRequest(policy_text = policyText, checklist = checklist)
        val call = RetrofitClient.instance.checkCompliance(request)

        call.enqueue(object : Callback<ComplianceResponse> {
            override fun onResponse(call: Call<ComplianceResponse>, response: Response<ComplianceResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _complianceResult.postValue(it.compliance_result)
                    }
                } else {
                    Log.e("RegulationViewModel", "Failed to get compliance result: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ComplianceResponse>, t: Throwable) {
                Log.e("RegulationViewModel", "Error: ${t.message}", t)
            }
        })
    }
}


