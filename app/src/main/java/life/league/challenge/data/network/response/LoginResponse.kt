package life.league.challenge.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("api_key") val apiKey: String? = null)
