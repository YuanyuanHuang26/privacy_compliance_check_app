package com.yuanyuansapplication.app.modules.customerfeedback.data.viewmodel
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.yuanyuansapplication.app.modules.customerfeedback.data.model.CustomerFeedbackModel

class CustomerFeedbackVM(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val customerFeedbackModel: MutableLiveData<CustomerFeedbackModel> by lazy {
        MutableLiveData<CustomerFeedbackModel>().also {
            it.value = CustomerFeedbackModel()
        }
    }

    fun setNavArguments(bundle: Bundle?) {
        savedStateHandle.set("bundle", bundle)
    }

    fun getNavArguments(): Bundle? {
        return savedStateHandle.get<Bundle>("bundle")
    }
}
