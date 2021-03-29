package com.app.lifehackstudiotestapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.lifehackstudiotestapp.model.entity.CompanyData
import com.app.lifehackstudiotestapp.model.interactor.ICompaniesInteractor
import com.app.lifehackstudiotestapp.utils.applySingleSchedulers
import io.reactivex.disposables.Disposable

class CompanyDetailsViewModel(private val companiesInteractor: ICompaniesInteractor) : ViewModel() {

    val companyDetailsLiveData = MutableLiveData<GetCompanyDetailsResult>()

    private var companyDisposable: Disposable? = null

    private lateinit var companyId: String

    fun initialize(companyId: String) {
        this.companyId = companyId
        getCompanyDetails()
    }

    fun getCompanyDetails() {
        companyDisposable = companiesInteractor.getCompanyDetailsById(companyId)
            .applySingleSchedulers()
            .doOnSubscribe {
                companyDetailsLiveData.value = GetCompanyDetailsResult.Progress
            }.subscribe({
                companyDetailsLiveData.value = GetCompanyDetailsResult.Success(it)
            }, {
                companyDetailsLiveData.value = GetCompanyDetailsResult.Error(it)
            })
    }

    override fun onCleared() {
        super.onCleared()
        companyDisposable?.dispose()
    }

    sealed class GetCompanyDetailsResult {
        class Success(val companyData: List<CompanyData>) : GetCompanyDetailsResult()
        class Error(val throwable: Throwable) : GetCompanyDetailsResult()
        object Progress : GetCompanyDetailsResult()
    }
}