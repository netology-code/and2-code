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
import ru.netology.nmedia.viewmodel.LoginEffect
import ru.netology.nmedia.viewmodel.LoginIntent
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
            viewModel.sendIntent(LoginIntent.SetLogin(it?.toString().orEmpty()))
        }

        binding.passwordEditText.doAfterTextChanged {
            viewModel.sendIntent(LoginIntent.SetPassword(it?.toString().orEmpty()))
        }

        binding.login.setOnClickListener {
            viewModel.sendIntent(LoginIntent.Login)
        }
    }

    private fun bindToViewModel(binding: ActivityLoginBinding) {
        viewModel.state.onEach { state ->
            binding.loginInput.error = state.loginError.toString(this)
            binding.passwordInput.error = state.passwordError.toString(this)
        }
            .launchIn(lifecycleScope)

        viewModel.effects.onEach { effect ->
            if (effect is LoginEffect.Success) {
                showSuccess()
            }
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
