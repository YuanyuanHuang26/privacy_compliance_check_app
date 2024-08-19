package com.yuanyuansapplication.app.modules.choose.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.base.BaseActivity
import com.yuanyuansapplication.app.databinding.ActivityChooseBinding
import com.yuanyuansapplication.app.modules.choose.`data`.viewmodel.ChooseVM
import com.yuanyuansapplication.app.modules.choosethelanguage.ui.ChooseTheLanguageActivity
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ChooseActivity : BaseActivity<ActivityChooseBinding>(R.layout.activity_choose) {
  private val viewModel: ChooseVM by viewModels<ChooseVM>()

  private val REQUEST_CODE_CHOOSE_THE_LANGUAGE_ACTIVITY: Int = 392

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.chooseVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.linearColumnxplora.setOnClickListener {
      val destIntent = ChooseTheLanguageActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_CHOOSE_THE_LANGUAGE_ACTIVITY)
    }
  }

  companion object {
    const val TAG: String = "CHOOSE_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ChooseActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
