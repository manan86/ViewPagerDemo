package com.codegalaxy.practicewithbhanu.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("CPU model")
    val cpu: String,

    @SerializedName("Hard disk size")
    val hardDisk : String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("year")
    val year: Int
)
