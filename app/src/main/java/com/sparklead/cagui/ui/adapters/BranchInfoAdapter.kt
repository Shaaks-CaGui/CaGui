package com.sparklead.cagui.ui.adapters

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.cagui.R
import com.sparklead.cagui.models.Info
import com.sparklead.cagui.models.Questions
import kotlinx.android.synthetic.main.item_list_details_info.view.*


class BranchInfoAdapter(
    private val context: Context,
    private var tisVisible: Boolean,
    private val overview : String,
    private val path :String
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var isVisible1 = false
    var isVisible2 = false
    var isVisible3 = false
    var isVisible4 = false
    var isVisible5 = false
    var isVisible6 = false
    var isVisible7 = false
    var isVisible8 = false
    var isVisible9 = false
    var isVisible10 = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list_details_info,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val model = items[position]
        if(holder is MyViewHolder){

            //overview

            holder.itemView.tv_details_info1.visibility = if(isVisible1) View.VISIBLE else View.GONE
            holder.itemView.ll_line1.visibility = if(isVisible1) View.VISIBLE else View.GONE
            holder.itemView.iv_visible_arrow1.setOnClickListener {
                isVisible1 = !isVisible1
                notifyItemChanged(position)
            }
            if(isVisible1){
                holder.itemView.iv_visible_arrow1.setImageResource(R.drawable.up_arrow)
            }
            else{
                holder.itemView.iv_visible_arrow1.setImageResource(R.drawable.down_arrow)
            }
            holder.itemView.tv_details_info1.text = overview

            //path

            holder.itemView.tv_details_info2.visibility = if(isVisible2) View.VISIBLE else View.GONE
            holder.itemView.ll_line2.visibility = if(isVisible2) View.VISIBLE else View.GONE
            holder.itemView.iv_visible_arrow2.setOnClickListener {
                isVisible2 = !isVisible2
                notifyItemChanged(position)
            }
            if(isVisible2){
                holder.itemView.iv_visible_arrow2.setImageResource(R.drawable.up_arrow)
            }
            else{
                holder.itemView.iv_visible_arrow2.setImageResource(R.drawable.down_arrow)
            }
            holder.itemView.tv_details_info2.text = path



        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    private class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

}