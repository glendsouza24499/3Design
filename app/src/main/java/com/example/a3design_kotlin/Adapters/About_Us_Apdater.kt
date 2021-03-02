package com.example.a3design_kotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a3design_kotlin.Model.About_Us_Model
import com.example.a3design_kotlin.R

class About_Us_Apdater(val arrayList:ArrayList<About_Us_Model>): RecyclerView.Adapter<About_Us_Apdater.myViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var view:View
        var inflater:LayoutInflater= LayoutInflater.from(parent?.context)
        view =inflater.inflate(R.layout.about_us_design,parent,false)

        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        var array:About_Us_Model=arrayList.get(position)

        holder.headerA.text= array.headerM
        holder.midtext1.text=array.midtxtM1
        holder.midtext2.text=array.midtxtM2
        holder.midtext3.text=array.midtxtM3
        holder.footerA.text=array.footerM

        //you will have to load image with glide here
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            var headerA=itemView.findViewById<TextView>(R.id.header_txt1)
            var midtext1=itemView.findViewById<TextView>(R.id.middle_txt1)
            var midtext2=itemView.findViewById<TextView>(R.id.middle_txt2)
            var midtext3=itemView.findViewById<TextView>(R.id.middle_txt3)
            var footerA=itemView.findViewById<TextView>(R.id.footer_txt1)
    }

}