package com.yuanyuansapplication.app.modules.paired.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.base.BaseActivity
import com.yuanyuansapplication.app.databinding.ActivityPairedBinding
import com.yuanyuansapplication.app.modules.home.ui.HomeActivity
import com.yuanyuansapplication.app.modules.paired.`data`.viewmodel.PairedVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class PairedActivity : BaseActivity<ActivityPairedBinding>(R.layout.activity_paired) {
  private val viewModel: PairedVM by viewModels<PairedVM>()

  private val REQUEST_CODE_HOME_ACTIVITY: Int = 351


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.pairedVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageEffectOne.setOnClickListener {
      val destIntent = HomeActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_HOME_ACTIVITY)
    }
  }

  companion object {
    const val TAG: String = "PAIRED_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, PairedActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
