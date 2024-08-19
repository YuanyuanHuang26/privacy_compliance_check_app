package com.yuanyuansapplication.app.modules.home.`data`.model

import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class HomeModel(

  var txtXplora: String? = MyApp.getInstance().resources.getString(R.string.lbl_xplora2)
  ,

  var txtSignout: String? = MyApp.getInstance().resources.getString(R.string.lbl_sign_out)
  ,

  var txtTdt: String? = MyApp.getInstance().resources.getString(R.string.lbl_total_scores)
  ,

  var txtCautionCounter: String? = MyApp.getInstance().resources.getString(R.string.lbl_caution_1)
  ,

  var txtCautionCounter1: String? = MyApp.getInstance().resources.getString(R.string.lbl_caution_2)
  ,

  var txtOverallstatus: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_overall_status)
  ,

  var txtDetailedreport: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_detailed_report)
  ,

  var txtCustomer: String? = MyApp.getInstance().resources.getString(R.string.msg_customer_feedback)
  ,

  var txtPairother: String? =
      MyApp.getInstance().resources.getString(R.string.msg_pair_other_device)

)
