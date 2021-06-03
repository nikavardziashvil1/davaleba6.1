package com.example.myappfb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordResetActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var sendEmailButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)

        mAuth = FirebaseAuth.getInstance()

        this.init()

        this.registerListeners()

    }

    private fun init() {

        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        sendEmailButton = findViewById(R.id.sendEmailButton)

    }

    private fun registerListeners() {

        sendEmailButton.setOnClickListener {

            val email = emailEditText.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            mAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Check email!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

}