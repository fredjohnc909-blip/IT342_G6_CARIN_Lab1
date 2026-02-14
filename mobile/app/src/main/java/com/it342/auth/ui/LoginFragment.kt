package com.it342.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.it342.auth.api.ApiClient
import com.it342.auth.TokenManager
import com.it342.auth.api.models.LoginRequest
import com.it342.auth.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener { doLogin() }
        binding.registerLink.setOnClickListener {
            (activity as? MainActivityContract)?.openRegister()
        }
    }

    private fun doLogin() {
        val email = binding.emailEdit.text?.toString()?.trim().orEmpty()
        val password = binding.passwordEdit.text?.toString().orEmpty()
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(requireContext(), "Email and password required", Toast.LENGTH_SHORT).show()
            return
        }
        binding.loginButton.isEnabled = false
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val res = ApiClient.authApi.login(LoginRequest(email, password))
                if (res.isSuccessful) {
                    val body = res.body()!!
                    TokenManager(requireContext()).token = body.token
                    Toast.makeText(requireContext(), "Welcome, ${body.username}", Toast.LENGTH_SHORT).show()
                    (activity as? MainActivityContract)?.openDashboard()
                } else {
                    val err = res.errorBody()?.string()?.let { parseError(it) } ?: "Login failed"
                    Toast.makeText(requireContext(), err, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
            binding.loginButton.isEnabled = true
        }
    }

    private fun parseError(body: String): String {
        return try {
            val json = org.json.JSONObject(body)
            json.optString("error", json.optString("message", "Request failed"))
        } catch (_: Exception) {
            "Request failed"
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}
