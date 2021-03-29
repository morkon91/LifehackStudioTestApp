package com.app.lifehackstudiotestapp.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CompanyData(

    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("img")
    @Expose
    var img: String,

    @SerializedName("description")
    @Expose
    var description: String,

    @SerializedName("lat")
    @Expose
    var lat: Float,

    @SerializedName("lon")
    @Expose
    var lon: Float,

    @SerializedName("www")
    @Expose
    var www: String,

    @SerializedName("phone")
    @Expose
    var phone: String,
)