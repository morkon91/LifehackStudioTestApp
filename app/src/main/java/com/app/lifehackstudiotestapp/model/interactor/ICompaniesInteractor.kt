package com.app.lifehackstudiotestapp.model.interactor

import com.app.lifehackstudiotestapp.model.entity.CompanyBriefData
import com.app.lifehackstudiotestapp.model.entity.CompanyData
import io.reactivex.Single

interface ICompaniesInteractor {

    fun getAllCompanies(): Single<List<CompanyBriefData>>

    fun getCompanyDetailsById(companyId: String): Single<List<CompanyData>>
}