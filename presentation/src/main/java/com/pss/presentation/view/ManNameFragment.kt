package com.pss.presentation.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pss.presentation.R
import com.pss.presentation.base.BaseFragment
import com.pss.presentation.databinding.FragmentManNameBinding

class ManNameFragment : BaseFragment<FragmentManNameBinding>(R.layout.fragment_man_name) {

    override fun init() {
        binding.fragment = this
    }

    fun nextBtnClick(view: View){
        if (TextUtils.isEmpty(binding.editTxt.text.toString())) shortShowToast("이름을 입력해 주세요")
        else this.findNavController().navigate(R.id.action_manNameFragment_to_resultFragment)
    }
}