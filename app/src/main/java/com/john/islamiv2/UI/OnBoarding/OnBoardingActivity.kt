package com.john.islamiv2.UI.OnBoarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.john.islamiv2.UI.Home.HomeActivity
import com.john.islamiv2.R
import com.john.islamiv2.databinding.ActivityOnBoardingBinding

// Activity for displaying the onboarding flow
class OnBoardingActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityOnBoardingBinding
    private lateinit var adapter: OnBoardingViewPagerAdapter

    // Called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityOnBoardingBinding.inflate(layoutInflater)
        initViewPager() // Initialize the ViewPager for onboarding pages
        setContentView(viewBinding.root)
    }

    // Initializes the ViewPager, adapter, and page change listener@SuppressLint("NotifyDataSetChanged")
    private fun initViewPager() {
        adapter = OnBoardingViewPagerAdapter(getOnBoardingData())
        adapter.registerOnLocalSelectListener {
            recreate() // Recreate the activity when locale is changed
        }
        viewBinding.viewPagerOnBoarding.adapter = adapter
        viewBinding.dotsIndicator.attachTo(viewBinding.viewPagerOnBoarding) // Attach dots indicator
        viewBinding.viewPagerOnBoarding.registerOnPageChangeCallback( // Register page change callback
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    onPageChanged(position) // Handle page selection changes
                }
            }
        )
        viewBinding.btnOnboardingRight.setOnClickListener {
            onRightButtonClick() // Handle right button clicks
        }
        viewBinding.btnOnboardingLeft.setOnClickListener {
            onLeftButtonClick() // Handle left button clicks
        }
    }

    // Returns a list of onboarding data (image, title, description)
    private fun getOnBoardingData(): MutableList<OnBoardingData> {
        return mutableListOf(
            OnBoardingData(R.drawable.img_onboarding_1, getString(R.string.chose_language), ""),
            OnBoardingData(
                R.drawable.img_onboarding_2,
                getString(R.string.welcome_to_islami),
                getString(R.string.we_are_very_excited_to_have_you_in_our_community)
            ),
            OnBoardingData(
                R.drawable.img_onboarding_3,
                getString(R.string.reading_the_quran),
                getString(R.string.read_and_your_lord_is_the_most_generous)
            ),
            OnBoardingData(
                R.drawable.img_onboarding_4,
                getString(R.string.bearish),
                getString(R.string.praise_the_name_of_your_lord_the_most_high)
            ),
            OnBoardingData(
                R.drawable.img_onboarding_5,
                getString(R.string.holy_quran_radio),
                getString(R.string.you_can_listen_to_the_holy_quran_radio_through_the_application_for_free_and_easily)
            ),
        )
    }

    // Handles page change events, updating button visibility and text
    private fun onPageChanged(position: Int) {
        when (position) {
            0 -> { // Hide left button on the first page
                viewBinding.btnOnboardingLeft.text = ""
                viewBinding.btnOnboardingLeft.isEnabled = false
            }
            1 -> { // Show "Back" button on the second page
                viewBinding.btnOnboardingLeft.text = getString(R.string.back)
                viewBinding.btnOnboardingLeft.isEnabled = true
            }
            else -> { // Show "Back" button on all other pages
                viewBinding.btnOnboardingLeft.text = getString(R.string.back)
                viewBinding.btnOnboardingLeft.isEnabled = true
            }
        }

        when (position) {
            4 -> { // Change right button text to "Finish" on the last page
                viewBinding.btnOnboardingRight.text = getString(R.string.finish)}
            else -> { // Show "Next" button on all other pages
                viewBinding.btnOnboardingRight.text = getString(R.string.next)
            }
        }
    }

    // Handles right button clicks (navigating to the next page or finishing onboarding)
    private fun onRightButtonClick() {
        val currentItem = viewBinding.viewPagerOnBoarding.currentItem
        if (currentItem < adapter.itemCount - 1) {
            viewBinding.viewPagerOnBoarding.currentItem = currentItem + 1 // Move to next page
        } else {
            updateSharedPref() // Update shared preferences (if needed)
            navigateToHomeActivity() // Navigate to the main app screen
        }
    }

    // Placeholder for updating shared preferences after onboarding
    private fun updateSharedPref() {
        //TODO("NO Implemented Yet")
    }

    // Handles left button clicks (navigating to the previous page)
    private fun onLeftButtonClick() {
        val currentItem = viewBinding.viewPagerOnBoarding.currentItem
        if (currentItem > 0) {
            viewBinding.viewPagerOnBoarding.currentItem = currentItem - 1 // Move to previous page
        }
    }

    // Navigates to the HomeActivity and finishes the onboarding activity
    private fun navigateToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}