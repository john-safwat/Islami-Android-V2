package com.john.islamiv2.OnBoarding

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.john.islamiv2.R
import com.john.islamiv2.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityOnBoardingBinding
    private lateinit var adapter: OnBoardingViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityOnBoardingBinding.inflate(layoutInflater)
        initViewPager()
        setContentView(viewBinding.root)
    }

    private fun initViewPager() {
        adapter = OnBoardingViewPagerAdapter(OnBoardingData.getOnBoardingData())
        viewBinding.viewPagerOnBoarding.adapter = adapter
        viewBinding.dotsIndicator.attachTo(viewBinding.viewPagerOnBoarding)
        viewBinding.viewPagerOnBoarding.registerOnPageChangeCallback(
            object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    onPageChanged(position)
                }
            }
        )
        viewBinding.btnOnboardingRight.setOnClickListener{
            onRightButtonClick()
        }
        viewBinding.btnOnboardingLeft.setOnClickListener{
            onLeftButtonClick()
        }
    }



    private fun onPageChanged(position: Int) {

        when (position) {
            0 -> {
                viewBinding.btnOnboardingLeft.text = ""
                viewBinding.btnOnboardingLeft.isEnabled = false
            }
            1 -> {
                viewBinding.btnOnboardingLeft.text = getString(R.string.back)
                viewBinding.btnOnboardingLeft.isEnabled = true
            }
            else -> {
                viewBinding.btnOnboardingLeft.text = getString(R.string.back)
                viewBinding.btnOnboardingLeft.isEnabled = true
            }
        }

        when (position){
            4 ->{
                viewBinding.btnOnboardingRight.text = getString(R.string.finish)
            }
            else ->{
                viewBinding.btnOnboardingRight.text = getString(R.string.next)
            }
        }

    }

    private fun onRightButtonClick() {
        val currentItem = viewBinding.viewPagerOnBoarding.currentItem
        if (currentItem < adapter.itemCount - 1) {
            viewBinding.viewPagerOnBoarding.currentItem = currentItem + 1
        }else{
            updateSharedPref()
            navigateToHomeActivity()
        }
    }

    private fun updateSharedPref() {

    }

    private fun onLeftButtonClick() {
        val currentItem = viewBinding.viewPagerOnBoarding.currentItem
        if (currentItem > 0) {
            viewBinding.viewPagerOnBoarding.currentItem = currentItem - 1
        }
    }

    private fun navigateToHomeActivity() {

    }

}