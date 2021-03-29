package com.app.lifehackstudiotestapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.lifehackstudiotestapp.App
import com.app.lifehackstudiotestapp.R
import com.app.lifehackstudiotestapp.databinding.FragmentCompaniesListBinding
import com.app.lifehackstudiotestapp.di.components.CompaniesUiComponent
import com.app.lifehackstudiotestapp.ui.base.BaseFragment
import com.app.lifehackstudiotestapp.ui.item.CompanyItem
import com.app.lifehackstudiotestapp.viewModel.CompaniesViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import javax.inject.Inject

class CompaniesListFragment :
    BaseFragment(R.layout.fragment_companies_list),
    Observer<CompaniesViewModel.GetCompaniesResult> {

    companion object {
        fun createInstance() = CompaniesListFragment()
    }

    private lateinit var binding: FragmentCompaniesListBinding
    private var itemAdapter = ItemAdapter<CompanyItem>()
    private var fastAdapter = FastAdapter.with(itemAdapter)

    @Inject
    lateinit var companiesViewModelFactory: ViewModelProvider.Factory

    private lateinit var companiesViewModel: CompaniesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        companiesViewModel = ViewModelProvider(this, companiesViewModelFactory)
            .get(CompaniesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCompaniesListBinding.bind(view)
        companiesViewModel.companiesLiveData.observe(viewLifecycleOwner, this)
        binding.companiesRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.companiesRecyclerView.adapter = fastAdapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            companiesViewModel.refreshCompaniesList()
        }
        fastAdapter.onClickListener = { _, _, item, _ ->
            getFlowNavigator()?.openFragment(CompanyDetailsFragment.createInstance(item.company.id))
            false
        }

    }

    override fun initComponent() {
        App.componentStorage.getOrCreateComponent<CompaniesUiComponent>().inject(this)
    }

    override fun releaseComponent() {
        App.componentStorage.releaseComponent<CompaniesUiComponent>()
    }

    override fun onChanged(result: CompaniesViewModel.GetCompaniesResult?) {
        when (result) {
            CompaniesViewModel.GetCompaniesResult.Progress -> {
//                showToast("Загрузка...")
            }
            is CompaniesViewModel.GetCompaniesResult.Success -> {
                itemAdapter.clear()
                val list = mutableListOf<CompanyItem>()
                result.companiesList.forEach { companyBriefData ->
                    list.add(CompanyItem(companyBriefData))
                }
                itemAdapter.add(list)
                binding.swipeRefreshLayout.isRefreshing = false
            }
            is CompaniesViewModel.GetCompaniesResult.Error -> {
                showToast("${result.throwable.message ?: "Неизвестная ошибка"}")
                binding.swipeRefreshLayout.isRefreshing = false
            }

        }
    }
}