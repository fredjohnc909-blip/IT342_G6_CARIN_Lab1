package com.it342.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.it342.auth.TokenManager
import com.it342.auth.api.ApiClient
import com.it342.auth.databinding.FragmentDashboardBinding
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!TokenManager(requireContext()).isLoggedIn()) {
            (activity as? MainActivityContract)?.openLogin()
            return
        }
        binding.logoutButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    ApiClient.authApi.logout()
                } catch (_: Exception) { }
                (activity as? MainActivityContract)?.logout()
            }
        }
        loadProfile()
    }

    private fun loadProfile() {
        binding.progressBar.visibility = View.VISIBLE
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val res = ApiClient.authApi.getMe()
                if (res.isSuccessful) {
                    val user = res.body()!!
                    binding.profileId.text = user.id.toString()
                    binding.profileUsername.text = user.username
                    binding.profileEmail.text = user.email
                    binding.profileDateJoined.text = user.createdAt?.let { parseAndFormat(it) } ?: "—"
                } else {
                    if (res.code() == 401) {
                        (activity as? MainActivityContract)?.logout()
                        return@launch
                    }
                    Toast.makeText(requireContext(), "Failed to load profile", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun parseAndFormat(iso: String): String {
        return try {
            val instant = java.time.Instant.parse(iso)
            java.time.format.DateTimeFormatter.ofPattern("MMMM d, yyyy")
                .withZone(java.time.ZoneId.systemDefault())
                .format(instant)
        } catch (_: Exception) {
            iso
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = DashboardFragment()
    }
}
