package com.john.islamiv2.UI.Home.Tabs.Hadeth

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.john.islamiv2.Models.Hadith
import com.john.islamiv2.UI.HadithDetails.HadithDetailsActivity
import com.john.islamiv2.Utils.Constants
import com.john.islamiv2.databinding.FragmentHadethBinding

class HadethFragment : Fragment() {

    lateinit var viewBinding: FragmentHadethBinding
    lateinit var adapter: AhadithListRecyclerViewAdapter
    private var ahadithList = mutableListOf<Hadith>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHadethBinding.inflate(inflater, container, false)
        initView()
        return viewBinding.root
    }

    private fun initView(){
        loadAhadithList()
        initAhadithRecyclerView()
        initSearchBar()
    }

    private fun initSearchBar() {
        viewBinding.etSearchInput.addTextChangedListener(
            object :TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ){}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    search(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {}

            }
        )
    }

    private fun search(s: String) {
        val searchList = ahadithList.filter {
            it.content.contains(s)
        }.toMutableList()
        if(searchList.isEmpty()){
            adapter.updateList(ahadithList)
        }else {
            adapter.updateList(searchList)
        }
    }

    private fun loadAhadithList() {
        val inputStream = resources.assets.open("ahadith.txt")
        val inputData = inputStream.bufferedReader().readText().trim().split("#")
        for(i in inputData.indices){
            val hadithStrings = inputData[i].trim().split("\n")
            val hadithTitle = hadithStrings[0]
            var hadithContent = ""
            for (j in 1 until hadithStrings.size) {
                hadithContent = hadithContent + hadithStrings[j].trim() + ""
            }
            val hadith = Hadith(hadithTitle, hadithContent)
            ahadithList.add(hadith)
        }
    }

    private fun initAhadithRecyclerView() {
        adapter = AhadithListRecyclerViewAdapter(ahadithList)
        viewBinding.carouselRecyclerview.adapter = adapter
        viewBinding.carouselRecyclerview.apply {
            setInfinite(true)
            setIntervalRatio(0.6f)
        }
        adapter.onItemClickListener = AhadithListRecyclerViewAdapter.OnItemClickListener {
            navigateToHadithDetailsScreen(it)
        }
    }

    private fun navigateToHadithDetailsScreen(hadith: Hadith) {
        val intent = Intent(activity , HadithDetailsActivity::class.java)
        intent.putExtra(Constants.Hadith_EXTRA_KEY, hadith)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())

    }


}