package com.app.lifehackstudiotestapp.di.factory

import com.app.lifehackstudiotestapp.di.components.CompanyDetailsUIComponent
import com.app.lifehackstudiotestapp.di.components.DaggerCompanyDetailsUIComponent
import com.app.lifehackstudiotestapp.di.modules.CompanyDetailsUiModule
import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage
import studio.inprogress.componentstorage.componentstorage.core.factory.IComponentFactory

class CompanyDetailsUiComponentFactory : IComponentFactory<CompanyDetailsUIComponent> {

    override fun create(componentStorage: ComponentStorage): CompanyDetailsUIComponent {
        return DaggerCompanyDetailsUIComponent.builder()
            .companyDetailsUiModule(CompanyDetailsUiModule())
            .companiesComponent(componentStorage.getOrCreateComponent())
            .build()
    }

    override fun getName(): String = CompanyDetailsUIComponent::class.java.simpleName

    override fun isReleasable(): Boolean = true
}