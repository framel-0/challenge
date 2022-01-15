package life.league.challenge.ui


import androidx.recyclerview.widget.RecyclerView
import life.league.challenge.R
import life.league.challenge.data.models.Post
import life.league.challenge.data.models.User
import life.league.challenge.databinding.ListItemPostBinding
import life.league.challenge.internal.GlideApp

class PostViewHolder(private val binding: ListItemPostBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(post: Post, user: User) {

        binding.textUsername.text = user.name

        binding.textTitle.text = post.title

        binding.textDescription.text = post.body

        GlideApp.with(itemView.context).load(user.avatar.thumbnail)
            .placeholder(R.drawable.avatar_placeholder).into(binding.imageAvatar)

    }


}