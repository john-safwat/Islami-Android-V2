package com.john.islamiv2.UI.Intro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.john.islamiv2.UI.OnBoarding.OnBoardingActivity
import com.john.islamiv2.R
import com.john.islamiv2.Utils.Constants
import com.john.islamiv2.Utils.LocalManager
import com.john.islamiv2.databinding.ActivityIntroBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Activity for displaying the intro screen
class IntroActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityIntroBinding
    var sharedPreferences: SharedPreferences? = null

    // Called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen() // Install splash screen for smoother launch
        setUpLocal()
        viewBinding = ActivityIntroBinding.inflate(layoutInflater)
        initViewsAnimations() // Start animations for intro elements
        navigateToOnBoardingActivity() // Schedule navigation to the onboarding screen
        setContentView(viewBinding.root)

    }

    private fun setUpLocal() {
        sharedPreferences =
            getSharedPreferences(Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
        val selectedLocal =
            sharedPreferences?.getString(Constants.LOCAL_KEY, resources.getString(R.string.english))
        val localCode = if (selectedLocal == resources.getString(R.string.english)) {
            Constants.ENGLISH_LOCAL_CODE
        } else {
            Constants.ARABIC_LOCAL_CODE
        }
        LocalManager.setLocale(this, localCode)
    }

    // Navigates to the OnBoardingActivity after a delay
    private fun navigateToOnBoardingActivity() {
        lifecycleScope.launch {
            delay(3500) // Delay for 3.5 seconds
            startActivity(
                Intent(this@IntroActivity, OnBoardingActivity::class.java),
            )
            finish() // Finish the IntroActivity
        }
    }

    // Initializes animations for various views in the intro screen
    private fun initViewsAnimations() {
        viewBinding.imgBackgroundIntro.startAnimation(generateAnimation(R.anim.fade_in_animation))
        viewBinding.imgIntroLogo.startAnimation(generateAnimation(R.anim.scale_up_and_rotate_animation))
        viewBinding.imgLampLight.startAnimation(generateAnimation(R.anim.swing_animation))
        viewBinding.imgMosqueLogoHeader.startAnimation(generateAnimation(R.anim.scale_up_animation))
        viewBinding.imgBrandingLogo.startAnimation(generateAnimation(R.anim.slide_up_animation))
        viewBinding.imgFlowerRight.startAnimation(generateAnimation(R.anim.slide_right_animation))
        viewBinding.imgFlowerLeft.startAnimation(generateAnimation(R.anim.slide_left_animation))
    }

    // Loads an animation from resources
    private fun generateAnimation(scaleUpAndRotateAnimation: Int): Animation? {
        return android.view.animation.AnimationUtils.loadAnimation(
            this,
            scaleUpAndRotateAnimation
        )
    }
}