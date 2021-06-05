package site.yoonsang.covidnow.model


import com.google.gson.annotations.SerializedName

data class Chungbuk(
    @SerializedName("countryName")
    val countryName: String,
    @SerializedName("death")
    val death: String,
    @SerializedName("newCase")
    val newCase: String,
    @SerializedName("newCcase")
    val newCcase: String,
    @SerializedName("newFcase")
    val newFcase: String,
    @SerializedName("percentage")
    val percentage: String,
    @SerializedName("recovered")
    val recovered: String,
    @SerializedName("totalCase")
    val totalCase: String
)