package com.john.islamiv2.UI.Home.Tabs.Radio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.john.islamiv2.databinding.FragmentRadioBinding

class RadioFragment : Fragment() {

    lateinit var viewBinding:FragmentRadioBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentRadioBinding.inflate(inflater,container,false)
        return viewBinding.root
    }
}