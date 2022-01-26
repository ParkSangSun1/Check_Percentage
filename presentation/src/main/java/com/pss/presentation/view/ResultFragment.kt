package com.pss.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.pss.domain.model.DomainLoveResponse
import com.pss.presentation.R
import com.pss.presentation.base.BaseFragment
import com.pss.presentation.databinding.FragmentResultBinding
import com.pss.presentation.viewmode.MainViewModel

class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        initResult()
    }

    private fun initResult(){
        binding.percentage.text = mainViewModel.apiCallResult.percentage.toString()
    }
}