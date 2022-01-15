package life.league.challenge.data.datasource

import android.util.Log
import life.league.challenge.data.models.Account
import life.league.challenge.data.network.Service
import life.league.challenge.data.network.response.PostResponse
import life.league.challenge.data.network.response.UserResponse
import life.league.challenge.internal.Result

class NetworkDataSource {

    suspend fun auth(username: String, password: String): Result<Account> {
        return try {

            val response = Service.api.login(
                "Basic " + android.util.Base64.encodeToString(
                    "$username:$password".toByteArray(),
                    android.util.Base64.NO_WRAP
                )
            )
            Result.Success(response)

        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            Result.Error(e)
        }

    }

    suspend fun fetchUsers(key: String): Result<UserResponse> {
        return try {

            val response = Service.api.users(key)
            Result.Success(response)

        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            Result.Error(e)
        }

    }

    suspend fun fetchPosts(key: String): Result<PostResponse> {
        return try {

            val response = Service.api.posts(key)
            Result.Success(response)

        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            Result.Error(e)
        }

    }

    companion object {
        private const val TAG: String = "NetworkDataSource"
    }

}