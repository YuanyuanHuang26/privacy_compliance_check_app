//package com.yuanyuansapplication.app.modules.choosethelanguage.ui
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.viewModels
//import com.yuanyuansapplication.app.R
//import com.yuanyuansapplication.app.appcomponents.base.BaseActivity
//import com.yuanyuansapplication.app.databinding.ActivityChooseTheLanguageBinding
//import com.yuanyuansapplication.app.modules.choosethelanguage.`data`.viewmodel.ChooseTheLanguageVM
//import com.yuanyuansapplication.app.modules.paired.ui.PairedActivity
//import kotlin.Int
//import kotlin.String
//import kotlin.Unit
//
//class ChooseTheLanguageActivity :
//    BaseActivity<ActivityChooseTheLanguageBinding>(R.layout.activity_choose_the_language) {
//  private val viewModel: ChooseTheLanguageVM by viewModels<ChooseTheLanguageVM>()
//
//  private val REQUEST_CODE_PAIRED_ACTIVITY: Int = 791
//
//  override fun onInitialized(): Unit {
//    viewModel.navArguments = intent.extras?.getBundle("bundle")
//    binding.chooseTheLanguageVM = viewModel
//  }
//
//  override fun setUpClicks(): Unit {
//    binding.btnContinue.setOnClickListener {
//      val destIntent = PairedActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_PAIRED_ACTIVITY)
//    }
//  }
//
//  companion object {
//    const val TAG: String = "CHOOSE_THE_LANGUAGE_ACTIVITY"
//
//
//    fun getIntent(context: Context, bundle: Bundle?): Intent {
//      val destIntent = Intent(context, ChooseTheLanguageActivity::class.java)
//      destIntent.putExtra("bundle", bundle)
//      return destIntent
//    }
//  }
//}

//package com.yuanyuansapplication.app.modules.choosethelanguage.ui
//
//import android.Manifest
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.location.Location
//import android.location.LocationManager
//import android.os.Bundle
//import androidx.activity.viewModels
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.yuanyuansapplication.app.R
//import com.yuanyuansapplication.app.appcomponents.base.BaseActivity
//import com.yuanyuansapplication.app.databinding.ActivityChooseTheLanguageBinding
//import com.yuanyuansapplication.app.modules.choosethelanguage.data.viewmodel.ChooseTheLanguageVM
//import com.yuanyuansapplication.app.modules.paired.ui.PairedActivity
//
//class ChooseTheLanguageActivity :
//  BaseActivity<ActivityChooseTheLanguageBinding>(R.layout.activity_choose_the_language) {
//
//  private val viewModel: ChooseTheLanguageVM by viewModels()
//
//  private val REQUEST_CODE_PAIRED_ACTIVITY: Int = 791
//  private val REQUEST_LOCATION_PERMISSION: Int = 100
//
//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    binding = ActivityChooseTheLanguageBinding.inflate(layoutInflater)
//    setContentView(binding.root)
//
//    // Request location permission every time the app runs
//    requestLocationPermissions()
//
//    setUpClicks()
//  }
//
//  private fun requestLocationPermissions() {
//    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//      // Request the location permission
//      ActivityCompat.requestPermissions(
//        this,
//        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
//        REQUEST_LOCATION_PERMISSION
//      )
//    } else {
//      // If already granted, detect the user's location
//      detectUserLocationAndLeadToRegulation()
//    }
//  }
//
//  private fun detectUserLocationAndLeadToRegulation() {
//    val location = getLastKnownLocationSafely()
//    location?.let {
//      // Use the location to detect the country and lead to the appropriate regulation
//      // val country = detectCountry(it)
//      // leadToRegulationBasedOnCountry(country)
//    }
//  }
//
//  private fun getLastKnownLocationSafely(): Location? {
//    val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//    return try {
//      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//        requestLocationPermissions()
//        null
//      } else {
//        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
//      }
//    } catch (e: SecurityException) {
//      e.printStackTrace()
//      null
//    }
//  }
//
//  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    if (requestCode == REQUEST_LOCATION_PERMISSION) {
//      if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//        detectUserLocationAndLeadToRegulation()
//      } else {
//        // Handle permission denial if necessary
//      }
//    }
//  }
//
//  override fun setUpClicks(): Unit {
//    binding.btnContinue.setOnClickListener {
//      val destIntent = PairedActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_PAIRED_ACTIVITY)
//    }
//  }
//
//  companion object {
//    const val TAG: String = "CHOOSE_THE_LANGUAGE_ACTIVITY"
//
//    fun getIntent(context: Context, bundle: Bundle?): Intent {
//      val destIntent = Intent(context, ChooseTheLanguageActivity::class.java)
//      destIntent.putExtra("bundle", bundle)
//      return destIntent
//    }
//  }
//}

//package com.yuanyuansapplication.app.modules.choosethelanguage.ui
//
//import android.Manifest
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.location.Location
//import android.location.LocationManager
//import android.os.Bundle
//import androidx.activity.viewModels
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.yuanyuansapplication.app.R
//import com.yuanyuansapplication.app.appcomponents.base.BaseActivity
//import com.yuanyuansapplication.app.databinding.ActivityChooseTheLanguageBinding
//import com.yuanyuansapplication.app.modules.choosethelanguage.data.viewmodel.ChooseTheLanguageVM
//import com.yuanyuansapplication.app.modules.paired.ui.PairedActivity
//
//class ChooseTheLanguageActivity :
//  BaseActivity<ActivityChooseTheLanguageBinding>(R.layout.activity_choose_the_language) {
//
//  private val viewModel: ChooseTheLanguageVM by viewModels()
//
//  private val REQUEST_CODE_PAIRED_ACTIVITY: Int = 791
//  private val REQUEST_LOCATION_PERMISSION: Int = 100
//
//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    binding = ActivityChooseTheLanguageBinding.inflate(layoutInflater)
//    setContentView(binding.root)
//
//    // Request location permission every time the app runs
//    requestLocationPermissions()
//
//    setUpClicks()
//  }
//
//  private fun requestLocationPermissions() {
//    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//      // Request the location permission
//      ActivityCompat.requestPermissions(
//        this,
//        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
//        REQUEST_LOCATION_PERMISSION
//      )
//    } else {
//      // If already granted, navigate to PairedActivity
//      navigateToPairedActivity()
//    }
//  }
//
//  private fun navigateToPairedActivity() {
//    val destIntent = PairedActivity.getIntent(this, null)
//    startActivityForResult(destIntent, REQUEST_CODE_PAIRED_ACTIVITY)
//  }
//
//  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    if (requestCode == REQUEST_LOCATION_PERMISSION) {
//      if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//        // Permission granted, navigate to PairedActivity
//        navigateToPairedActivity()
//      } else {
//        // Permission denied, stay in this activity
//        // Optional: You can show a message or guide the user to select manually
//      }
//    }
//  }
//
//  override fun setUpClicks(): Unit {
//    binding.btnContinue.setOnClickListener {
//      val destIntent = PairedActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_PAIRED_ACTIVITY)
//    }
//  }
//
//  companion object {
//    const val TAG: String = "CHOOSE_THE_LANGUAGE_ACTIVITY"
//
//    fun getIntent(context: Context, bundle: Bundle?): Intent {
//      val destIntent = Intent(context, ChooseTheLanguageActivity::class.java)
//      destIntent.putExtra("bundle", bundle)
//      return destIntent
//    }
//  }
//}

//package com.yuanyuansapplication.app.modules.choosethelanguage.ui
//
//import android.Manifest
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.os.Bundle
//import androidx.activity.viewModels
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.yuanyuansapplication.app.R
//import com.yuanyuansapplication.app.appcomponents.base.BaseActivity
//import com.yuanyuansapplication.app.databinding.ActivityChooseTheLanguageBinding
//import com.yuanyuansapplication.app.modules.choosethelanguage.data.viewmodel.ChooseTheLanguageVM
//import com.yuanyuansapplication.app.modules.paired.ui.PairedActivity
//
//class ChooseTheLanguageActivity :
//  BaseActivity<ActivityChooseTheLanguageBinding>(R.layout.activity_choose_the_language) {
//
//  private val viewModel: ChooseTheLanguageVM by viewModels()
//
//  private val REQUEST_CODE_PAIRED_ACTIVITY: Int = 791
//  private val REQUEST_LOCATION_PERMISSION: Int = 100
//
//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    binding = ActivityChooseTheLanguageBinding.inflate(layoutInflater)
//    setContentView(binding.root)
//
//    // Request location permission every time the app runs
//    requestLocationPermissions()
//
//    setUpClicks()
//  }
//
//  private fun requestLocationPermissions() {
//    // Always request location permissions regardless of whether they are already granted
//    ActivityCompat.requestPermissions(
//      this,
//      arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
//      REQUEST_LOCATION_PERMISSION
//    )
//  }
//
//  private fun navigateToPairedActivity() {
//    val destIntent = PairedActivity.getIntent(this, null)
//    startActivityForResult(destIntent, REQUEST_CODE_PAIRED_ACTIVITY)
//  }
//
//  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    if (requestCode == REQUEST_LOCATION_PERMISSION) {
//      if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//        // Permission granted, navigate to PairedActivity
//        navigateToPairedActivity()
//      } else {
//        // Permission denied, stay in this activity
//        // Optional: You can show a message or guide the user to select manually
//      }
//    }
//  }
//
//  override fun setUpClicks(): Unit {
//    binding.btnContinue.setOnClickListener {
//      val destIntent = PairedActivity.getIntent(this, null)
//      startActivityForResult(destIntent, REQUEST_CODE_PAIRED_ACTIVITY)
//    }
//  }
//
//  companion object {
//    const val TAG: String = "CHOOSE_THE_LANGUAGE_ACTIVITY"
//
//    fun getIntent(context: Context, bundle: Bundle?): Intent {
//      val destIntent = Intent(context, ChooseTheLanguageActivity::class.java)
//      destIntent.putExtra("bundle", bundle)
//      return destIntent
//    }
//  }
//}

package com.yuanyuansapplication.app.modules.choosethelanguage.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.yuanyuansapplication.app.R
import com.yuanyuansapplication.app.appcomponents.base.BaseActivity
import com.yuanyuansapplication.app.databinding.ActivityChooseTheLanguageBinding
import com.yuanyuansapplication.app.modules.choosethelanguage.data.viewmodel.ChooseTheLanguageVM
import com.yuanyuansapplication.app.modules.paired.ui.PairedActivity

class ChooseTheLanguageActivity :
  BaseActivity<ActivityChooseTheLanguageBinding>(R.layout.activity_choose_the_language) {

  private val viewModel: ChooseTheLanguageVM by viewModels()
  private val REQUEST_CODE_PAIRED_ACTIVITY: Int = 791
  private val REQUEST_LOCATION_PERMISSION: Int = 100

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityChooseTheLanguageBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // Request location permission every time the app runs
    requestLocationPermissions()

    setUpClicks()
  }

  private fun requestLocationPermissions() {
    // Always request location permissions regardless of whether they are already granted
    ActivityCompat.requestPermissions(
      this,
      arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
      REQUEST_LOCATION_PERMISSION
    )
  }

  private fun navigateToPairedActivity() {
    val destIntent = PairedActivity.getIntent(this, null)
    startActivityForResult(destIntent, REQUEST_CODE_PAIRED_ACTIVITY)
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == REQUEST_LOCATION_PERMISSION) {
      if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        // Permission granted, navigate to PairedActivity
        navigateToPairedActivity()
      } else {
        // Permission denied, stay in this activity
      }
    }
  }

  override fun setUpClicks(): Unit {
    binding.btnContinue.setOnClickListener {
      val destIntent = PairedActivity.getIntent(this, null)
      startActivityForResult(destIntent, REQUEST_CODE_PAIRED_ACTIVITY)
    }
  }

  companion object {
    const val TAG: String = "CHOOSE_THE_LANGUAGE_ACTIVITY"

    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ChooseTheLanguageActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
