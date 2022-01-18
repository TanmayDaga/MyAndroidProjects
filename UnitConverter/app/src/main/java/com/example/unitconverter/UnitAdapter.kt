package com.example.unitconverter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UnitAdapter(context: Context) : RecyclerView.Adapter<UnitAdapter.CustomViewHolder>() {


    private lateinit var mListner: onItemClickListner
    val applicationContext:Context = context

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listner: onItemClickListner){
        mListner = listner
    }


    class CustomViewHolder(view: View,listner: onItemClickListner):RecyclerView.ViewHolder(view){
        var textView:TextView
        var imageView: ImageView

        init{

            textView = view.findViewById<TextView>(R.id.textView)
            imageView = view.findViewById<ImageView>(R.id.imageView)
            itemView.setOnClickListener {
                listner.onItemClick(adapterPosition)
            }

        }





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)

        return CustomViewHolder(view,mListner)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.textView.text = getString(position,this.applicationContext)
        holder.imageView.setImageResource(getImage(position))
        holder.textView.tag = getCode(position)


    }

    override fun getItemCount(): Int {
        return 8;
    }
}