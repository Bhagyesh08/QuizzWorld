package com.example.quizzworld

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizzworld.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter:QuizAdapter
    private var quizList= mutableListOf<Quiz>()
    lateinit var firestore: FirebaseFirestore
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViews()
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
                R.id.about -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                }
R.id.exit ->{
    val builder=MaterialAlertDialogBuilder(this)
    builder.setTitle("Exit")
        .setMessage("Do you want to close app?")
        .setPositiveButton("Yes"){_, _ ->
exitProcess(1)
        }
        .setNegativeButton("No"){dialog, _ ->
            dialog.dismiss()

        }
    val customDialog=builder.create()
    customDialog.show()
    customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED)
    customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)
}
            }
            true
        }
    }
    fun setViews(){
        setupFireStore()
        setDrawerLayout()
        setRecyclerView()
        setDatePicker()

    }

    private fun setDatePicker(){
        datePicker.setOnClickListener {
            val date=MaterialDatePicker.Builder.datePicker().build()
            date.show(supportFragmentManager,"DatePicker")
            date.addOnPositiveButtonClickListener {
                val format=SimpleDateFormat("dd-mm-yyyy")
                val dt=format.format(Date(it))
                val intent= Intent(this,QuestionActivity::class.java)
                intent.putExtra("DATE",dt)
                startActivity(intent)
            }
            date.addOnNegativeButtonClickListener {
                Log.d("DATEPICKER", date.headerText)
            }
            date.addOnCancelListener {
                Log.d("DATEPICKER", "Cancelled")
            }
        }
    }
    private fun setupFireStore(){
        firestore= FirebaseFirestore.getInstance()
        val collectionReference=firestore.collection("quizzes")
        collectionReference.addSnapshotListener{ value, error ->
            if(value==null||error!=null){
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }
    fun setDrawerLayout(){
        setSupportActionBar(appBar)
        actionBarDrawerToggle= ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name)
   actionBarDrawerToggle.syncState()

    }
   private fun setRecyclerView(){
        adapter= QuizAdapter(this,quizList)
        quizRec.layoutManager=GridLayoutManager(this,2)
        quizRec.adapter=adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}