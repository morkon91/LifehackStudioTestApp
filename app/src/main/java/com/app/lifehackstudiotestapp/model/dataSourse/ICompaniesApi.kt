package com.app.lifehackstudiotestapp.model.dataSourse

import com.app.lifehackstudiotestapp.model.entity.CompanyBriefData
import com.app.lifehackstudiotestapp.model.entity.CompanyData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ICompaniesApi {

    @GET("/test_task/test.php")
    fun fetchAllCompanies(): Single<List<CompanyBriefData>>

    @GET("/test_task/test.php")
    fun fetchCompanyDetailsById(@Query("id") companyId: String): Single<List<CompanyData>>
}