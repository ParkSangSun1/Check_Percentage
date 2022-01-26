package com.pss.presentation.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pss.domain.utils.ErrorType
import com.pss.presentation.R
import com.pss.presentation.base.BaseFragment
import com.pss.presentation.databinding.FragmentManNameBinding
import com.pss.presentation.viewmode.MainViewModel

class ManNameFragment : BaseFragment<FragmentManNameBinding>(R.layout.fragment_man_name) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        binding.fragment = this
        observeViewModel()
    }

    fun nextBtnClick(view: View){
        if (TextUtils.isEmpty(binding.editTxt.text.toString())) shortShowToast("이름을 입력해 주세요")
        else {
            mainViewModel.manNameResult = binding.editTxt.text.toString()
            binding.progressBar.visibility = View.VISIBLE
            mainViewModel.checkLoveCalculator("love-calculator.p.rapidapi.com","6f15755a03msh1e9952813104629p1ae459jsn2facf2b12aaa",mainViewModel.manNameResult,mainViewModel.womanNameResult)
        }
    }

    private fun observeViewModel(){
        mainViewModel.successEvent.observe(this,{
            binding.progressBar.visibility = View.INVISIBLE
            this.findNavController().navigate(R.id.action_manNameFragment_to_resultFragment)
        })

        mainViewModel.errorType.observe(this,{
            when(it){
                ErrorType.NETWORK ->longShowToast("네트워크 오류가 발생했습니다")
                ErrorType.SESSION_EXPIRED ->longShowToast("세션이 만료되었습니다")
                ErrorType.TIMEOUT ->longShowToast("서버 호출 시간이 초과되었습니다")
                ErrorType.UNKNOWN ->longShowToast("예기치 못한 오류가 발생했습니다")
            }
            binding.progressBar.visibility = View.INVISIBLE
        })
    }
}