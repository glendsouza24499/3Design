package com.example.a3design_kotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a3design_kotlin.Fragments.Home_Fragment
import com.example.a3design_kotlin.Model.Home_Model
import com.example.a3design_kotlin.R

class Home_Adapter( val arrayList: ArrayList<Home_Model>) : RecyclerView.Adapter<Home_Adapter.myViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var view:View
        var inflater:LayoutInflater= LayoutInflater.from(parent?.context)
        view= inflater.inflate(R.layout.home_design,parent,false)

        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        var array:Home_Model=arrayList.get(position)

        holder.Sub_headerTextA1.text=array.Sub_headertxt1
        holder.Desc_TextA1.text=array.Desc_Text1
        holder.Desc_TextA2.text=array.Desc_Text2

        holder.HeaderA1.text=array.Header1

        Glide.with(holder.itemView.context).load(array.Image_View1).into(holder.Image_ViewA1)

        holder.Desc_TextA3.text=array.Desc_Text3

        Glide.with(holder.itemView.context).load(array.Image_View2).into(holder.Image_ViewA2)

        holder.Desc_TextA4.text=array.Desc_Text4

        holder.HeaderA2.text=array.Header2

        holder.TextA1.text=array.Text1
        holder.TextA2.text=array.Text2
        holder.TextA3.text=array.Text3
        holder.TextA4.text=array.Text4

        holder.HeaderA3.text=array.Header3

        holder.Desc_TextA5.text=array.Desc_Text5

        holder.footerA1.text=array.footer
    }

    override fun getItemCount(): Int {
        return  arrayList.size
    }

    class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var Sub_headerTextA1=itemView.findViewById<TextView>(R.id.Sub_headertxt1)
        var Desc_TextA1=itemView.findViewById<TextView>(R.id.Desc_Text1)
        var Desc_TextA2=itemView.findViewById<TextView>(R.id.Desc_Text2)

        var HeaderA1=itemView.findViewById<TextView>(R.id.Header1)

        var Image_ViewA1=itemView.findViewById<ImageView>(R.id.Image_View1)
        var Desc_TextA3=itemView.findViewById<TextView>(R.id.Desc_Text3)
        var Image_ViewA2=itemView.findViewById<ImageView>(R.id.Image_View2)
        var Desc_TextA4=itemView.findViewById<TextView>(R.id.Desc_Text4)

        var HeaderA2=itemView.findViewById<TextView>(R.id.Header2)

        var TextA1=itemView.findViewById<TextView>(R.id.Text1)
        var TextA2=itemView.findViewById<TextView>(R.id.Text2)
        var TextA3=itemView.findViewById<TextView>(R.id.Text3)
        var TextA4=itemView.findViewById<TextView>(R.id.Text4)

        var HeaderA3=itemView.findViewById<TextView>(R.id.Header3)

        var Desc_TextA5=itemView.findViewById<TextView>(R.id.Desc_Text5)

        var footerA1=itemView.findViewById<TextView>(R.id.footer)
    }
}