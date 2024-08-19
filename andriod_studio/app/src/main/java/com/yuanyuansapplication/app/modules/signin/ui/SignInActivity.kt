package com.yuanyuansapplication.app.modules.signin.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.base.BaseActivity
import com.yuanyuansapplication.app.databinding.ActivitySignInBinding
import com.yuanyuansapplication.app.modules.home.ui.HomeActivity
import com.yuanyuansapplication.app.modules.signin.`data`.viewmodel.SignInVM
import com.yuanyuansapplication.app.modules.signup.ui.SignUpActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
  private val viewModel: SignInVM by viewModels<SignInVM>()

  private val REQUEST_CODE_SIGN_UP_ACTIVITY: Int = 434


  private val REQUEST_CODE_HOME_ACTIVITY: Int = 238


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.signInVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.linearSignupbutton.setOnClickListener {
      val destIntent = SignUpActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_SIGN_UP_ACTIVITY)
    }
    binding.btnSignIn.setOnClickListener {
      val destIntent = HomeActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_HOME_ACTIVITY)
    }
  }

  companion object {
    const val TAG: String = "SIGN_IN_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, SignInActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
