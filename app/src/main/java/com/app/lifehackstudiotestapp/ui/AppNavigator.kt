package com.app.lifehackstudiotestapp.ui

import androidx.fragment.app.FragmentManager
import com.app.lifehackstudiotestapp.ui.base.INavigator
import com.app.lifehackstudiotestapp.ui.flowFragment.HomeFlowFragment

class AppNavigator(private val fragmentManager: FragmentManager) : INavigator {
    override fun openHomeFlowFragment() {
        fragmentManager.beginTransaction()
            .replace((android.R.id.content), HomeFlowFragment.createInstance())
            .commit()
    }


}