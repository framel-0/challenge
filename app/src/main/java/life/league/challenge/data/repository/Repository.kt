package life.league.challenge.data.repository

import life.league.challenge.data.network.response.LoginResponse
import life.league.challenge.data.network.response.PostResponse
import life.league.challenge.data.network.response.UserResponse
import life.league.challenge.internal.Result

interface Repository {

    suspend fun login(username: String, password: String): Result<LoginResponse>

    suspend fun getUsers(apiKey: String): Result<UserResponse>

    suspend fun getPosts(apiKey: String): Result<PostResponse>

}