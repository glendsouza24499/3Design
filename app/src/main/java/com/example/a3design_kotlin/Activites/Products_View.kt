package com.example.a3design_kotlin.Activites

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.a3design_kotlin.Adapters.All_Products_Adapters
import com.example.a3design_kotlin.Model.All_Products_Model
import com.example.a3design_kotlin.R
import org.json.JSONException

private var requestQueue: RequestQueue?=null
private var toolbar2: androidx.appcompat.widget.Toolbar?=null

class Products_View : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products__view)

        toolbar2=findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar2)

        val backtoggle=supportActionBar
        backtoggle?.setDisplayHomeAsUpEnabled(true)
        backtoggle?.setDisplayHomeAsUpEnabled(true)



        jsonRequest()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun jsonRequest() {
        val JSON_URL=intent.getStringExtra("JSON_URLM")
        Toast.makeText(this, JSON_URL, Toast.LENGTH_SHORT).show()
        var request=JsonArrayRequest(Request.Method.GET, JSON_URL,null,Response.Listener { response ->
            try {
                val image1=findViewById<ImageView>(R.id.PImage1)
                val name=findViewById<TextView>(R.id.PName)
                val category=findViewById<TextView>(R.id.PCategory)
                val price=findViewById<TextView>(R.id.PPrice)
                val description=findViewById<TextView>(R.id.PDescription)

                for (i in 0 until response.length()){
                    var jsonObject=response.getJSONObject(i)
                    Glide.with(applicationContext).load(jsonObject.getString("PImage1_J")).into(image1)
                    name.text=jsonObject.getString("PName_J")
                    category.text=jsonObject.getString("PCategory")
                    price.text=jsonObject.getString("PPrice")
                    description.text=jsonObject.getString("PDescirption")

                }

            }catch (e: JSONException){
                e.printStackTrace()
            }
        },Response.ErrorListener { error ->
            error.printStackTrace()
        })
        requestQueue= Volley.newRequestQueue(applicationContext)
        requestQueue?.add(request)
    }

}

