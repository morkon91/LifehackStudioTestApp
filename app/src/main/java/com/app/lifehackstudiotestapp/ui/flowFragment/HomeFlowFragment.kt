package com.app.lifehackstudiotestapp.ui.flowFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.app.lifehackstudiotestapp.App
import com.app.lifehackstudiotestapp.R
import com.app.lifehackstudiotestapp.databinding.FragmentFlowHomeBinding
import com.app.lifehackstudiotestapp.di.components.CompaniesComponent
import com.app.lifehackstudiotestapp.ui.fragment.CompaniesListFragment
import com.app.lifehackstudiotestapp.ui.base.BaseFlowFragment
import com.app.lifehackstudiotestapp.ui.fragment.CompanyDetailsFragment

class HomeFlowFragment : BaseFlowFragment(R.layout.fragment_flow_home) {

    companion object {
        fun createInstance() = HomeFlowFragment()
    }

    private lateinit var binding: FragmentFlowHomeBinding
    private var startFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            startFragment = CompaniesListFragment.createInstance()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFlowHomeBinding.bind(view)

        startFragment?.let {
            openFragment(it)
            startFragment = null
        }
    }


    override fun initComponent() {
        App.componentStorage.getOrCreateComponent<CompaniesComponent>()
    }

    override fun releaseComponent() {
        App.componentStorage.releaseComponent<CompaniesComponent>()
    }

    override fun openFragment(fragment: Fragment) {
        when (fragment) {
            is CompaniesListFragment -> replaceFragment(fragment, false)
            is CompanyDetailsFragment -> replaceFragment(fragment, true)
        }
    }

}