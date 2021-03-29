package com.app.lifehackstudiotestapp.model.interactor.impl

import com.app.lifehackstudiotestapp.model.entity.CompanyBriefData
import com.app.lifehackstudiotestapp.model.entity.CompanyData
import com.app.lifehackstudiotestapp.model.interactor.ICompaniesInteractor
import com.app.lifehackstudiotestapp.model.repository.ICompaniesRepository
import io.reactivex.Single

class CompaniesInteractor(private val repository: ICompaniesRepository) : ICompaniesInteractor {

    override fun getAllCompanies(): Single<List<CompanyBriefData>> {
        return repository.getAllCompanies()
    }

    override fun getCompanyDetailsById(companyId: String): Single<List<CompanyData>> {
        return repository.getCompanyDetailsById(companyId)
    }


}