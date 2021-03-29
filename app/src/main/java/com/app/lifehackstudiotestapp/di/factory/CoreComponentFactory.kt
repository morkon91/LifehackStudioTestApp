package com.app.lifehackstudiotestapp.di.factory

import android.content.Context
import com.app.lifehackstudiotestapp.di.components.CoreComponent
import com.app.lifehackstudiotestapp.di.components.DaggerCoreComponent
import com.app.lifehackstudiotestapp.di.modules.AppModule
import com.app.lifehackstudiotestapp.di.modules.NetworkModule
import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage
import studio.inprogress.componentstorage.componentstorage.core.factory.IComponentFactory

class CoreComponentFactory(private val appContext: Context) : IComponentFactory<CoreComponent> {
    override fun create(componentStorage: ComponentStorage): CoreComponent {
        return DaggerCoreComponent.builder()
            .appModule(AppModule(appContext))
            .networkModule(NetworkModule())
            .build()
    }

    override fun getName(): String =CoreComponent::class.java.simpleName

    override fun isReleasable(): Boolean = false
}