package com.it342.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.it342.auth.api.ApiClient
import com.it342.auth.ui.DashboardFragment
import com.it342.auth.ui.LoginFragment
import com.it342.auth.ui.MainActivityContract
import com.it342.auth.ui.RegisterFragment

class MainActivity : AppCompatActivity(), MainActivityContract {

    private val tokenManager by lazy { TokenManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApiClient.init(tokenManager)
        if (savedInstanceState == null) {
            navigateToStartScreen()
        }
    }

    fun navigateToStartScreen() {
        if (tokenManager.isLoggedIn()) {
            replaceFragment(DashboardFragment.newInstance())
        } else {
            replaceFragment(LoginFragment.newInstance())
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun openRegister() {
        replaceFragment(RegisterFragment.newInstance())
    }

    override fun openLogin() {
        replaceFragment(LoginFragment.newInstance())
    }

    override fun openDashboard() {
        replaceFragment(DashboardFragment.newInstance())
    }

    override fun logout() {
        tokenManager.clear()
        openLogin()
    }
}
