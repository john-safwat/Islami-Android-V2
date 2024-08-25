package com.john.islamiv2.UI.SuraDetails


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.john.islamiv2.Models.Aya
import com.john.islamiv2.Models.Sura
import com.john.islamiv2.Utils.Constants
import com.john.islamiv2.databinding.ActivitySuraDetailsBinding
import java.io.BufferedReader
import java.io.InputStreamReader

@Suppress("DEPRECATION")
class SuraDetailsActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivitySuraDetailsBinding
    private lateinit var sura: Sura
    private var ayasList  = mutableListOf<Aya>()
    private lateinit var ayasRecyclerViewAdapter: AyasRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySuraDetailsBinding.inflate(layoutInflater)
        initView()
        setContentView(viewBinding.root)
    }

    private fun initView() {
        getSuraFromExtras()
        setupViewParameters()
        readSura()
        initAyasRecyclerView()
    }

    private fun initAyasRecyclerView() {
        ayasRecyclerViewAdapter = AyasRecyclerViewAdapter(ayasList)
        viewBinding.mainContent.rvAyas.adapter = ayasRecyclerViewAdapter
    }


    private fun getSuraFromExtras() {
        sura = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(Constants.SURA_EXTRA_KEY, Sura::class.java)!!
        } else {
            intent.getParcelableExtra(Constants.SURA_EXTRA_KEY)!!
        }
    }

    private fun setupViewParameters() {
        viewBinding.toolBar.title = sura.englishTitle
        viewBinding.toolBar.setNavigationOnClickListener { finish() }
        viewBinding.mainContent.txtArabicSuraTitle.txtTitle.text = sura.arabicTitle
    }


    private fun readSura() {
        val inputStream =
            BufferedReader(InputStreamReader(assets.open("${sura.id}.txt"))).use { it.readText() }
        val suraContent = inputStream.trim().split("\n")

        for(i in suraContent.indices){
            ayasList.add(Aya("[${i+1}] ${suraContent[i].trim()}" , false))
        }
    }
}