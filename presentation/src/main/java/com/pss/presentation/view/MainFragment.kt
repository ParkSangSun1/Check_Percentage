package com.pss.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pss.library.CountNumberEvent.Companion.countNumber
import com.pss.presentation.R
import com.pss.presentation.base.BaseFragment
import com.pss.presentation.databinding.FragmentMainBinding
import com.pss.presentation.viewmode.MainViewModel


class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        binding.fragment = this
        observeViewModel()
    }

    fun startBtnClick(view: View) {
        this.findNavController().navigate(R.id.action_mainFragment_to_womanNameFragment)
    }

    private fun observeViewModel() {
        mainViewModel.getStatisticsDisplayEvent.observe(this) {
            binding.number.text = ""
            countNumber(0, it, binding.number, 1000)
        }
    }
}