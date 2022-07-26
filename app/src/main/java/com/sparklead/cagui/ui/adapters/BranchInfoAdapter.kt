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
    private val overview : String,
    private val path :String,
    private val responsibilities:String,
    private val skills:String,
    private val careerProspects:String,
    private val companies:String,
    private val prosAndCons:String,
    private val futureGrowth:String,
    private val alternateCareer:String,
    private val averageSalary:String
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
            holder.itemView.tv_details_info1.text = overview.replace("\\n", "\n")
            holder.itemView.btn_speak.setOnClickListener {

                //implement speak option.....

            }

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

            //responsibility

            holder.itemView.tv_details_info3.visibility = if(isVisible3) View.VISIBLE else View.GONE
            holder.itemView.ll_line3.visibility = if(isVisible3) View.VISIBLE else View.GONE
            holder.itemView.iv_visible_arrow3.setOnClickListener {
                isVisible3 = !isVisible3
                notifyItemChanged(position)
            }
            if(isVisible3){
                holder.itemView.iv_visible_arrow3.setImageResource(R.drawable.up_arrow)
            }
            else{
                holder.itemView.iv_visible_arrow3.setImageResource(R.drawable.down_arrow)
            }
            holder.itemView.tv_details_info3.text = responsibilities

            //skills

            holder.itemView.tv_details_info4.visibility = if(isVisible4) View.VISIBLE else View.GONE
            holder.itemView.ll_line4.visibility = if(isVisible4) View.VISIBLE else View.GONE
            holder.itemView.iv_visible_arrow4.setOnClickListener {
                isVisible4 = !isVisible4
                notifyItemChanged(position)
            }
            if(isVisible4){
                holder.itemView.iv_visible_arrow4.setImageResource(R.drawable.up_arrow)
            }
            else{
                holder.itemView.iv_visible_arrow4.setImageResource(R.drawable.down_arrow)
            }
            holder.itemView.tv_details_info4.text = skills

            // career prospects

            holder.itemView.tv_details_info5.visibility = if(isVisible5) View.VISIBLE else View.GONE
            holder.itemView.ll_line5.visibility = if(isVisible5) View.VISIBLE else View.GONE
            holder.itemView.iv_visible_arrow5.setOnClickListener {
                isVisible5 = !isVisible5
                notifyItemChanged(position)
            }
            if(isVisible5){
                holder.itemView.iv_visible_arrow5.setImageResource(R.drawable.up_arrow)
            }
            else{
                holder.itemView.iv_visible_arrow5.setImageResource(R.drawable.down_arrow)
            }
            holder.itemView.tv_details_info5.text = careerProspects

            // companies

            holder.itemView.tv_details_info6.visibility = if(isVisible6) View.VISIBLE else View.GONE
            holder.itemView.ll_line6.visibility = if(isVisible6) View.VISIBLE else View.GONE
            holder.itemView.iv_visible_arrow6.setOnClickListener {
                isVisible6 = !isVisible6
                notifyItemChanged(position)
            }
            if(isVisible6){
                holder.itemView.iv_visible_arrow6.setImageResource(R.drawable.up_arrow)
            }
            else{
                holder.itemView.iv_visible_arrow6.setImageResource(R.drawable.down_arrow)
            }
            holder.itemView.tv_details_info6.text = companies

            // pros and cons

            holder.itemView.tv_details_info7.visibility = if(isVisible7) View.VISIBLE else View.GONE
            holder.itemView.ll_line7.visibility = if(isVisible7) View.VISIBLE else View.GONE
            holder.itemView.iv_visible_arrow7.setOnClickListener {
                isVisible7 = !isVisible7
                notifyItemChanged(position)
            }
            if(isVisible7){
                holder.itemView.iv_visible_arrow7.setImageResource(R.drawable.up_arrow)
            }
            else{
                holder.itemView.iv_visible_arrow7.setImageResource(R.drawable.down_arrow)
            }
            holder.itemView.tv_details_info7.text = prosAndCons

            //future growth

            holder.itemView.tv_details_info8.visibility = if(isVisible8) View.VISIBLE else View.GONE
            holder.itemView.ll_line8.visibility = if(isVisible8) View.VISIBLE else View.GONE
            holder.itemView.iv_visible_arrow8.setOnClickListener {
                isVisible8 = !isVisible8
                notifyItemChanged(position)
            }
            if(isVisible8){
                holder.itemView.iv_visible_arrow8.setImageResource(R.drawable.up_arrow)
            }
            else{
                holder.itemView.iv_visible_arrow8.setImageResource(R.drawable.down_arrow)
            }
            holder.itemView.tv_details_info8.text = futureGrowth

            // alternate career

            holder.itemView.tv_details_info9.visibility = if(isVisible9) View.VISIBLE else View.GONE
            holder.itemView.ll_line9.visibility = if(isVisible9) View.VISIBLE else View.GONE
            holder.itemView.iv_visible_arrow9.setOnClickListener {
                isVisible9 = !isVisible9
                notifyItemChanged(position)
            }
            if(isVisible9){
                holder.itemView.iv_visible_arrow9.setImageResource(R.drawable.up_arrow)
            }
            else{
                holder.itemView.iv_visible_arrow9.setImageResource(R.drawable.down_arrow)
            }
            holder.itemView.tv_details_info9.text = alternateCareer

            // average salary

            holder.itemView.tv_details_info10.visibility = if(isVisible10) View.VISIBLE else View.GONE
            holder.itemView.ll_line10.visibility = if(isVisible10) View.VISIBLE else View.GONE
            holder.itemView.iv_visible_arrow10.setOnClickListener {
                isVisible10 = !isVisible10
                notifyItemChanged(position)
            }
            if(isVisible10){
                holder.itemView.iv_visible_arrow10.setImageResource(R.drawable.up_arrow)
            }
            else{
                holder.itemView.iv_visible_arrow10.setImageResource(R.drawable.down_arrow)
            }
            holder.itemView.tv_details_info10.text = averageSalary



        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    private class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

}