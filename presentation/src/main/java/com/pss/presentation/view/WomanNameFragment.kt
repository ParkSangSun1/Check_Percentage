package com.pss.presentation.view

import android.text.TextUtils
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pss.presentation.R
import com.pss.presentation.base.BaseFragment
import com.pss.presentation.databinding.FragmentWomanNameBinding
import com.pss.presentation.viewmodel.MainViewModel

class WomanNameFragment : BaseFragment<FragmentWomanNameBinding>(R.layout.fragment_woman_name) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        binding.fragment = this
    }

    fun nextBtnClick(view: View){
        if (TextUtils.isEmpty(binding.editTxt.text.toString())) shortShowToast("이름을 입력해 주세요")
        else {
            mainViewModel.womanNameResult = binding.editTxt.text.toString()
            this.findNavController().navigate(R.id.action_womanNameFragment_to_manNameFragment)
        }
    }
}