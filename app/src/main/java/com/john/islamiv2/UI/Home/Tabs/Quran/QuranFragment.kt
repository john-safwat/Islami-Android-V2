package com.john.islamiv2.UI.Home.Tabs.Quran

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import com.john.islamiv2.Database.SurasDatabase
import com.john.islamiv2.Models.Sura
import com.john.islamiv2.R
import com.john.islamiv2.UI.SuraDetails.SuraDetailsActivity
import com.john.islamiv2.Utils.Constants
import com.john.islamiv2.databinding.FragmentQuranBinding

class QuranFragment : Fragment() {

    lateinit var viewBinding: FragmentQuranBinding
    private lateinit var mostRecentSourasRecyclerViewAdapter: MostRecentSourasRecyclerViewAdapter
    private lateinit var surasListRecyclerViewAdapter: SurasListRecyclerViewAdapter
    private var surasList = Sura.getListOfSuras()
    private var surasDatabase :SurasDatabase? = SurasDatabase.getDatabase()
    private var mostRecentSurasList = Sura.getListOfSuras()
    private var searchSurasList = mutableListOf<Sura>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentQuranBinding.inflate(inflater, container, false)
        initView()
        return viewBinding.root
    }


    private fun initSearchBar() {
        viewBinding.textFieldSeuraSearchInput.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    search(((s ?: "").toString()))
                }

                override fun afterTextChanged(s: Editable?) {}

            }
        )
    }

    private fun search(query: String) {
        if (query.isEmpty()) {
            searchSurasList = mutableListOf()
        } else {
            searchSurasList = surasList.filter {
                it.englishTitle!!.lowercase().contains(query.lowercase())
            }.toMutableList()
            if (searchSurasList.isEmpty()) {
                searchSurasList = surasList.filter {
                    it.arabicTitle!!.lowercase().contains(query.lowercase())
                }.toMutableList()
            }
        }
        if (searchSurasList.isEmpty()) {
            surasListRecyclerViewAdapter.updateList(surasList)
        } else {
            surasListRecyclerViewAdapter.updateList(searchSurasList)
        }
        updateView()
    }

    override fun onResume() {
        super.onResume()
        mostRecentSurasList = surasDatabase?.surasDao()?.getAllSuras() ?: mutableListOf()
        mostRecentSourasRecyclerViewAdapter.updateSurasList(mostRecentSurasList)
    }

    private fun initView() {
        initMostRecentSurasRecyclerView()
        initSurasListRecyclerView()
        initSearchBar()
        updateView()
    }

    private fun updateView() {
        if (searchSurasList.isNotEmpty() || mostRecentSurasList.isEmpty()) {
            updateViewVisibility(GONE)
        } else {
            updateViewVisibility(VISIBLE)
        }
    }


    private fun updateViewVisibility(visibility: Int) {
        viewBinding.rvMostRecentSuras.visibility = visibility
        viewBinding.txtMostRecent.visibility = visibility
    }

    private fun initSurasListRecyclerView() {
        surasListRecyclerViewAdapter = SurasListRecyclerViewAdapter(surasList)
        surasListRecyclerViewAdapter.onItemClickListener =
            SurasListRecyclerViewAdapter.OnItemClickListener {
                insertSuraToDatabase(it)
                navigateToSuraDetailsActivity(it)
            }
        viewBinding.rvSurasList.adapter = surasListRecyclerViewAdapter
    }

    // function to init most recent suras recycler view and the on click listener
    private fun initMostRecentSurasRecyclerView() {
        mostRecentSurasList = surasDatabase?.surasDao()?.getAllSuras() ?: mutableListOf()
        mostRecentSourasRecyclerViewAdapter =
            MostRecentSourasRecyclerViewAdapter(mostRecentSurasList)
        mostRecentSourasRecyclerViewAdapter.onItemClickListener =
            MostRecentSourasRecyclerViewAdapter.OnItemClickListener {
                insertSuraToDatabase(it)
                navigateToSuraDetailsActivity(it)
            }
        viewBinding.rvMostRecentSuras.adapter = mostRecentSourasRecyclerViewAdapter
    }

    private fun insertSuraToDatabase(sura:Sura) {
        sura.updateTime = System.currentTimeMillis().toInt()
        if(!mostRecentSurasList.contains(sura)){
            surasDatabase?.surasDao()?.addSura(sura)
        }else {
            surasDatabase?.surasDao()?.updateSura(sura)
        }
    }

    // navigation function
    private fun navigateToSuraDetailsActivity(sura: Sura) {
        val intent = Intent(activity, SuraDetailsActivity::class.java)
        intent.putExtra(Constants.SURA_EXTRA_KEY, sura)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
    }

}