package com.yuanyuansapplication.app.modules.paired.`data`.model

import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class PairedModel(
  var txtPageheading: String? =
      MyApp.getInstance().resources.getString(R.string.msg_looking_for_your2)
  ,

  var txtPaired: String? = MyApp.getInstance().resources.getString(R.string.msg_paired_successfully)

)
