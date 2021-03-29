package com.app.lifehackstudiotestapp.ui.item

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import com.app.lifehackstudiotestapp.R
import com.app.lifehackstudiotestapp.databinding.ItemCompanyBinding
import com.app.lifehackstudiotestapp.model.entity.CompanyBriefData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class CompanyItem(var company: CompanyBriefData) : AbstractBindingItem<ItemCompanyBinding>() {

    override val type: Int
        get() = R.id.itemCompany


    override fun bindView(binding: ItemCompanyBinding, payloads: List<Any>) {
        binding.companyNameItemTextView.text = company.name

        Glide.with(binding.root.context)
            .asBitmap()
            .load("https://lifehack.studio/test_task/${company.img}")
            .placeholder(R.drawable.ic_baseline_error_outline_24)
            .apply(RequestOptions().centerCrop())
            .into(binding.imageCompany)
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemCompanyBinding {
        return ItemCompanyBinding.inflate(inflater, parent, false)
    }
}
