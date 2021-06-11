package site.yoonsang.covidnow.model


import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("documents")
    val documents: List<Document>,
    @SerializedName("meta")
    val meta: Meta
)