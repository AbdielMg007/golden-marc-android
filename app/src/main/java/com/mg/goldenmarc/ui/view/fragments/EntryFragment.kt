package com.mg.goldenmarc.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import com.mg.goldenmarc.R
import com.mg.goldenmarc.databinding.FragmentEntryBinding

class EntryFragment : Fragment(R.layout.fragment_entry) {

    private lateinit var binding: FragmentEntryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEntryBinding.bind(view)
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
        Toast.makeText(context, "entry", Toast.LENGTH_SHORT).show()
        val xd = "hola"
        showAlert(xd)
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