package com.yuanyuansapplication.app.modules.signup.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.base.BaseActivity
import com.yuanyuansapplication.app.databinding.ActivitySignUpBinding
import com.yuanyuansapplication.app.modules.addingwatch.ui.AddingWatchActivity
import com.yuanyuansapplication.app.modules.signin.ui.SignInActivity
import com.yuanyuansapplication.app.modules.signup.`data`.viewmodel.SignUpVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
  private val viewModel: SignUpVM by viewModels<SignUpVM>()

  private val REQUEST_CODE_SIGN_IN_ACTIVITY: Int = 291

  private val REQUEST_CODE_ADDING_WATCH_ACTIVITY: Int = 213

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.signUpVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.linearRowsignin.setOnClickListener {
      val destIntent = SignInActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_SIGN_IN_ACTIVITY)
    }
    binding.btnSignUp.setOnClickListener {
      val destIntent = AddingWatchActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_ADDING_WATCH_ACTIVITY)
    }
  }

  companion object {
    const val TAG: String = "SIGN_UP_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SignUpActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
