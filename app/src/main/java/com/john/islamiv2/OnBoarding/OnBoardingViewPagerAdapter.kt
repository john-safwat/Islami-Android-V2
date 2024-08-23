package com.john.islamiv2.OnBoarding

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.INVISIBLE
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.john.islamiv2.R
import com.john.islamiv2.databinding.ItemOnBoardingPageBinding
import java.util.Locale

class OnBoardingViewPagerAdapter(private var onBoardingData: MutableList<OnBoardingData>) :
    RecyclerView.Adapter<OnBoardingViewPagerAdapter.OnBoardingViewHolder>() {

    @Suppress("DEPRECATION")
    inner class OnBoardingViewHolder(val viewBinding: ItemOnBoardingPageBinding) :
        ViewHolder(viewBinding.root) {

        fun bind(onBoardingData: OnBoardingData, position: Int) {
            viewBinding.imgOnBoarding.setImageResource(onBoardingData.image)
            viewBinding.txtTitleOnboarding.text = onBoardingData.title
            if (position == 0) {
                bindLocalSelectionView()
            } else {
                bindDefaultView(onBoardingData.description)
            }

        }

        private fun bindLocalSelectionView() {
            setUpDropDownMenu()
            onLanguageDropDownMenuClick()
            viewBinding.txtDescriptionOnboarding.visibility = INVISIBLE
            viewBinding.autoCompleteTextView.setDropDownBackgroundTint(
                viewBinding.root.resources.getColor(R.color.black)
            )
            viewBinding.autoCompleteTextView.visibility = VISIBLE
        }

        private fun onLanguageDropDownMenuClick() {
            viewBinding.autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
                val selectedLocal = parent.getItemAtPosition(position).toString()
                val localCode =
                    if (selectedLocal == "English" || selectedLocal == "الإنجليزية") "en_US" else "ar"
                setLocalDropDownTitle(selectedLocal)
                setLocale(viewBinding.root.context, localCode)
                onLocalSelectListener?.onSelect()
            }
        }

        private fun setLocale(context: Context, localCode: String) {
            val local = Locale(localCode)
            Locale.setDefault(local)
            val configuration = Configuration(context.resources.configuration)
            configuration.setLocale(local)
            context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
        }

        private fun setUpDropDownMenu() {
            val localsList = arrayOf(
                viewBinding.root.resources.getString(R.string.english),
                viewBinding.root.resources.getString(R.string.arabic),
            )
            val dropDownAdapter =
                ArrayAdapter(viewBinding.root.context, R.layout.item_drop_down, localsList)
            setupDefaultLocal()
            viewBinding.autoCompleteTextView.setAdapter(dropDownAdapter)
        }

        private fun setupDefaultLocal() {
            val currentLocal =
                viewBinding.root.resources.configuration.locales.get(0).displayLanguage
            if (currentLocal.equals("English") || currentLocal.equals("en_us")) {
                setLocalDropDownTitle("English")
            } else {
                setLocalDropDownTitle("العربية")
            }
        }

        private fun setLocalDropDownTitle(local: String) {
            viewBinding.autoCompleteTextView.setText(local)
        }

        private fun bindDefaultView(description: String) {
            viewBinding.txtDescriptionOnboarding.text = description
            viewBinding.txtDescriptionOnboarding.visibility = VISIBLE
            viewBinding.dropdownLanguagesMenu.visibility = INVISIBLE
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


    fun interface OnLocalSelectListener {
        fun onSelect()
    }

    private var onLocalSelectListener: OnLocalSelectListener? = null
    fun registerOnLocalSelectListener(listener: OnLocalSelectListener) {
        onLocalSelectListener = listener
    }
}