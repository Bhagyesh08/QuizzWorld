package com.example.quizzworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth= FirebaseAuth.getInstance()


        log_btn.setOnClickListener {
            login()
        }

        sign_log.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
        finish()
        }
    }

    private fun login() {
       var email=login_email.text.toString()
        var password=login_pass.text.toString()
        if(email.isBlank()||password.isBlank()){
            Toast.makeText(this,"Login credentials cannot be empty",Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                if(it.isSuccessful){
                    Toast.makeText(this,"Login Successful!",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this,"Authentication Failed!!",Toast.LENGTH_SHORT).show()
                }
            }


    }
}