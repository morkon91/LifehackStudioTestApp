package com.app.lifehackstudiotestapp.di.components

import com.app.lifehackstudiotestapp.di.modules.CompanyDetailsUiModule
import com.app.lifehackstudiotestapp.di.scopes.CompanyDetailsUiScope
import com.app.lifehackstudiotestapp.ui.fragment.CompanyDetailsFragment
import dagger.Component

@Component(
    dependencies = [CompaniesComponent::class],
    modules = [CompanyDetailsUiModule::class]
)
@CompanyDetailsUiScope
interface CompanyDetailsUIComponent {

    fun inject(fragment: CompanyDetailsFragment)
}