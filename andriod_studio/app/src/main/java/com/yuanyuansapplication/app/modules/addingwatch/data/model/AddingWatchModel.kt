package com.yuanyuansapplication.app.modules.addingwatch.`data`.model

import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class AddingWatchModel(

  var txtPageheading: String? =
      MyApp.getInstance().resources.getString(R.string.msg_lets_connect_your)

)
