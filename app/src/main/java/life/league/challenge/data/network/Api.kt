package life.league.challenge.data.network

import life.league.challenge.data.network.response.LoginResponse
import life.league.challenge.data.network.response.PostResponse
import life.league.challenge.data.network.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Retrofit API interface definition using coroutines. Feel free to change this implementation to
 * suit your chosen architecture pattern and concurrency tools
 */
interface Api {

    @GET("login")
    suspend fun login(@Header("Authorization") credentials: String?): LoginResponse

    @GET("users")
    suspend fun users(@Header("x-access-token") apiKey: String): UserResponse

    @GET("posts")
    suspend fun posts(@Header("x-access-token") apiKey: String): PostResponse

}
