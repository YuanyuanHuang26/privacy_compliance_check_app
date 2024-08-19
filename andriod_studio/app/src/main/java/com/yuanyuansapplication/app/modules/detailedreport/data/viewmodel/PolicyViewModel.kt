package com.yuanyuansapplication.app.modules.detailedreport.data.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.yuanyuansapplication.app.appcomponents.database.AppDatabase
import com.yuanyuansapplication.app.modules.detailedreport.data.model.PrivacyPolicy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class PolicyViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = AppDatabase.getDatabase(application)
    private val policyDao = db.policyDao()
    private val _policies = MediatorLiveData<List<PrivacyPolicy>>()
    val policies: LiveData<List<PrivacyPolicy>> = _policies

    init {
        // Observing changes from the policyDao and updating the _policies LiveData
        _policies.addSource(policyDao.getAllPolicies()) { policies ->
            _policies.postValue(policies)
        }
    }

    fun getPolicyById(id: Int): LiveData<PrivacyPolicy> {
        return policyDao.getPolicyById(id)
    }

    fun preloadData(policies: List<PrivacyPolicy>) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    // Clearing the existing policies and inserting new ones
                    Log.d("PolicyViewModel", "Deleting all policies from the database")
                    policyDao.deleteAll()
                    Log.d("PolicyViewModel", "Inserting policies into the database: $policies")
                    policyDao.insertAll(*policies.toTypedArray())
                }
            } catch (e: Exception) {
                Log.e("PolicyViewModel", "Error preloading policies", e)
            }
        }
    }

    fun checkPolicyCompliance(policyText: String, checklist: List<String>): List<Pair<String, String>> {
        val results = checklist.map { question ->
            val answer = if (policyText.contains(question, ignoreCase = true)) {
                "Yes"
            } else {
                "No"
            }
            question to answer
        }
        return results
    }
}
