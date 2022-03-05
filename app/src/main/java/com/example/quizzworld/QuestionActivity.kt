package com.example.quizzworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    var q:MutableList<Quiz>?=null
    var qe:MutableMap<String,Questions>?=null
    var ind=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setFireStore()
        prev.setOnClickListener {
            ind--
            bind()
        }
        next.setOnClickListener {
            ind++
            bind()
        }
        submit.setOnClickListener {
            val intent= Intent(this,ResultAcivity::class.java)
val json= Gson().toJson(q!![0])
            intent.putExtra("Quiz",json)
            startActivity(intent)
        }
    }
    private fun setFireStore(){
        val firestore=FirebaseFirestore.getInstance()
        var date=intent.getStringExtra("DATE")
        if(date!=null){
            firestore.collection("quizzes").whereEqualTo("title",date)
                .get()
                .addOnSuccessListener {
                    if(it!=null&&!it.isEmpty){
                        q=it.toObjects(Quiz::class.java)
                        qe=q!![0].questions
                        bind()
                    }
                }
        }
    }
    private fun bind(){
      prev.visibility=View.GONE
      submit.visibility=View.GONE
      next.visibility=View.GONE
      if(ind===1){
          next.visibility=View.VISIBLE
      }else if(ind==qe!!.size){
          submit.visibility=View.VISIBLE
          prev.visibility=View.VISIBLE
      }else{
          prev.visibility=View.VISIBLE
          next.visibility=View.VISIBLE
      }
        val ques=qe!!["question$ind"]
        ques?.let {
            que.text=it.question
            val option=OptionsAdapter(this,it)
            options.layoutManager=LinearLayoutManager(this)
            options.adapter=option
            options.setHasFixedSize(true)
        }

    }

}