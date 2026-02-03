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
import ru.netology.nmedia.domain.LoginError
import ru.netology.nmedia.domain.PasswordError
import ru.netology.nmedia.presenter.LoginPresenter

class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityLoginBinding
    private val presenter = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.setView(this)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applyInsets()

        binding.loginEditText.doAfterTextChanged {
            presenter.setLogin(it?.toString())
        }

        binding.passwordEditText.doAfterTextChanged {
            presenter.setPassword(it?.toString())
        }

        binding.login.setOnClickListener {
            presenter.login()
        }
    }

    override fun showLoginError(loginError: LoginError?) {
        binding.loginInput.error = loginError.toString(this)
    }

    override fun showPasswordError(passwordError: PasswordError?) {
        binding.passwordInput.error = passwordError.toString(this)
    }

    override fun showSuccess() {
        Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show()
    }

    private fun applyInsets() {
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
