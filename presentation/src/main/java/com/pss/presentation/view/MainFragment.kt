package com.pss.presentation.view

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pss.library.CountNumberEvent.Companion.countNumber
import com.pss.presentation.R
import com.pss.presentation.adapter.ScoreRecyclerViewAdapter
import com.pss.presentation.base.BaseFragment
import com.pss.presentation.databinding.FragmentMainBinding
import com.pss.presentation.viewmodel.MainViewModel
import com.pss.presentation.widget.extension.showVertical


class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        binding.fragment = this
        mainViewModel.getStatisticsDisplay()
        mainViewModel.getScore()
        if (mainViewModel.getStatisticsDisplayEvent.value != null)
            countNumber(0, mainViewModel.getStatisticsDisplayEvent.value!!, binding.number, 1000)
        observeViewModel()
    }

    fun startBtnClick(view: View) {
        this.findNavController().navigate(R.id.action_mainFragment_to_womanNameFragment)
    }

    private fun initRecyclerView(){
        binding.scoreRecyclerView.adapter = ScoreRecyclerViewAdapter(mainViewModel)
        binding.scoreRecyclerView.showVertical(requireContext())
    }

    private fun observeViewModel() {
        mainViewModel.getStatisticsDisplayEvent.observe(this) {
            binding.number.text = ""
            countNumber(0, it, binding.number, 1000)
        }

        mainViewModel.getScoreEvent.observe(this){
            initRecyclerView()
        }
    }
}