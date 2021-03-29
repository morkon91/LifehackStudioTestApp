package com.app.lifehackstudiotestapp.model.repository.impl

import com.app.lifehackstudiotestapp.model.dataSourse.ICompaniesApi
import com.app.lifehackstudiotestapp.model.entity.CompanyBriefData
import com.app.lifehackstudiotestapp.model.entity.CompanyData
import com.app.lifehackstudiotestapp.model.repository.ICompaniesRepository
import io.reactivex.Single

class CompaniesRepository(private val companiesApi: ICompaniesApi) : ICompaniesRepository {
    override fun getAllCompanies(): Single<List<CompanyBriefData>> {
        return companiesApi.fetchAllCompanies()
    }

    override fun getCompanyDetailsById(companyId: String): Single<List<CompanyData>> {
        return companiesApi.fetchCompanyDetailsById(companyId)
    }
}