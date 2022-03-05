package com.example.quizzworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_intro.*
import java.lang.Exception

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        val auth=FirebaseAuth.getInstance()

       if(auth.currentUser!=null){
           redirect("Main")
       }
        started.setOnClickListener {
            redirect("Login")
        }
    }
    private fun redirect(string:String){
        val intent=when(string){
            "Main"->Intent(this,MainActivity::class.java)
            "Login"-> Intent(this,LoginActivity::class.java)
       else->throw Exception("Does not exist!!")
        }
        startActivity(intent)
        finish()
    }
}