package com.pss.presentation.view

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pss.domain.utils.FirebaseState
import com.pss.presentation.R
import com.pss.presentation.base.BaseFragment
import com.pss.presentation.databinding.FragmentResultBinding
import com.pss.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        binding.fragment = this
        initResult()
        saveScore()
    }

    private fun saveScore() = with(mainViewModel.apiCallResult){
        mainViewModel.setScore(this.sname, this.fname, this.percentage, nowTime())
    }

    private fun nowTime(): String = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분", Locale("ko", "KR")).format(
        Date(System.currentTimeMillis())
    )

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

    private fun saveStatistics() = lifecycleScope.launch {
        with(mainViewModel.getStatistics()){
            when(this.state){
                FirebaseState.SUCCESS -> if (mainViewModel.setStatistics(this.result.toString().toInt() + 1).state == FirebaseState.FAILURE) error()
                FirebaseState.FAILURE -> error()
            }
        }
    }

    private fun error() = shortShowToast("통계를 저장하는데 오류가 발생했습니다")

    private fun setLoveMsgTxt(msg: String) = binding.loveTxt.setText(msg)

    fun clickBackMainBtn(view: View){
        this.findNavController().navigate(R.id.action_resultFragment_to_mainFragment)
    }
}