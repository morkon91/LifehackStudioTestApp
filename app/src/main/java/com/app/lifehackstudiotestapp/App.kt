package com.app.lifehackstudiotestapp

import android.app.Application
import android.util.Log
import com.app.lifehackstudiotestapp.di.factory.CompaniesComponentFactory
import com.app.lifehackstudiotestapp.di.factory.CompaniesUiComponentFactory
import com.app.lifehackstudiotestapp.di.factory.CompanyDetailsUiComponentFactory
import com.app.lifehackstudiotestapp.di.factory.CoreComponentFactory
import studio.inprogress.componentstorage.componentstorage.core.ComponentStorage

class App : Application() {

    companion object {
        lateinit var componentStorage: ComponentStorage
    }

    override fun onCreate() {
        super.onCreate()
        initComponentStorage()
    }

    private fun initComponentStorage() {
        componentStorage = ComponentStorage().apply {
            registerComponentFactory(
                CoreComponentFactory(this@App),
                CompaniesComponentFactory(),
                CompaniesUiComponentFactory(),
                CompanyDetailsUiComponentFactory()
            )
            withLogger {
                Log.d("ComponentStorage", it)
            }
        }
    }
}