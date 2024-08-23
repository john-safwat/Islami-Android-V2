package com.john.islamiv2.Home.Tabs.Hadeth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.john.islamiv2.databinding.FragmentTimeBinding

class TimeFragment : Fragment() {

    lateinit var viewBinding: FragmentTimeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentTimeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

}