package com.yuanyuansapplication.app.modules.addingwatch.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuanyuansapplication.app.modules.addingwatch.`data`.model.AddingWatchModel
import org.koin.core.KoinComponent

class AddingWatchVM : ViewModel(), KoinComponent {
  val addingWatchModel: MutableLiveData<AddingWatchModel> = MutableLiveData(AddingWatchModel())

  var navArguments: Bundle? = null
}
