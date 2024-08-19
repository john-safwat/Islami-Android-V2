package com.john.islamiv2.OnBoarding

import android.content.Intent
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.john.islamiv2.R
import com.john.islamiv2.databinding.ItemOnBoardingPageBinding
import java.util.Locale

class OnBoardingViewPagerAdapter(private val onBoardingData: MutableList<OnBoardingData>) :
    RecyclerView.Adapter<OnBoardingViewPagerAdapter.OnBoardingViewHolder>() {

    inner class OnBoardingViewHolder(private val viewBinding: ItemOnBoardingPageBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(onBoardingData: OnBoardingData, position: Int) {
            viewBinding.imgOnBoarding.setImageResource(onBoardingData.image)
            viewBinding.txtTitleOnboarding.text = onBoardingData.title
            if (position == 0) {

                setLanguageSettings()
                onLanguageDropDownMenuClick()
                bindLanguagePage()
            } else {
                bindDefaultPage(onBoardingData.description)
            }
        }

        private fun onLanguageDropDownMenuClick() {
            viewBinding.dropdownLanguagesMenu.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selectedLanguage = when (position) {
                            0 -> "en" // English
                            1 -> "ar" // Arabic
                            else -> "en"
                        }
                        applyLanguageChange(selectedLanguage)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // No action needed
                    }
                }
        }

        private fun applyLanguageChange(languageCode: String) {
            if (languageCode == "ar") {
                val locale = Locale(languageCode)
                Locale.setDefault(locale)
                val configuration = Configuration(viewBinding.root.context.resources.configuration)
                configuration.setLocale(locale)
                viewBinding.root.context.resources.updateConfiguration(
                    configuration,
                    viewBinding.root.context.resources.displayMetrics
                )
                val intent = Intent(viewBinding.root.context, OnBoardingActivity::class.java)
                viewBinding.root.context.startActivity(intent)
            }

        }

        private fun setLanguageSettings() {
            val languages = viewBinding.root.resources.getStringArray(R.array.languages)
            val arrayAdapterLanguages =
                ArrayAdapter(super.itemView.context, R.layout.item_drop_down, languages)
            viewBinding.dropdownLanguagesMenu.setAdapter(arrayAdapterLanguages)

        }

        private fun bindLanguagePage() {
            viewBinding.txtDescriptionOnboarding.visibility = View.INVISIBLE
            viewBinding.dropdownLanguagesMenu.visibility = View.VISIBLE
        }

        private fun bindDefaultPage(description: String) {
            viewBinding.txtDescriptionOnboarding.visibility = View.VISIBLE
            viewBinding.txtDescriptionOnboarding.text = description
            viewBinding.dropdownLanguagesMenu.visibility = View.INVISIBLE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val viewBinding =
            ItemOnBoardingPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnBoardingViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return onBoardingData.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoardingData[position], position)
    }

}