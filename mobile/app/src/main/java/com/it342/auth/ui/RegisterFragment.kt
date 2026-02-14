package com.it342.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.it342.auth.api.ApiClient
import com.it342.auth.api.models.RegisterRequest
import com.it342.auth.databinding.FragmentRegisterBinding
import com.it342.auth.TokenManager
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener { doRegister() }
        binding.loginLink.setOnClickListener {
            (activity as? MainActivityContract)?.openLogin()
        }
    }

    private fun doRegister() {
        val username = binding.usernameEdit.text?.toString()?.trim().orEmpty()
        val email = binding.emailEdit.text?.toString()?.trim().orEmpty()
        val password = binding.passwordEdit.text?.toString().orEmpty()
        if (username.isBlank() || email.isBlank() || password.isBlank()) {
            Toast.makeText(requireContext(), "All fields required", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.length < 6) {
            Toast.makeText(requireContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return
        }
        binding.registerButton.isEnabled = false
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val res = ApiClient.authApi.register(RegisterRequest(username, email, password))
                if (res.isSuccessful) {
                    val body = res.body()!!
                    TokenManager(requireContext()).token = body.token
                    Toast.makeText(requireContext(), "Account created. Welcome, $username!", Toast.LENGTH_SHORT).show()
                    (activity as? MainActivityContract)?.openDashboard()
                } else {
                    val err = res.errorBody()?.string()?.let { parseError(it) } ?: "Registration failed"
                    Toast.makeText(requireContext(), err, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
            binding.registerButton.isEnabled = true
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
        fun newInstance() = RegisterFragment()
    }
}
