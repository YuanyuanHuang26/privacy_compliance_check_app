package com.yuanyuansapplication.app.modules.addingwatch.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.base.BaseActivity
import com.yuanyuansapplication.app.databinding.ActivityAddingWatchBinding
import com.yuanyuansapplication.app.modules.addingwatch.`data`.viewmodel.AddingWatchVM
import com.yuanyuansapplication.app.modules.scanningone.ui.ScanningoneActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class AddingWatchActivity : BaseActivity<ActivityAddingWatchBinding>(R.layout.activity_adding_watch)
    {
  private val viewModel: AddingWatchVM by viewModels<AddingWatchVM>()

  private val REQUEST_CODE_SCANNINGONE_ACTIVITY: Int = 687


  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.addingWatchVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnScan.setOnClickListener {
      val destIntent = ScanningoneActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_SCANNINGONE_ACTIVITY)
    }
  }

  companion object {
    const val TAG: String = "ADDING_WATCH_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, AddingWatchActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
