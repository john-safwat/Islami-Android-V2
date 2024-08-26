package com.john.islamiv2.UI.OnBoarding

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.INVISIBLE
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.john.islamiv2.Models.OnBoardingData
import com.john.islamiv2.R
import com.john.islamiv2.Utils.Constants
import com.john.islamiv2.Utils.LocalManager
import com.john.islamiv2.databinding.ItemOnBoardingPageBinding

class OnBoardingViewPagerAdapter(private var onBoardingData: MutableList<OnBoardingData>) :
    RecyclerView.Adapter<OnBoardingViewPagerAdapter.OnBoardingViewHolder>() {

    // ViewHolder for individual onboarding pages
    @Suppress("DEPRECATION")
    inner class OnBoardingViewHolder(val viewBinding: ItemOnBoardingPageBinding) :
        ViewHolder(viewBinding.root) {
        var sharedPreferences: SharedPreferences? = null

        // Binds data to the views within the onboarding page item
        fun bind(onBoardingData: OnBoardingData, position: Int) {
            if (sharedPreferences == null) {
                sharedPreferences = viewBinding.root.context.getSharedPreferences(
                    Constants.SHARED_PREFERENCES_KEY,
                    Context.MODE_PRIVATE
                )
            }
            viewBinding.imgOnBoarding.setImageResource(onBoardingData.image)
            viewBinding.txtTitleOnboarding.text = onBoardingData.title
            if (position == 0) {
                bindLocalSelectionView() // Show language selection on the first page
            } else {
                bindDefaultView(onBoardingData.description) // Show default content for other pages
            }

        }

        // Sets up the UI for language selection
        private fun bindLocalSelectionView() {
            setUpDropDownMenu() // Populate the dropdown with language options
            onLanguageDropDownMenuClick() // Handle language selection clicks
            viewBinding.txtDescriptionOnboarding.visibility =
                INVISIBLE // Hide the default description
            viewBinding.autoCompleteTextView.setDropDownBackgroundTint(
                viewBinding.root.resources.getColor(R.color.black)
            ) // Set dropdown background color
            viewBinding.autoCompleteTextView.visibility = VISIBLE // Show the dropdown
        }

        // Handles language selection from the dropdown menu
        private fun onLanguageDropDownMenuClick() {
            viewBinding.autoCompleteTextView.setOnItemClickListener { parent, _, position, _ ->
                val selectedLocal = parent.getItemAtPosition(position).toString()
                changeLocal(selectedLocal)
            }
        }

        // Sets up the dropdown menu with language options
        private fun setUpDropDownMenu() {
            val localsList = arrayOf(
                viewBinding.root.resources.getString(R.string.english),
                viewBinding.root.resources.getString(R.string.arabic),
            )
            val dropDownAdapter =
                ArrayAdapter(viewBinding.root.context, R.layout.item_drop_down, localsList)
            setupDefaultLocalTitle() // Set the initial language based on current locale
            viewBinding.autoCompleteTextView.setAdapter(dropDownAdapter)
        }

        // Determines and sets the default language for the dropdown
        private fun setupDefaultLocalTitle() {
            val currentLocal = sharedPreferences?.getString(
                Constants.LOCAL_KEY,
                viewBinding.root.resources.getString(R.string.english)
            )
            if (currentLocal.equals(viewBinding.root.resources.getString(R.string.english))) {
                setLocalDropDownTitle(viewBinding.root.resources.getString(R.string.english))
            } else {
                setLocalDropDownTitle(viewBinding.root.resources.getString(R.string.arabic))
            }
        }

        private fun changeLocal(selectedLocal: String) {
            val localCode =
                if (selectedLocal == viewBinding.root.resources.getString(R.string.english)) Constants.ENGLISH_LOCAL_CODE else Constants.ARABIC_LOCAL_CODE // Determine locale code
            LocalManager.setLocale(
                viewBinding.root.context,
                localCode
            ) // Change the app's locale
            val title =
                if (localCode == Constants.ENGLISH_LOCAL_CODE) viewBinding.root.resources.getString(
                    R.string.english
                ) else viewBinding.root.resources.getString(R.string.arabic)
            sharedPreferences?.edit()?.putString(Constants.LOCAL_KEY, title)?.apply()
            setLocalDropDownTitle(title) // Update the dropdown title
            onLocalSelectListener?.onSelect() // Notify listener of locale change
        }

        // Updates the title displayed in the language dropdown
        private fun setLocalDropDownTitle(local: String) {
            viewBinding.autoCompleteTextView.setText(local)
        }

        //Sets up the UI for default onboarding pages (not the language selection page)
        private fun bindDefaultView(description: String) {
            viewBinding.txtDescriptionOnboarding.text = description
            viewBinding.txtDescriptionOnboarding.visibility = VISIBLE // Show the description
            viewBinding.dropdownLanguagesMenu.visibility = INVISIBLE // Hide the dropdown
        }

    }

    // Creates a ViewHolder for an onboarding page item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val viewBinding =
            ItemOnBoardingPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnBoardingViewHolder(viewBinding)
    }

    // Returns the number of onboarding pages
    override fun getItemCount(): Int {
        return onBoardingData.size
    }

    // Binds data to the ViewHolder at the specified position
    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoardingData[position], position)
    }

    // Interface for handling locale selection events
    fun interface OnLocalSelectListener {
        fun onSelect()
    }

    private var onLocalSelectListener: OnLocalSelectListener? = null

    // Registers a listener to be notified when a locale is selected
    fun registerOnLocalSelectListener(listener: OnLocalSelectListener) {
        onLocalSelectListener = listener
    }
}