package com.app.lifehackstudiotestapp.di.modules

import com.app.lifehackstudiotestapp.di.scopes.CompaniesScope
import com.app.lifehackstudiotestapp.model.dataSourse.ICompaniesApi
import com.app.lifehackstudiotestapp.model.interactor.ICompaniesInteractor
import com.app.lifehackstudiotestapp.model.interactor.impl.CompaniesInteractor
import com.app.lifehackstudiotestapp.model.repository.ICompaniesRepository
import com.app.lifehackstudiotestapp.model.repository.impl.CompaniesRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CompaniesModule {

    @Provides
    @CompaniesScope
    fun provideCompaniesInteractor(repository: ICompaniesRepository): ICompaniesInteractor {
        return CompaniesInteractor(repository)
    }

    @Provides
    @CompaniesScope
    fun provideCompaniesRepository(companiesApi: ICompaniesApi): ICompaniesRepository {
        return CompaniesRepository(companiesApi)
    }

    @Provides
    @CompaniesScope
    fun provideCompaniesApi(retrofit: Retrofit): ICompaniesApi {
        return retrofit.create(ICompaniesApi::class.java)
    }
}