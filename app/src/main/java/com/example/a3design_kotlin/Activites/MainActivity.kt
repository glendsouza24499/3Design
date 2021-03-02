package com.example.a3design_kotlin.Activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.a3design_kotlin.Fragments.About_Us_Fragment
import com.example.a3design_kotlin.Fragments.All_Products_Fragment
import com.example.a3design_kotlin.Fragments.Home_Fragment
import com.example.a3design_kotlin.Fragments.Profile_Fragment
import com.example.a3design_kotlin.Model.All_Products_Model
import com.example.a3design_kotlin.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private var nav_view: NavigationView?=null
    private var toggle : ActionBarDrawerToggle?=null
    private var drawerLayout : DrawerLayout?=null
    private var toolbar: androidx.appcompat.widget.Toolbar?=null
    private var frag:Fragment?=null
    private var auth: FirebaseAuth?=null
//    private  var profileImage: ImageView?=null
//    private  var profileName:TextView?=null

//    val arrayList=ArrayList<All_Products_Model>()       //original arraylist for all_products
//    val displayList=ArrayList<All_Products_Model>()     //backup arraylist for all_products

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        nav_view = findViewById(R.id.nav_menu)
        drawerLayout = findViewById(R.id.drawerLayout)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout?.addDrawerListener(toggle!!)
        toggle?.syncState()



        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, Home_Fragment()).commit()


        nav_view?.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    frag = Home_Fragment()
                }
                R.id.menu_aboutus -> {
                    frag = About_Us_Fragment()
                }
                R.id.menu_allproducts -> {
                    frag = All_Products_Fragment()
                }
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, frag!!).commit()
            drawerLayout?.closeDrawer(GravityCompat.START)
            true
        }

        auth = FirebaseAuth.getInstance()
        val currentUser = auth?.currentUser

        val profileName: TextView = nav_view?.getHeaderView(0)!!.findViewById(R.id.header_name)  //Important line to add the info into specific place in header from google account
        val profileImage: ImageView = nav_view?.getHeaderView(0)!!.findViewById(R.id.imageViewHeader)

//        profileName.text = "Hello, " + currentUser?.displayName
//        Glide.with(this).load(currentUser?.photoUrl).into(profileImage)

//        val signout_bbtn=findViewById<Button>(R.id.sign_out_bttn)       //test
//        signout_bbtn.setOnClickListener {
//            auth.signOut()
//            val intent1s=Intent(this,MainActivity::class.java)
//            startActivity(intent1s)
//        }


        //test for condition based profile

        val prName = currentUser?.displayName
        when {
            prName == null -> {
                profileName.text = "Login/SignIn"

                val prclick: TextView = nav_view?.getHeaderView(0)!!.findViewById(R.id.header_name)
                prclick.setOnClickListener {
                    val intent2 = Intent(this, Log_In_Activity::class.java)
                    startActivity(intent2)
                    return@setOnClickListener
                }
                return
            }
            prName != null -> {

                profileName.text = "Hello, " + currentUser?.displayName
                Glide.with(this).load(currentUser?.photoUrl).into(profileImage)

                val prclick2: TextView = nav_view?.getHeaderView(0)!!.findViewById(R.id.header_name)
                prclick2.setOnClickListener {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, Profile_Fragment()).commit()
                    drawerLayout?.closeDrawer(GravityCompat.START)
                    return@setOnClickListener
                }
                return
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle!!.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}