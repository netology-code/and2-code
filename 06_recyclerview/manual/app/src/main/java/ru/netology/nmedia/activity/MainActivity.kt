package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { posts ->
            binding.container.removeAllViews()
            posts.map { post ->
                CardPostBinding.inflate(layoutInflater, binding.container, true).apply {
                    author.text = post.author
                    published.text = post.published
                    content.text = post.content
                    likeCount.text = post.likes.toString()
                    like.setImageResource(
                        if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                    )
                    like.setOnClickListener {
                        viewModel.likeById(post.id)
                    }
                }.root
            }
        }
    }
}
