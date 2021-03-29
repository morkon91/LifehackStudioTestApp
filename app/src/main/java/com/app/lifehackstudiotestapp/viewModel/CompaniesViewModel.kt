package com.app.lifehackstudiotestapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.lifehackstudiotestapp.model.entity.CompanyBriefData
import com.app.lifehackstudiotestapp.model.interactor.ICompaniesInteractor
import com.app.lifehackstudiotestapp.utils.applySingleSchedulers
import io.reactivex.disposables.Disposable

class CompaniesViewModel(private val companiesInteractor: ICompaniesInteractor) : ViewModel() {

    val companiesLiveData = MutableLiveData<GetCompaniesResult>()

    private var companiesDisposable: Disposable? = null

    init {
        refreshCompaniesList()
    }

    fun refreshCompaniesList() {
        companiesDisposable?.dispose()
        companiesDisposable = companiesInteractor.getAllCompanies()
            .applySingleSchedulers()
            .doOnSubscribe {
                companiesLiveData.value = GetCompaniesResult.Progress
            }
            .subscribe(
                { response ->
                    companiesLiveData.value = GetCompaniesResult.Success(response)
                },
                { throwable ->
                    companiesLiveData.value = GetCompaniesResult.Error(throwable)
                }
            )
    }


    override fun onCleared() {
        super.onCleared()
        companiesDisposable?.dispose()
    }

    sealed class GetCompaniesResult {
        class Success(val companiesList: List<CompanyBriefData>) : GetCompaniesResult()
        class Error(val throwable: Throwable) : GetCompaniesResult()
        object Progress : GetCompaniesResult()
    }
}