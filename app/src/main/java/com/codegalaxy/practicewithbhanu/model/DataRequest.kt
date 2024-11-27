package com.codegalaxy.practicewithbhanu.model

import com.google.gson.annotations.SerializedName

data class DataRequest(
    @SerializedName("name")
    val name : String,

    @SerializedName("data")
    val data : DataFromAPI
)

data class DataFromAPI(
    @SerializedName("year")
    val year : String,

    @SerializedName("price")
    val price : String,

    @SerializedName("CPU")
    val cpu : String,

    @SerializedName("Hard disk size")
    val hardDisk : String
)

