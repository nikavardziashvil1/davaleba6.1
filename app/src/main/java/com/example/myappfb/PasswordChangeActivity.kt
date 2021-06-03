package com.example.myappfb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordChangeActivity : AppCompatActivity() {

    private lateinit var newPasswordEditText: EditText
    private lateinit var changePasswordButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        mAuth = FirebaseAuth.getInstance()

        this.init()

        this.registerListeners()

    }

    private fun init() {

        newPasswordEditText = findViewById(R.id.editTextTextNewPassword)
        changePasswordButton = findViewById(R.id.changePasswordButton)

    }

    private fun registerListeners() {

        changePasswordButton.setOnClickListener {

            val newPassword = newPasswordEditText.text.toString()

            if (newPassword.isEmpty()) {
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            mAuth.currentUser?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

}