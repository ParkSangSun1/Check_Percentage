package com.pss.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pss.domain.model.DomainLoveResponse
import com.pss.presentation.R
import com.pss.presentation.base.BaseFragment
import com.pss.presentation.databinding.FragmentResultBinding
import com.pss.presentation.viewmode.MainViewModel

class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        binding.fragment = this
        initResult()
    }

    private fun initResult() {
        binding.percentage.text = mainViewModel.apiCallResult.percentage.toString()
        when (mainViewModel.apiCallResult.percentage) {
            in 0..20 -> setLoveMsgTxt("조금 힘들어보여요")
            in 21..40 -> setLoveMsgTxt("노력해 보세요")
            in 41..70 -> setLoveMsgTxt("기대해도 좋겠는데요?")
            in 71..90 -> setLoveMsgTxt("일단 축하드려요")
            in 91..99 -> setLoveMsgTxt("와우.. 눈을 의심하고 있어요")
            100 -> {
                setLoveMsgTxt("완벽하네요, 축하드려요")
                saveStatistics()
            }
            else -> setLoveMsgTxt("알수없는 힘!!")
        }
        binding.backMainBtn.isEnabled = true
    }

    private fun saveStatistics() {
        mainViewModel.getStatistics()
            .addOnSuccessListener {
                if (it != null) mainViewModel.setStatistics(it.value.toString().toInt() + 1)
                    .addOnFailureListener {
                        error()
                    }
            }
            .addOnFailureListener {
                error()
            }
    }

    private fun error() = shortShowToast("통계를 저장하는데 오류가 발생했습니다")

    private fun setLoveMsgTxt(msg: String) = binding.loveTxt.setText(msg)

    fun clickBackMainBtn(view: View){
        this.findNavController().navigate(R.id.action_resultFragment_to_mainFragment)
    }
}