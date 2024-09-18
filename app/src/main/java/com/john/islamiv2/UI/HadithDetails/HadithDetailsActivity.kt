package com.john.islamiv2.UI.HadithDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.john.islamiv2.Models.Hadith
import com.john.islamiv2.Utils.Constants
import com.john.islamiv2.databinding.ActivityHadithDetailsBinding

class HadithDetailsActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityHadithDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityHadithDetailsBinding.inflate(layoutInflater)
        initView()
        setContentView(viewBinding.root)
    }

    private fun initView() {
        initViewParameters()
        initToolBar()
    }

    private fun initToolBar() {
        viewBinding.toolBar.setNavigationOnClickListener { finish() }
    }

    private fun initViewParameters() {
        val hadith = intent.getParcelableExtra<Hadith>(Constants.HADITH_EXTRA_KEY)!!
        viewBinding.toolBar.title = hadith.title
        viewBinding.mainContent.txtAhadithTitle.txtTitle.text = hadith.title
        viewBinding.mainContent.tvHadithContent.text = hadith.content
    }

}