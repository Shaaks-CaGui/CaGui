package com.sparklead.cagui.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.cagui.R
import com.sparklead.cagui.ui.activities.Questions
import kotlinx.android.synthetic.main.item_list_layout.view.*


class QuestionsListAdapter(
    private val items: ArrayList<Questions>
): RecyclerView.Adapter<QuestionsListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_layout,
            parent,
            false
        )
        println("yes")
        return MyViewHolder(item)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = items[position]

        holder.questionsView.text = currentItem.questions
        holder.answerView.text = currentItem.answer

    }

    override fun getItemCount(): Int {
        println(items.size)
        return items.size
    }

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

        val questionsView : TextView = view.findViewById(R.id.tv_item_name)
        val answerView : TextView = view.findViewById(R.id.extended_text)

    }
}