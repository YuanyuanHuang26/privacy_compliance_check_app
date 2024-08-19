package com.yuanyuansapplication.app.modules.scanningone.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuanyuansapplication.app.modules.scanningone.`data`.model.ScanningoneModel
import org.koin.core.KoinComponent

class ScanningoneVM : ViewModel(), KoinComponent {
  val scanningoneModel: MutableLiveData<ScanningoneModel> = MutableLiveData(ScanningoneModel())

  var navArguments: Bundle? = null
}
