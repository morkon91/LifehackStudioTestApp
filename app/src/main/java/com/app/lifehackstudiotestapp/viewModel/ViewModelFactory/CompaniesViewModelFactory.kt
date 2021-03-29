package com.app.lifehackstudiotestapp.viewModel.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.lifehackstudiotestapp.model.interactor.ICompaniesInteractor
import com.app.lifehackstudiotestapp.viewModel.CompaniesViewModel
import com.app.lifehackstudiotestapp.viewModel.CompanyDetailsViewModel

@Suppress("UNCHECKED_CAST")
class CompaniesViewModelFactory(
    private val interactor: ICompaniesInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(CompaniesViewModel::class.java) ->
                CompaniesViewModel(interactor) as T
            modelClass.isAssignableFrom(CompanyDetailsViewModel::class.java) ->
                CompanyDetailsViewModel(interactor) as T
            else -> throw IllegalArgumentException()
        }
    }
}