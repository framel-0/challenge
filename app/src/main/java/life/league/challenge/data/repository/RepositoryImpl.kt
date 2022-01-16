package life.league.challenge.data.repository

import life.league.challenge.data.datasource.NetworkDataSource
import life.league.challenge.data.network.response.LoginResponse
import life.league.challenge.data.network.response.PostResponse
import life.league.challenge.data.network.response.UserResponse
import life.league.challenge.internal.Result

class RepositoryImpl(private val networkDataSource: NetworkDataSource) : Repository {

    override suspend fun login(username: String, password: String): Result<LoginResponse> {
        return networkDataSource.auth(username, password)
    }

    override suspend fun getUsers(apiKey: String): Result<UserResponse> {
        return networkDataSource.fetchUsers(apiKey)
    }

    override suspend fun getPosts(apiKey: String): Result<PostResponse> {
        return networkDataSource.fetchPosts(apiKey)
    }
}