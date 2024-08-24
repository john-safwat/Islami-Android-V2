package com.john.islamiv2.UI.Home.Tabs.Quran

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.john.islamiv2.databinding.FragmentQuranBinding

class QuranFragment : Fragment() {

    lateinit var viewBinding: FragmentQuranBinding
    private lateinit var mostRecentSourasRecyclerViewAdapter: MostRecentSourasRecyclerViewAdapter
    private lateinit var surasListRecyclerViewAdapter: SurasListRecyclerViewAdapter
    private var surasList = Sura.getListOfSuras()
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
                    search(((s?:"").toString()))
                }

                override fun afterTextChanged(s: Editable?) {}

            }
        )
    }

    private fun search(query: String) {
        if(query.isEmpty()){
            searchSurasList = mutableListOf()
        }else {
            searchSurasList = surasList.filter {
                it.englishTitle.lowercase().contains(query.lowercase())
            }.toMutableList()
            if(searchSurasList.isEmpty()){
                searchSurasList = surasList.filter {
                    it.arabicTitle.lowercase().contains(query.lowercase())
                }.toMutableList()
            }
        }
        if(searchSurasList.isEmpty()){
            surasListRecyclerViewAdapter.updateList(surasList)
        }else{
            surasListRecyclerViewAdapter.updateList(searchSurasList)
        }
        updateView()
    }


    private fun initView(){
        initMostRecentSurasRecyclerView()
        initSurasListRecyclerView()
        initSearchBar()
        updateView()
    }

    private fun updateView() {
        if(searchSurasList.isNotEmpty()||mostRecentSurasList.isEmpty()){
            updateViewVisibility(GONE)
        }else{
            updateViewVisibility(VISIBLE)
        }
    }


    private fun updateViewVisibility(visibility: Int){
        viewBinding.rvMostRecentSuras.visibility = visibility
        viewBinding.txtMostRecent.visibility = visibility
    }

    private fun initSurasListRecyclerView() {
        surasListRecyclerViewAdapter = SurasListRecyclerViewAdapter(surasList)
        viewBinding.rvSurasList.adapter = surasListRecyclerViewAdapter
    }

    private fun initMostRecentSurasRecyclerView() {
        mostRecentSourasRecyclerViewAdapter =
            MostRecentSourasRecyclerViewAdapter(mostRecentSurasList)
        viewBinding.rvMostRecentSuras.adapter = mostRecentSourasRecyclerViewAdapter
    }

}