package com.pss.presentation.view

import com.pss.barlibrary.CustomBar.Companion.setTransparentBar
import com.pss.presentation.R
import com.pss.presentation.base.BaseActivity
import com.pss.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {


    override fun init() {
        setTransparentBar(this)
    }
}