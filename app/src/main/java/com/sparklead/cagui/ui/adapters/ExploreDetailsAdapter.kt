package com.sparklead.cagui.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.cagui.R
import com.sparklead.cagui.models.BranchItem
import com.sparklead.cagui.models.Constants
import com.sparklead.cagui.ui.activities.BranchInfoActivity
import kotlinx.android.synthetic.main.item_list_type_branches.view.*

class ExploreDetailsAdapter(
    private val context: Context,
    private val list: ArrayList<String>,
    private val title: String,
    private val total : Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list_type_branches,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if(holder is MyViewHolder)
        {
            holder.itemView.tv_branch_name.text = model
            val branch = "branch${position+1}"
            holder.itemView.btn_view_details.setOnClickListener {
                val intent = Intent(context,BranchInfoActivity::class.java)
                intent.putExtra(Constants.EXTRA_BRANCH_NAME,branch)
                intent.putExtra(Constants.EXTRA_TITLE,title)
                intent.putExtra(Constants.EXTRA_BRANCH_TITLE,model)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return total
    }

    private class MyViewHolder(view: View): RecyclerView.ViewHolder(view)
}