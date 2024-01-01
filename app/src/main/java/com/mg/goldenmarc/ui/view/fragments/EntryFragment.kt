package com.mg.goldenmarc.ui.view.fragments

import AuthViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mg.goldenmarc.R
import com.mg.goldenmarc.data.remote.network.ApiDbClient
import com.mg.goldenmarc.data.remote.network.AuthRepository
import com.mg.goldenmarc.databinding.FragmentEntryBinding
import com.mg.goldenmarc.ui.viewmodel.AuthViewModelFactory
import com.mg.goldenmarc.common.Result



class EntryFragment : Fragment(R.layout.fragment_entry) {

    private lateinit var binding: FragmentEntryBinding
    private lateinit var viewModelFactory: AuthViewModelFactory
    private lateinit var viewModel: AuthViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEntryBinding.bind(view)
        val authRepository = AuthRepository(ApiDbClient.service)
        viewModelFactory = AuthViewModelFactory(authRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]

        setup()
    }

    private fun setup() {
        binding.progressBar.isInvisible = true
        binding.entryBtn.setOnClickListener {
            //binding.progressBar.isInvisible = false
            entryBtnAction()
        }
        binding.forgotTv.setOnClickListener {
            forgotTvAction()
        }
        binding.loginEntryTv.setOnClickListener {
            loginEntryTvAction()
        }

    }

    private fun entryBtnAction() {
        if (binding.emailInput.text.isEmpty() || binding.passwordInput.text.isEmpty()) {
            binding.progressBar.isInvisible = true
            Toast.makeText(context, "Favor de llenar todos los campos", Toast.LENGTH_SHORT).show()
            return
        }


        viewModel.login(
            binding.emailInput.text.toString(),
            binding.passwordInput.text.toString()
        )

        viewModel.loginResult.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success<*> -> {
                    // Handle successful login
                    val userResponse = result.getOrNull()
                    Toast.makeText(context, "Login successful. User: ${userResponse?.userName}", Toast.LENGTH_SHORT).show()
                }
                is Result.Failure<*> -> {
                    // Handle login failure
                    val errorMessage = result.exception.message ?: "Unknown error"
                    showAlert(errorMessage)
                }
            }
        })
    }

    private fun forgotTvAction() {
        Toast.makeText(context, "forgot your pass", Toast.LENGTH_SHORT).show()
    }

    private fun loginEntryTvAction() {
        Toast.makeText(context, "don't have account", Toast.LENGTH_SHORT).show()
    }

    private fun showAlert(errorMessage: String) {
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setTitle("Error")
        builder?.setMessage(errorMessage)
        builder?.setPositiveButton("Aceptar", null)
        builder?.create()?.show()
    }

}