package com.john.islamiv2.Intro

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.john.islamiv2.OnBoarding.OnBoardingActivity
import com.john.islamiv2.R
import com.john.islamiv2.databinding.ActivityIntroBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        viewBinding = ActivityIntroBinding.inflate(layoutInflater)
        initViewsAnimations()
        navigateToOnBoardingActivity()
        setContentView(viewBinding.root)
    }

    private fun navigateToOnBoardingActivity() {

        lifecycleScope.launch {
            delay(3500)
            startActivity(
                Intent(this@IntroActivity, OnBoardingActivity::class.java),
            )
            finish()
        }

    }


    private fun initViewsAnimations() {
        viewBinding.imgBackgroundIntro.startAnimation(generateAnimation(R.anim.fade_in_animation))
        viewBinding.imgIntroLogo.startAnimation(generateAnimation(R.anim.scale_up_and_rotate_animation))
        viewBinding.imgFlowerRight.startAnimation(generateAnimation(R.anim.slide_right_animation))
        viewBinding.imgFlowerLeft.startAnimation(generateAnimation(R.anim.slide_left_animation))
        viewBinding.imgLampLight.startAnimation(generateAnimation(R.anim.swing_animation))
        viewBinding.imgMosqueLogoHeader.startAnimation(generateAnimation(R.anim.scale_up_animation))
        viewBinding.imgBrandingLogo.startAnimation(generateAnimation(R.anim.slide_up_animation))
    }

    private fun generateAnimation(scaleUpAndRotateAnimation: Int): Animation? {
        return android.view.animation.AnimationUtils.loadAnimation(
            this,
            scaleUpAndRotateAnimation
        )
    }

}