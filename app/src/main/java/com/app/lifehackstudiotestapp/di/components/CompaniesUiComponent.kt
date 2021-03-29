package com.app.lifehackstudiotestapp.di.components

import com.app.lifehackstudiotestapp.di.modules.CompaniesUiModule
import com.app.lifehackstudiotestapp.di.scopes.CompaniesUiScope
import com.app.lifehackstudiotestapp.ui.fragment.CompaniesListFragment
import dagger.Component

@Component(dependencies = [CompaniesComponent::class], modules = [CompaniesUiModule::class])
@CompaniesUiScope
interface CompaniesUiComponent {

    fun inject(fragment: CompaniesListFragment)
}