package com.yuanyuansapplication.app.modules.choosethelanguage.`data`.model

import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class ChooseTheLanguageModel(

  var txtChoosethearea: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_choose_the_area)
  ,

  var txtEu: String? = MyApp.getInstance().resources.getString(R.string.lbl_eu)
  ,

  var txtUsa: String? = MyApp.getInstance().resources.getString(R.string.lbl_usa)
  ,

  var txtChina: String? = MyApp.getInstance().resources.getString(R.string.lbl_china)
  ,

  var txtSwitzerland: String? = MyApp.getInstance().resources.getString(R.string.lbl_switzerland)
  ,

  var txtSouthafrica: String? = MyApp.getInstance().resources.getString(R.string.lbl_south_africa)

)
