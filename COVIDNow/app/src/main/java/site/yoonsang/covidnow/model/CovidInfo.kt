package site.yoonsang.covidnow.model


import com.google.gson.annotations.SerializedName

data class CovidInfo(
    @SerializedName("caseCount")
    val caseCount: String,
    @SerializedName("casePercentage")
    val casePercentage: String,
    @SerializedName("checkingCounter")
    val checkingCounter: String,
    @SerializedName("checkingPercentage")
    val checkingPercentage: String,
    @SerializedName("city1n")
    val city1n: String,
    @SerializedName("city1p")
    val city1p: String,
    @SerializedName("city2n")
    val city2n: String,
    @SerializedName("city2p")
    val city2p: String,
    @SerializedName("city3n")
    val city3n: String,
    @SerializedName("city3p")
    val city3p: String,
    @SerializedName("city4n")
    val city4n: String,
    @SerializedName("city4p")
    val city4p: String,
    @SerializedName("city5n")
    val city5n: String,
    @SerializedName("city5p")
    val city5p: String,
    @SerializedName("deathPercentage")
    val deathPercentage: Double,
    @SerializedName("notcaseCount")
    val notcaseCount: String,
    @SerializedName("notcasePercentage")
    val notcasePercentage: String,
    @SerializedName("NowCase")
    val nowCase: String,
    @SerializedName("recoveredPercentage")
    val recoveredPercentage: Double,
    @SerializedName("resultCode")
    val resultCode: String,
    @SerializedName("resultMessage")
    val resultMessage: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("TodayDeath")
    val todayDeath: String,
    @SerializedName("TodayRecovered")
    val todayRecovered: String,
    @SerializedName("TotalCase")
    val totalCase: String,
    @SerializedName("TotalCaseBefore")
    val totalCaseBefore: String,
    @SerializedName("TotalChecking")
    val totalChecking: String,
    @SerializedName("TotalDeath")
    val totalDeath: String,
    @SerializedName("TotalRecovered")
    val totalRecovered: String,
    @SerializedName("updateTime")
    val updateTime: String
)