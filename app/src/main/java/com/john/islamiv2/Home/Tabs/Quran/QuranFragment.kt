package com.john.islamiv2.Home.Tabs.Quran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.john.islamiv2.databinding.FragmentQuranBinding

class QuranFragment : Fragment() {

    lateinit var viewBinding:FragmentQuranBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentQuranBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

}