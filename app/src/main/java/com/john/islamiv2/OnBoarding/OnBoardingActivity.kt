package com.john.islamiv2.OnBoarding

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.john.islamiv2.Home.HomeActivity
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


    @SuppressLint("NotifyDataSetChanged")
    private fun initViewPager() {
        adapter = OnBoardingViewPagerAdapter(getOnBoardingData())
        adapter.registerOnLocalSelectListener {
            recreate()
        }
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

    fun getOnBoardingData():MutableList<OnBoardingData>{
        return mutableListOf(
            OnBoardingData(R.drawable.img_onboarding_1 , getString(R.string.chose_language) , ""),
            OnBoardingData(R.drawable.img_onboarding_2 ,
                getString(R.string.welcome_to_islami) ,
                getString(R.string.we_are_very_excited_to_have_you_in_our_community)),
            OnBoardingData(R.drawable.img_onboarding_3 ,
                getString(R.string.reading_the_quran) ,
                getString(R.string.read_and_your_lord_is_the_most_generous)),
            OnBoardingData(R.drawable.img_onboarding_4 ,
                getString(R.string.bearish) ,
                getString(R.string.praise_the_name_of_your_lord_the_most_high)),
            OnBoardingData(R.drawable.img_onboarding_5 ,
                getString(R.string.holy_quran_radio) ,
                getString(R.string.you_can_listen_to_the_holy_quran_radio_through_the_application_for_free_and_easily)),
        )
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
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}