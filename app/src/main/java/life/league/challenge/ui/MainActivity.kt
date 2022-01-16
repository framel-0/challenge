package life.league.challenge.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.launch
import life.league.challenge.R
import life.league.challenge.data.models.Post
import life.league.challenge.data.models.User
import life.league.challenge.databinding.ActivityMainBinding
import life.league.challenge.internal.Result
import life.league.challenge.ui.base.ScopedActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : ScopedActivity(), KodeinAware {

    override val kodein by closestKodein()

    private lateinit var viewModel: MainViewModel
    private val viewModelFactory: MainViewModelFactory by instance()

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Posts")

        val progressBar = binding.progressBar

        auth()

        viewModel.loginResult.observe(this,
            Observer { loginResult ->
                loginResult ?: return@Observer

                if (loginResult is Result.Success) {
                    val account = loginResult.data

                    viewModel.updateKey(account.apiKey ?: "")
                    Log.i(TAG, account.apiKey ?: "")

                    getUsers()
                } else {
                    progressBar.visibility = View.GONE
                    showFailed(R.string.auth_failure_msg)
                }

            })

        viewModel.usersResult.observe(this,
            Observer { usersResult ->
                usersResult ?: return@Observer

                if (usersResult is Result.Success) {
                    val users = usersResult.data

                    viewModel.updateUsers(users)

                    getPosts()
                } else {
                    progressBar.visibility = View.GONE
                    showFailed(R.string.user_post_error_msg)
                }

            })

        viewModel.postsResult.observe(this,
            Observer { postsResult ->
                postsResult ?: return@Observer

                if (postsResult is Result.Success) {
                    val posts = postsResult.data
                    viewModel.updatePosts(posts)
                    progressBar.visibility = View.GONE
                } else {
                    progressBar.visibility = View.GONE
                    showFailed(R.string.user_post_error_msg)
                }

            })

        viewModel.posts.observe(this,
            { posts ->
                val users = viewModel.users.value
                if (posts != null && users != null)
                    setupPostRecyclerView(posts, users)
            })

        binding.buttonRefresh.setOnClickListener {
            auth()
        }


    }

    private fun showFailed(@StringRes errorString: Int) {
        binding.groupRefresh.visibility = View.VISIBLE
        binding.textMessage.text = getString(errorString)
    }

    private fun auth() = launch {
        viewModel.login.await()
    }

    private fun getUsers() = launch {
        viewModel.getUsers.await()
    }

    private fun getPosts() = launch {
        viewModel.getPosts.await()
    }

    private fun setupPostRecyclerView(posts: ArrayList<Post>, users: ArrayList<User>) {
        val recyclerView = binding.listPost
        val postRecyclerAdapter = PostListAdapter(::onPostClick)
        recyclerView.adapter = postRecyclerAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        postRecyclerAdapter.setPosts(posts, users)
    }

    private fun onPostClick(post: Post) {
        val text = "${post.title} clicked"
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

}