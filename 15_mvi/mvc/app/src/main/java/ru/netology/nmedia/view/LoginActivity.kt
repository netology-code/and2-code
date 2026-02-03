package ru.netology.nmedia.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityLoginBinding
import ru.netology.nmedia.domain.validateLogin
import ru.netology.nmedia.domain.validatePassword

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applyInsets(binding)

        binding.loginEditText.doAfterTextChanged {
            binding.loginInput.error = null
        }

        binding.passwordEditText.doAfterTextChanged {
            binding.loginInput.error = null
        }

        binding.login.setOnClickListener {
            val loginError = validateLogin(binding.loginEditText.text)
            val passwordError = validatePassword(binding.passwordEditText.text)

            binding.loginInput.error = loginError.toString(this)
            binding.passwordInput.error = passwordError.toString(this)

            if (loginError == null && passwordError == null) {
                showSuccess()
            }
        }
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
