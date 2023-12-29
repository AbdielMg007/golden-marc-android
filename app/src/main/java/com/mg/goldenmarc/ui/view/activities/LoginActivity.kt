package com.mg.goldenmarc.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.mg.goldenmarc.R
import com.mg.goldenmarc.databinding.ActivityLoginBinding
import com.mg.goldenmarc.ui.view.fragments.EntryFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragmentLogin, EntryFragment())
        }
    }
    override fun onPause() {
        super.onPause()
        finish()
    }
}