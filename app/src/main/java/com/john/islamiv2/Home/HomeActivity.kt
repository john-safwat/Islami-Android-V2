package com.john.islamiv2.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.john.islamiv2.Home.Tabs.Hadeth.HadethFragment
import com.john.islamiv2.Home.Tabs.Hadeth.TimeFragment
import com.john.islamiv2.Home.Tabs.Quran.QuranFragment
import com.john.islamiv2.Home.Tabs.Radio.RadioFragment
import com.john.islamiv2.Home.Tabs.Sebha.SebhaFragment
import com.john.islamiv2.R
import com.john.islamiv2.databinding.ActivityHomeBinding

class HomeActivity() : AppCompatActivity() {
    private var firstFragment:Boolean= true
    private var fragmentsIdsList = mutableListOf<Int>()
    private lateinit var viewBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.bottomNavigationBar.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.navigation_quran -> { QuranFragment() }
                R.id.navigation_hadeth-> { HadethFragment() }
                R.id.navigation_sebha -> { SebhaFragment() }
                R.id.navigation_radio -> { RadioFragment() }
                R.id.navigation_more -> { TimeFragment() }
                else -> { QuranFragment() }
            }
            setFragment(fragment , item.itemId)
            true
        }
        viewBinding.bottomNavigationBar.selectedItemId = R.id.navigation_quran

    }

    private fun setFragment(fragment: Fragment, itemId: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.contentContainer.id, fragment)
            .commit()
    }
}