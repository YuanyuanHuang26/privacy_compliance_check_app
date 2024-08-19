package com.yuanyuansapplication.app.modules.choose.`data`.model

import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class ChooseModel(

  var txtPageheading: String? =
      MyApp.getInstance().resources.getString(R.string.msg_looking_for_your2)
  ,

  var txtPageheadingOne: String? =
      MyApp.getInstance().resources.getString(R.string.msg_chose_your_smartwatch)
  ,

  var txtXplora: String? = MyApp.getInstance().resources.getString(R.string.lbl_xplora)
  ,

  var txtGarett: String? = MyApp.getInstance().resources.getString(R.string.lbl_garett)

)
