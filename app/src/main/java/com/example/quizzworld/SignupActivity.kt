package com.example.quizzworld

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*
import kotlin.math.log

class SignupActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth= FirebaseAuth.getInstance()

        signup_btn.setOnClickListener {
            signUp()
        }
        login_sign.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
       finish()
        }
    }
    private fun signUp(){
       var email=signup_email.text.toString()
        var password=pass_signup.text.toString()
        var confirm=con_pass.text.toString()
        if(email.isBlank()||password.isBlank()||confirm.isBlank()){
            Toast.makeText(this,"Please enter required fields!!",Toast.LENGTH_SHORT).show()
            return
        }

        if(password!=confirm){
            Toast.makeText(this,"Password confirm password do not match",Toast.LENGTH_SHORT).show()
      return
        }


        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                if(it.isSuccessful){
Toast.makeText(this,"Login successful!",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this,"Error creating user",Toast.LENGTH_SHORT).show()
                }
            }
    }
}