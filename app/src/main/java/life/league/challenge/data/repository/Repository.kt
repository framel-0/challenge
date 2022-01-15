package life.league.challenge.data.repository

import life.league.challenge.data.datasource.NetworkDataSource

class Repository(private val networkDataSource: NetworkDataSource) {

    suspend fun login(username: String, password: String) =
        networkDataSource.auth(username, password)

    suspend fun getUsers(apiKey: String) = networkDataSource.fetchUsers(apiKey)

    suspend fun getPosts(apiKey: String) = networkDataSource.fetchPosts(apiKey)

}