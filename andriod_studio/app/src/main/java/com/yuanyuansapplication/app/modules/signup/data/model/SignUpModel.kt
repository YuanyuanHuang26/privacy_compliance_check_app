package com.yuanyuansapplication.app.modules.signup.`data`.model

import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class SignUpModel(
  var txtPageheading: String? = MyApp.getInstance().resources.getString(R.string.lbl_sign_up2)
  ,
  var txtEmail: String? = MyApp.getInstance().resources.getString(R.string.lbl_e_mail)
  ,
  var txtPhonenumber: String? = MyApp.getInstance().resources.getString(R.string.lbl_phone_number)
  ,
  var txtPassword: String? = MyApp.getInstance().resources.getString(R.string.lbl_password)
  ,
  var txtConfirmation: String? =
      MyApp.getInstance().resources.getString(R.string.msg_already_have_an)
  ,
  var txtSignin: String? = MyApp.getInstance().resources.getString(R.string.lbl_sign_in2)
  ,
  var etEmailValue: String? = null
  ,
  var etPhoneNumberValue: String? = null
  ,
  var etPasswordValue: String? = null
)
