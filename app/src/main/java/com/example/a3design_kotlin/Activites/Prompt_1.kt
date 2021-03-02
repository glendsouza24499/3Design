package com.example.a3design_kotlin.Activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.a3design_kotlin.R

class Prompt_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prompt_1)

        val login_b=findViewById<Button>(R.id.prompt_button_log_in)
        login_b.setOnClickListener {
            val intent1=Intent(this,Log_In_Activity::class.java)
            startActivity(intent1)
        }

        val sign_in=findViewById<Button>(R.id.prompt_button_sign_in)
        sign_in.setOnClickListener {
            val intent2=Intent(this,Sign_in_activity::class.java)
            startActivity(intent2)
        }

        val skip=findViewById<Button>(R.id.skip)
        skip.setOnClickListener {
            val intent3=Intent(this,MainActivity::class.java)
            startActivity(intent3)
        }
    }
}