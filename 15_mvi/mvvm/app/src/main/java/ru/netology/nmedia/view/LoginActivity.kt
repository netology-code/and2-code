package ru.netology.nmedia.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityLoginBinding
import ru.netology.nmedia.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applyInsets(binding)

        bindToViewModel(binding)

        binding.loginEditText.doAfterTextChanged {
            viewModel.setLogin(it?.toString())
        }

        binding.passwordEditText.doAfterTextChanged {
            viewModel.setPassword(it?.toString())
        }

        binding.login.setOnClickListener {
            viewModel.login()
        }
    }

    private fun bindToViewModel(binding: ActivityLoginBinding) {
        viewModel.loginError.onEach {
            binding.loginInput.error = it.toString(this)
        }
            .launchIn(lifecycleScope)

        viewModel.passwordError.onEach {
            binding.passwordInput.error = it.toString(this)
        }
            .launchIn(lifecycleScope)

        viewModel.loginSuccess.onEach {
            showSuccess()
        }
            .launchIn(lifecycleScope)
    }

    private fun showSuccess() {
        Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show()
    }

    private fun applyInsets(binding: ActivityLoginBinding) {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val offset = resources.getDimensionPixelSize(R.dimen.common_spacing)
            v.setPadding(
                systemBars.left + offset,
                systemBars.top + offset,
                systemBars.right + offset,
                systemBars.bottom + offset,
            )
            insets
        }
    }
}
