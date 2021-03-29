package com.app.lifehackstudiotestapp.di.components

import android.content.Context
import com.app.lifehackstudiotestapp.di.modules.CompaniesModule
import com.app.lifehackstudiotestapp.di.scopes.CompaniesScope
import com.app.lifehackstudiotestapp.model.interactor.ICompaniesInteractor
import com.app.lifehackstudiotestapp.model.repository.ICompaniesRepository
import dagger.Component


@Component(dependencies = [CoreComponent::class], modules = [CompaniesModule::class])
@CompaniesScope
interface CompaniesComponent {

    fun provideContext(): Context
    fun provideMoviesInteractor(): ICompaniesInteractor
    fun provideMoviesRepository(): ICompaniesRepository

}