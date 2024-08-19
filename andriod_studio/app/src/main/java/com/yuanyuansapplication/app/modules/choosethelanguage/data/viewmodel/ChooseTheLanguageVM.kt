package com.yuanyuansapplication.app.modules.choosethelanguage.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuanyuansapplication.app.modules.choosethelanguage.`data`.model.ChooseTheLanguageModel
import org.koin.core.KoinComponent

class ChooseTheLanguageVM : ViewModel(), KoinComponent {
  val chooseTheLanguageModel: MutableLiveData<ChooseTheLanguageModel> =
      MutableLiveData(ChooseTheLanguageModel())

  var navArguments: Bundle? = null
}
