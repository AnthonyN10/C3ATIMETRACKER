package com.example.c3atimetracker

import Users
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.c3atimetracker.databinding.ActivitySignupBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth



class SignupActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding //Connection to database do not touch
    private lateinit var firebaseAuth: FirebaseAuth //Connection to database do not touch
    //
    private lateinit var signupUsernameEditText: EditText
    private lateinit var signupPasswordEditText: EditText
    private lateinit var signupButton: Button
    //private lateinit var signUpTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater) //Connection to database do not touch
        setContentView(binding.root)    //Connection to database do not touch

        firebaseAuth = FirebaseAuth.getInstance() //Connection to database do not touch - Receives all the data from the database

        signupUsernameEditText = findViewById(R.id.signupUsernameEditText)
        signupPasswordEditText = findViewById(R.id.signupPasswordEditText)
        signupButton = findViewById(R.id.signupButton)

        binding.signupButton.setOnClickListener { //when you click the sign up button
            val email = binding.signupUsernameEditText.text.toString() //.binding is to say fill database here
            val password = binding.signupPasswordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, LoginActivity::class.java)
                            Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
            }

            if (email.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, "Empty fields are NOT allowed!.", Toast.LENGTH_SHORT).show()
                signupUsernameEditText.requestFocus()
            } else if (email.isEmpty()) {

                //signupUsernameEditText.error = "Enter a username. "
                signupUsernameEditText.requestFocus()
                Toast.makeText(this,"Enter a Email. Eg-username@domain.co.za", Toast.LENGTH_SHORT).show()

            } else if (password.isEmpty()) {
                // signupPasswordEditText.error = "Enter a VALID Password"
                signupPasswordEditText.requestFocus()
                Toast.makeText(this, "Enter a PASSWORD must be 6 characters long.", Toast.LENGTH_SHORT).show()

            }



        }

        }

        }




