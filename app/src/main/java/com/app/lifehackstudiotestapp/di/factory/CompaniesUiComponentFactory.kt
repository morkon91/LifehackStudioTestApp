package com.app.lifehackstudiotestapp.di.factory

import com.app.lifehackstudiotestapp.di.components.CompaniesUiComponent
import com.app.lifehackstudiotestapp.di.components.DaggerCompaniesUiComponent
import com.app.lifehackstudiotestapp.di.modules.CompaniesUiModule
import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage
import studio.inprogress.componentstorage.componentstorage.core.factory.IComponentFactory

class CompaniesUiComponentFactory : IComponentFactory<CompaniesUiComponent> {

    override fun create(componentStorage: ComponentStorage): CompaniesUiComponent {
        return DaggerCompaniesUiComponent.builder()
            .companiesUiModule(CompaniesUiModule())
            .companiesComponent(componentStorage.getOrCreateComponent())
            .build()
    }

    override fun getName(): String = CompaniesUiComponent::class.java.simpleName

    override fun isReleasable(): Boolean = true
}