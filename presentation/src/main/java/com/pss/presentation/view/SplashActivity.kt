package com.pss.presentation.view

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.pss.barlibrary.CustomBar.Companion.setTransparentBar
import com.pss.domain.utils.FirebaseState
import com.pss.presentation.R
import com.pss.presentation.base.BaseActivity
import com.pss.presentation.databinding.ActivitySplashBinding
import com.pss.presentation.viewmodel.SplashViewModel
import com.pss.presentation.widget.extension.startActivityWithFinish
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private val splashViewModel by viewModels<SplashViewModel>()
    private val appVersion = "1.0.0"

    override fun init() {
        setTransparentBar(this)
        checkAppVersion()
    }

    private fun checkAppVersion() = lifecycleScope.launch {
        with(splashViewModel.checkAppVersion()){
            if (this.state == FirebaseState.SUCCESS){
                if(appVersion == this.result) this@SplashActivity.startActivityWithFinish(this@SplashActivity, MainActivity::class.java)
                else longShowToast("앱 버전이 다릅니다!")
            }else shortShowToast("알수없는 오류가 발생했습니다")
        }
    }
}