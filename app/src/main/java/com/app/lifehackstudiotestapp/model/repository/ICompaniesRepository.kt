package com.app.lifehackstudiotestapp.model.repository

import com.app.lifehackstudiotestapp.model.entity.CompanyBriefData
import com.app.lifehackstudiotestapp.model.entity.CompanyData
import io.reactivex.Single

interface ICompaniesRepository {

    fun getAllCompanies(): Single<List<CompanyBriefData>>

    fun getCompanyDetailsById(companyId: String): Single<List<CompanyData>>
}