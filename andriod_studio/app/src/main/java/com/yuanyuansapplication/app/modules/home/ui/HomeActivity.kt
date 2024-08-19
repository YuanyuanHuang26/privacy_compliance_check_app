package com.yuanyuansapplication.app.modules.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.base.BaseActivity
import com.yuanyuansapplication.app.databinding.ActivityHomeBinding
import com.yuanyuansapplication.app.modules.choose.ui.ChooseActivity
import com.yuanyuansapplication.app.modules.customerfeedback.ui.CustomerFeedbackActivity
import com.yuanyuansapplication.app.modules.detailedreport.ui.ComplianceReportActivity
import com.yuanyuansapplication.app.modules.home.`data`.viewmodel.HomeVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
  private val viewModel: HomeVM by viewModels<HomeVM>()

  private val REQUEST_CODE_SCANNINGONE_ACTIVITY: Int = 179

  private val REQUEST_CODE_SIGN_IN_ACTIVITY: Int = 945

  private val REQUEST_CODE_CUSTOMER_FEEDBACK_ACTIVITY: Int = 201

  private val REQUEST_CODE_DETAILED_REPORT_ACTIVITY: Int = 338

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.homeVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.txtCustomer.setOnClickListener {
      val destIntent = CustomerFeedbackActivity.getIntent(this, null)
      destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
      startActivity(destIntent)
    }
    binding.txtDetailedreport.setOnClickListener {
      val destIntent = ComplianceReportActivity.getIntent(this, null)
      destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
      startActivity(destIntent)
    }
    binding.txtPairother.setOnClickListener {
      val destIntent = ChooseActivity.getIntent(this, null)
      destIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
      startActivity(destIntent)
    }
  }

  companion object {
    const val TAG: String = "HOME_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, HomeActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
