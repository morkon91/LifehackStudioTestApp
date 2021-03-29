package com.app.lifehackstudiotestapp.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CompanyBriefData(

    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("img")
    @Expose
    var img: String
)