package com.example.quizzworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_result_acivity.*

class ResultAcivity : AppCompatActivity() {
    lateinit var quiz: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_acivity)
   setUp()
    }
    private fun setUp(){
        val data=intent.getStringExtra("Quiz")
quiz= Gson().fromJson<Quiz>(data,Quiz::class.java)
        calculate()

    }
    private fun calculate(){
        var score=0
        for(entry in quiz.questions.entries){
            val question=entry.value
            if(question.answer==question.correct){
                score+=10
            }
        }
        txtScore.text="Your Score : $score"
    }
}