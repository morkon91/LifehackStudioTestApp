package com.app.lifehackstudiotestapp.di.modules

import androidx.lifecycle.ViewModelProvider
import com.app.lifehackstudiotestapp.di.scopes.CompaniesUiScope
import com.app.lifehackstudiotestapp.di.scopes.CompanyDetailsUiScope
import com.app.lifehackstudiotestapp.model.interactor.ICompaniesInteractor
import com.app.lifehackstudiotestapp.viewModel.ViewModelFactory.CompaniesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CompanyDetailsUiModule {

    @Provides
    @CompanyDetailsUiScope
    fun provideCompaniesViewModelFactory(
        interactor: ICompaniesInteractor
    ): ViewModelProvider.Factory {

        return CompaniesViewModelFactory(interactor)
    }
}