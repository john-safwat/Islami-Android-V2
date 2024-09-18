package com.john.islamiv2.UI.Home.Tabs.Sebha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.john.islamiv2.R
import com.john.islamiv2.databinding.FragmentSebhaBinding

class SebhaFragment : Fragment() {

    lateinit var viewBinding: FragmentSebhaBinding
    private val tasbihTitle = listOf(
        "سبحان الله",
        "الحمد لله",
        "الله اكبر"
    )
    private var selectedIndex = 0
    private var tasbehCount = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSebhaBinding.inflate(inflater, container, false)
        initViewParameters()
        return viewBinding.root
    }

    private fun initViewParameters() {
        viewBinding.tvTasbehTitle.text = tasbihTitle[selectedIndex]
        viewBinding.tvTasbehCount.text = tasbehCount.toString()
        viewBinding.imgSebhaBody.setOnClickListener {
            doRotation()
        }
    }

    private fun doRotation() {

        updateParameters()
        updateView()
        rotateSebhaBody()

    }

    private fun rotateSebhaBody() {
        viewBinding.imgSebhaBody.animate().rotationBy(22f).setDuration(500).start()
    }

    private fun updateView() {
        viewBinding.tvTasbehTitle.text = tasbihTitle[selectedIndex]
        viewBinding.tvTasbehCount.text = tasbehCount.toString()
    }

    private fun updateParameters() {
        if(tasbehCount == 33){
            selectedIndex =  ((selectedIndex + 1 )% 3)
            tasbehCount = 0
        }
        tasbehCount++

    }


}