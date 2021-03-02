package com.example.a3design_kotlin.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a3design_kotlin.Activites.Products_View
import com.example.a3design_kotlin.Model.All_Products_Model
import com.example.a3design_kotlin.R


class All_Products_Adapters(var arrayList: ArrayList<All_Products_Model>): RecyclerView.Adapter<All_Products_Adapters.ItemHolder>() {
    val context: Context?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        var layout=LayoutInflater.from(parent.context)
        var itemHolder=layout.inflate(R.layout.allproducts_gridview_design, parent, false)
        return  ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var array:All_Products_Model= arrayList.get(position)

        Glide.with(holder.itemView.context).load(array.ImageM1).into(holder.ImageA1) //glide for image

        holder.text_typeA1.text=array.text_typeM1
        holder.text_nameA1.text=array.text_nameM1
        holder.text_priceA1.text= array.text_priceM1

        holder.url=array.productdetail_urlM
    }


    override fun getItemCount(): Int {
        return arrayList.size
    }



    class ItemHolder(itemView: View, var url:String?=null): RecyclerView.ViewHolder(itemView){

        var ImageA1=itemView.findViewById<ImageView>(R.id.Image1)
        var text_typeA1=itemView.findViewById<TextView>(R.id.text_type1)
        var text_nameA1=itemView.findViewById<TextView>(R.id.text_name1)
        var text_priceA1=itemView.findViewById<TextView>(R.id.text_price1)
//        val fragmentManager:FragmentManager?=null
//        var onItemClick:((All_Products_Model)->Unit)?=null
//        var arrayList2:ArrayList<All_Products_Model>?=null
//        companion object{
//
//        }

        init {
                itemView.setOnClickListener{
//                    val fragment=Home_Fragment()
//                    Toast.makeText(itemView.context, "TEST: " + position, Toast.LENGTH_SHORT).show()
//                    fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, fragment)?.commit()
                    val intent=Intent(itemView.context,Products_View::class.java)
                    intent.putExtra("JSON_URLM", url)
                    itemView.context.startActivity(intent)
                }
        }
    }
}

