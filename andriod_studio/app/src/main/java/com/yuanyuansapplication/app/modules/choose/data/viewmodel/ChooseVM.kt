package com.yuanyuansapplication.app.modules.choose.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuanyuansapplication.app.modules.choose.`data`.model.ChooseModel
import org.koin.core.KoinComponent

class ChooseVM : ViewModel(), KoinComponent {
  val chooseModel: MutableLiveData<ChooseModel> = MutableLiveData(ChooseModel())

  var navArguments: Bundle? = null
}
