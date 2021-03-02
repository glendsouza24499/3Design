package com.example.a3design_kotlin.Activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.a3design_kotlin.R
import com.google.firebase.auth.FirebaseAuth

class App_Start_Screen : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val user=auth.currentUser

        setContentView(R.layout.activity_app__start__screen)

        Handler().postDelayed({
            if (user!=null){
                val mainActIntent=Intent(this,MainActivity::class.java)
                startActivity(mainActIntent)
                finish()
            }else{
                val prompt1Intent=Intent(this,Prompt_1::class.java)
                startActivity(prompt1Intent)
            }
        },1000)
    }
}