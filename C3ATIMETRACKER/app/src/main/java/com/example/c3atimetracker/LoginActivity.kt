package com.example.c3atimetracker

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle

import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.c3atimetracker.databinding.ActivityLoginBinding
import com.example.c3atimetracker.databinding.ActivityMainBinding
import com.example.c3atimetracker.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

private lateinit var usernametxt: EditText
private lateinit var passwordtxt: EditText
private lateinit var loginbtn: Button
private lateinit var signUpTextView :TextView
class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth =FirebaseAuth.getInstance()

        signUpTextView =findViewById(R.id.signUpTextView)
        usernametxt = findViewById(R.id.usernametxt)
        passwordtxt = findViewById(R.id.passwordtxt)
        loginbtn = findViewById(R.id.loginbtn)

        signUpTextView.setOnClickListener {

            val intent = Intent(this, SignupActivity::class.java);
            startActivity(intent)
        }

        loginbtn.setOnClickListener {

            val username = binding.usernametxt.text.toString().trim()
            val password = binding.passwordtxt.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty())
            {
                firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener{
                if (it.isSuccessful)
                {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    //finish()
                }
                else
                {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }

                 if (username.isEmpty()){
                usernametxt.error="Enter a Email"
                passwordtxt.requestFocus()

                }
                    if (username.isEmpty()) {
                passwordtxt.error = "Enter a password"
                passwordtxt.requestFocus()

                }
                    if (username.isEmpty() && password.isEmpty())
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
                usernametxt.requestFocus()
            }
            }



        }
    }
   }
