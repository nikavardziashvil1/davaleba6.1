package com.example.myappfb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var emailTextView: TextView
    private lateinit var uidTextView: TextView
    private lateinit var passwordChangeButton: Button
    private lateinit var logoutButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        init()

        registerListeners()

    }

    private fun init() {

        emailTextView = findViewById(R.id.emailTextView)
        uidTextView = findViewById(R.id.uidTextView)
        passwordChangeButton = findViewById(R.id.passwordChangeButton)
        logoutButton = findViewById(R.id.logoutButton)

        emailTextView.text = mAuth.currentUser?.email
        uidTextView.text = mAuth.currentUser?.uid

    }

    private fun registerListeners() {

        passwordChangeButton.setOnClickListener {
            startActivity(Intent(this, PasswordChangeActivity::class.java))
        }

        logoutButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            mAuth.signOut()
            finish()
        }

    }

}