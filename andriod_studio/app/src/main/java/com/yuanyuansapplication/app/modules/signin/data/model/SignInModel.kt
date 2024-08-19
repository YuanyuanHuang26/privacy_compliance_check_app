package com.yuanyuansapplication.app.modules.signin.`data`.model

import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.di.MyApp
import kotlin.String

data class SignInModel(

  var txtPageheading: String? = MyApp.getInstance().resources.getString(R.string.lbl_sign_in)
  ,

  var txtEmail: String? = MyApp.getInstance().resources.getString(R.string.lbl_e_mail)
  ,

  var txtPassword: String? = MyApp.getInstance().resources.getString(R.string.lbl_password)
  ,

  var txtForgotpassword: String? =
      MyApp.getInstance().resources.getString(R.string.msg_forgot_password)
  ,

  var txtConfirmation: String? =
      MyApp.getInstance().resources.getString(R.string.msg_don_t_have_an_account)
  ,

  var txtSignup: String? = MyApp.getInstance().resources.getString(R.string.lbl_sign_up)
  ,

  var etEmailValue: String? = null,

  var etPasswordValue: String? = null
)
