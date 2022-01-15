package life.league.challenge.data.models


import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)