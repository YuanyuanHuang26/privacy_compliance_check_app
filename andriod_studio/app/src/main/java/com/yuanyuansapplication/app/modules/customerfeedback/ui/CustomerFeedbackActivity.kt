package com.yuanyuansapplication.app.modules.customerfeedback.ui
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.base.BaseActivity
import com.yuanyuansapplication.app.databinding.ActivityCustomerFeedbackBinding
import com.yuanyuansapplication.app.modules.customerfeedback.data.viewmodel.CustomerFeedbackVM
import com.yuanyuansapplication.app.modules.detailedreport.ui.ComplianceReportActivity
import com.yuanyuansapplication.app.modules.home.ui.HomeActivity
import com.yuanyuansapplication.app.modules.scanningone.ui.ScanningoneActivity
import com.yuanyuansapplication.app.modules.signin.ui.SignInActivity

class CustomerFeedbackActivity :
  BaseActivity<ActivityCustomerFeedbackBinding>(R.layout.activity_customer_feedback) {

  private val viewModel: CustomerFeedbackVM by viewModels()

  private val REQUEST_CODE_SIGN_IN_ACTIVITY: Int = 784
  private val REQUEST_CODE_SCANNINGONE_ACTIVITY: Int = 688
  private val REQUEST_CODE_DETAILED_REPORT_ACTIVITY: Int = 172
  private val REQUEST_CODE_HOME_ACTIVITY: Int = 775

  override fun onInitialized() {
    viewModel.setNavArguments(intent.extras?.getBundle("bundle"))
    binding.customerFeedbackVM = viewModel
  }

  override fun setUpClicks() {
    binding.linearProfile.setOnClickListener {
      val destIntent = SignInActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_SIGN_IN_ACTIVITY)
    }
    binding.txtPairOther.setOnClickListener {
      val destIntent = ScanningoneActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_SCANNINGONE_ACTIVITY)
    }
    binding.txtDetailedReport.setOnClickListener {
      val destIntent = ComplianceReportActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_DETAILED_REPORT_ACTIVITY)
    }
    binding.txtOverallStatus.setOnClickListener {
      val destIntent = HomeActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_HOME_ACTIVITY)
    }
    binding.btnSubmitFeedback.setOnClickListener {
      // Handle the submit feedback action here
    }
  }

  companion object {
    const val TAG: String = "CUSTOMER_FEEDBACK_ACTIVITY"

    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CustomerFeedbackActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
