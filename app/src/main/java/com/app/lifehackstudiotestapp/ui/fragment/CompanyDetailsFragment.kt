package com.app.lifehackstudiotestapp.ui.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.lifehackstudiotestapp.R
import com.app.lifehackstudiotestapp.databinding.FragmentCompanyDetailsBinding
import com.app.lifehackstudiotestapp.di.components.CompanyDetailsUIComponent
import com.app.lifehackstudiotestapp.ui.base.BaseFragment
import com.app.lifehackstudiotestapp.utils.resolveVisibility
import com.app.lifehackstudiotestapp.viewModel.CompanyDetailsViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import javax.inject.Inject

class CompanyDetailsFragment :
    BaseFragment(R.layout.fragment_company_details),
    Observer<CompanyDetailsViewModel.GetCompanyDetailsResult> {

    companion object {
        const val KEY_COMPANY = "companyId"
        fun createInstance(companyId: String): Fragment {
            val fragment = CompanyDetailsFragment()
            val bundle = Bundle()
            bundle.putString(KEY_COMPANY, companyId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentCompanyDetailsBinding

    private lateinit var companyId: String

    @Inject
    lateinit var companiesViewModelFactory: ViewModelProvider.Factory

    private lateinit var companyDetailsViewModel: CompanyDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        companyId = requireArguments().get(KEY_COMPANY) as String
        companyDetailsViewModel = ViewModelProvider(this, companiesViewModelFactory)
            .get(CompanyDetailsViewModel::class.java)
        companyDetailsViewModel.initialize(companyId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCompanyDetailsBinding.bind(view)
        companyDetailsViewModel.companyDetailsLiveData.observe(viewLifecycleOwner, this)

    }

    override fun initComponent() {
        componentStorage.getOrCreateComponent<CompanyDetailsUIComponent>().inject(this)
    }

    override fun releaseComponent() {
        componentStorage.releaseComponent<CompanyDetailsUIComponent>()
    }

    override fun onChanged(result: CompanyDetailsViewModel.GetCompanyDetailsResult) {
        when (result) {
            CompanyDetailsViewModel.GetCompanyDetailsResult.Progress -> {
//                showToast("Загрузка...")
            }
            is CompanyDetailsViewModel.GetCompanyDetailsResult.Success -> {
                showCompanyDetails(result)
            }
            is CompanyDetailsViewModel.GetCompanyDetailsResult.Error -> {
                showAlertDialog(result)
            }
        }
    }

    private fun showCompanyDetails(result: CompanyDetailsViewModel.GetCompanyDetailsResult.Success) {
        binding.companyNameTextView.text = result.companyData[0].name
        binding.descriptionTextView.text = result.companyData[0].description

        Glide.with(binding.root.context)
            .load("https://lifehack.studio/test_task/${result.companyData[0].img}")
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.companyDetailsImage.setImageResource(R.drawable.ic_baseline_error_outline_24)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.companyDetailsImage.setImageDrawable(resource)
                    return true
                }

            })
            .apply(RequestOptions().centerCrop())
            .into(binding.companyDetailsImage)

        binding.locationLinearLayout.resolveVisibility(
            !result.companyData[0].lat.equals(0.0F) &&
                    !result.companyData[0].lon.equals(0.0F)
        )

        binding.companyLocationTextView.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("geo:${result.companyData[0].lat},${result.companyData[0].lon}")
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                showToast("Нет подходящего приложения для просмотра геопозиции")
            }
        }

        binding.companySiteTextView.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("http://${result.companyData[0].www}")
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                showToast("Нет подходящего приложения для просмотра геопозиции")
            }
        }

        binding.phoneLinearLayout.resolveVisibility(result.companyData[0].phone != "")
        binding.siteLinearLayout.resolveVisibility(result.companyData[0].www != "")
        binding.companyPhoneTextView.text = result.companyData[0].phone
        binding.companySiteTextView.text = result.companyData[0].www

        binding.companyFragmentLinearLayout.visibility = View.VISIBLE
    }

    private fun showAlertDialog(result: CompanyDetailsViewModel.GetCompanyDetailsResult.Error) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Ошибка!")
        alertDialogBuilder.setMessage("Неверный ответ от сервера \n${result.throwable.message}")
        alertDialogBuilder.setNeutralButton("OK") { _, _ ->
            getFlowNavigator()?.onBackPressed()
        }
        alertDialogBuilder.setCancelable(false)
        val dialog: AlertDialog = alertDialogBuilder.create()
        dialog.show()
    }

}