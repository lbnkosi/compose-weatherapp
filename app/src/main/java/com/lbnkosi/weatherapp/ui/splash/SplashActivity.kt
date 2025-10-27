package com.lbnkosi.weatherapp.ui.splash

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.lbnkosi.weatherapp.MainActivity
import com.lbnkosi.weatherapp.ui.permission.PermissionActivity
import com.lbnkosi.weatherapp.utils.Utility.launchActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        startPermissionsActivity()
        finish()
    }


    private fun startPermissionsActivity() {
        when (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            PERMISSION_GRANTED -> {
                launchActivity(packageName = packageName, className = MainActivity::class.java.name)
            }
            else -> {
                launchActivity(packageName = packageName, className = PermissionActivity::class.java.name)
            }
        }
    }
}