package com.app.lifehackstudiotestapp.ui

import android.os.Bundle
import com.app.lifehackstudiotestapp.R
import com.app.lifehackstudiotestapp.ui.base.BaseActivity
import com.app.lifehackstudiotestapp.ui.base.INavigator
import com.app.lifehackstudiotestapp.ui.base.INavigatorHolder

class AppActivity : BaseActivity(), INavigatorHolder {

    private lateinit var appNavigator: INavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appNavigator = AppNavigator(supportFragmentManager)
        appNavigator.openHomeFlowFragment()

    }

    override fun initComponents() {
        // no op
    }

    override fun releaseComponent() {
        // no op
    }

    override fun getNavigator(): INavigator {
        return appNavigator
    }
}