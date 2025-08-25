package com.example.task4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSignup: Button
    private lateinit var btnLogin: Button
    private lateinit var tvAuthStatus: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // use your XML file name

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Find views
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnSignup = findViewById(R.id.btnSignup)
        btnLogin = findViewById(R.id.btnLogin)
        tvAuthStatus = findViewById(R.id.tvAuthStatus)

        // Signup
        btnSignup.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                tvAuthStatus.text = "Please enter email & password"
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        tvAuthStatus.text = "Signup successful ✅"
                    } else {
                        tvAuthStatus.text = "Signup failed: ${task.exception?.message}"
                    }
                }
        }

        // Login
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                tvAuthStatus.text = "Please enter email & password"
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        tvAuthStatus.text = "Login successful 🎉"
                    } else {
                        tvAuthStatus.text = "Login failed: ${task.exception?.message}"
                    }
                }
        }
    }
}
