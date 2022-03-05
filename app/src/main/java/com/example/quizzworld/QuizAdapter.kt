package com.example.quizzworld

import android.content.Context
import android.content.Intent
import android.content.ReceiverCallNotAllowedException
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class QuizAdapter(val context:Context,val quizzes:List<Quiz>):
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {
inner class QuizViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var tl:TextView=itemView.findViewById(R.id.card_title)
    var img:ImageView=itemView.findViewById(R.id.card_icon)
            var card:CardView=itemView.findViewById(R.id.cardView)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.quiz,parent,false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
  holder.tl.text=quizzes[position].title
        holder.card.setCardBackgroundColor(Color.parseColor(PickColor.getcolor()))
        holder.img.setImageResource(PickImage.getImg())
        holder.itemView.setOnClickListener{

            val intent= Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE",quizzes[position].title)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
       return quizzes.size
    }
}