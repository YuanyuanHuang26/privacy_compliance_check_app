package com.yuanyuansapplication.app.modules.scanningone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.base.BaseActivity
import com.yuanyuansapplication.app.databinding.ActivityScanningoneBinding
import com.yuanyuansapplication.app.modules.choose.ui.ChooseActivity
import com.yuanyuansapplication.app.modules.scanningone.`data`.viewmodel.ScanningoneVM
import com.yuanyuansapplication.app.modules.signup.ui.SignUpActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ScanningoneActivity : BaseActivity<ActivityScanningoneBinding>(R.layout.activity_scanningone)
    {
  private val viewModel: ScanningoneVM by viewModels<ScanningoneVM>()

  private val REQUEST_CODE_SIGN_UP_ACTIVITY: Int = 647


  private val REQUEST_CODE_CHOOSE_ACTIVITY: Int = 177


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.scanningoneVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnCancel.setOnClickListener {
      val destIntent = SignUpActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_SIGN_UP_ACTIVITY)
    }
    binding.imageImage.setOnClickListener {
      val destIntent = ChooseActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CHOOSE_ACTIVITY)
    }
  }

  companion object {
    const val TAG: String = "SCANNINGONE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ScanningoneActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
