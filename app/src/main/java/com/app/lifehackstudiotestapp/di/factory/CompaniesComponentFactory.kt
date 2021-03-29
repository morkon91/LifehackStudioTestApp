package com.app.lifehackstudiotestapp.di.factory

import com.app.lifehackstudiotestapp.di.components.CompaniesComponent
import com.app.lifehackstudiotestapp.di.components.CoreComponent
import com.app.lifehackstudiotestapp.di.components.DaggerCompaniesComponent
import com.app.lifehackstudiotestapp.di.modules.CompaniesModule
import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage
import studio.inprogress.componentstorage.componentstorage.core.factory.IComponentFactory

class CompaniesComponentFactory : IComponentFactory<CompaniesComponent> {
    override fun create(componentStorage: ComponentStorage): CompaniesComponent {
        return DaggerCompaniesComponent.builder()
            .companiesModule(CompaniesModule())
            .coreComponent(componentStorage.getOrCreateComponent())
            .build()
    }

    override fun getName(): String = CompaniesComponent::class.java.simpleName

    override fun isReleasable(): Boolean = true
}