package life.league.challenge.data.models


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar")
    val avatar: Avatar,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("website")
    val website: String
)