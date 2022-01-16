package life.league.challenge.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import life.league.challenge.data.models.Post
import life.league.challenge.data.models.User
import life.league.challenge.databinding.ListItemPostBinding

class PostListAdapter(private val onPostClick: (Post) -> Unit) :
    RecyclerView.Adapter<PostViewHolder>() {

    private var _posts: ArrayList<Post> = arrayListOf()
    private var _users: ArrayList<User> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemPostBinding.inflate(layoutInflater, parent, false)
        return PostViewHolder(binding, onPostClick)
    }

    fun setPosts(posts: ArrayList<Post>, users: ArrayList<User>) {
        _posts = posts
        _users = users
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = _posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = _posts[position]
        val user = _users.single { s -> s.id == post.userId }
        holder.bind(post, user)
    }

}