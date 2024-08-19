package com.yuanyuansapplication.app.modules.scanningone.`data`.model

import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class ScanningoneModel(

  var txtPageheading: String? =
      MyApp.getInstance().resources.getString(R.string.msg_looking_for_your)

)
