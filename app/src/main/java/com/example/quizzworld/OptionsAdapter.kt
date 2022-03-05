package com.example.quizzworld

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OptionsAdapter(val context:Context,val questions: Questions):
RecyclerView.Adapter<OptionsAdapter.OptionViewHolder>(){
    private var options:List<String> = listOf(questions.option1,questions.option2,questions.option3,questions.option4)
    inner class OptionViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var v=itemView.findViewById<TextView>(R.id.quizOption)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.options,parent,false)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.v.text=options[position]
        holder.itemView.setOnClickListener{
            questions.correct=options[position]
            notifyDataSetChanged()
        }
        if(questions.correct==options[position]){
            holder.itemView.setBackgroundResource(R.drawable.option_selected)

        }else{
            holder.itemView.setBackgroundResource(R.drawable.option_item)
        }
    }

    override fun getItemCount(): Int {
      return options.size
    }
}