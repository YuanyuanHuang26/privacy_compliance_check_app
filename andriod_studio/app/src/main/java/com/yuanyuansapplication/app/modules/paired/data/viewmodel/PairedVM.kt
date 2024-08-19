package com.yuanyuansapplication.app.modules.paired.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuanyuansapplication.app.modules.paired.`data`.model.PairedModel
import org.koin.core.KoinComponent

class PairedVM : ViewModel(), KoinComponent {
  val pairedModel: MutableLiveData<PairedModel> = MutableLiveData(PairedModel())

  var navArguments: Bundle? = null
}
