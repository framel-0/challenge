package life.league.challenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import life.league.challenge.data.network.response.LoginResponse
import life.league.challenge.data.models.Post
import life.league.challenge.data.models.User
import life.league.challenge.data.network.response.PostResponse
import life.league.challenge.data.network.response.UserResponse
import life.league.challenge.data.repository.Repository
import life.league.challenge.internal.Result
import life.league.challenge.internal.lazyDeferred

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> = _loginResult

    private val _usersResult = MutableLiveData<Result<UserResponse>>()
    val usersResult: LiveData<Result<UserResponse>> = _usersResult

    private val _postsResult = MutableLiveData<Result<PostResponse>>()
    val postsResult: LiveData<Result<PostResponse>> = _postsResult

    private val _apiKey = MutableLiveData<String>()
    val apiKey: LiveData<String> = _apiKey

    private val _users = MutableLiveData<ArrayList<User>>()
    val users: LiveData<ArrayList<User>> = _users

    private val _posts = MutableLiveData<ArrayList<Post>>()
    val posts: LiveData<ArrayList<Post>> = _posts

    val login by lazyDeferred {
        val result = repository.login("John", "Doe")

        _loginResult.postValue(result)
    }

    val getUsers by lazyDeferred {
        val key = apiKey.value!!
        val result = repository.getUsers(key)

        _usersResult.postValue(result)
    }

    val getPosts by lazyDeferred {
        val key = apiKey.value!!
        val result = repository.getPosts(key)

        _postsResult.postValue(result)
    }


    fun updateKey(key: String) {
        _apiKey.postValue(key)
    }

    fun updateUsers(users: ArrayList<User>) {
        _users.postValue(users)
    }

    fun updatePosts(posts: ArrayList<Post>) {
        _posts.postValue(posts)
    }

}