package life.league.challenge.data.datasource

import life.league.challenge.data.network.response.LoginResponse
import life.league.challenge.data.network.response.PostResponse
import life.league.challenge.data.network.response.UserResponse
import life.league.challenge.internal.Result

interface NetworkDataSource {

    suspend fun auth(username: String, password: String): Result<LoginResponse>

    suspend fun fetchUsers(key: String): Result<UserResponse>

    suspend fun fetchPosts(key: String): Result<PostResponse>

}