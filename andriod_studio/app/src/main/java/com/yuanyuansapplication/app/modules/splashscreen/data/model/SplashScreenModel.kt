package com.yuanyuansapplication.app.modules.splashscreen.`data`.model

import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class SplashScreenModel(
  var txtChildrensafty: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_childrensafty)

)
