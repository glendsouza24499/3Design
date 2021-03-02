package com.example.a3design_kotlin.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.example.a3design_kotlin.Activites.MainActivity
import com.example.a3design_kotlin.R
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Profile_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Profile_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var auth: FirebaseAuth?=null
    private var drawerLayout : DrawerLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile_, container, false)


        auth = FirebaseAuth.getInstance()
        val currentUser=auth?.currentUser

        val logout=view.findViewById<Button>(R.id.profileLogOutBttn)
        logout.setOnClickListener {
            auth?.signOut()
            val intent1s=Intent(this.context,MainActivity::class.java)
            startActivity(intent1s)
        }

        val profileimage=view.findViewById<ImageView>(R.id.imageProfileF)
        val profilename=view.findViewById<TextView>(R.id.profileNameF)
        val profileEmail=view.findViewById<TextView>(R.id.profileEmailF)

        Glide.with(this).load(currentUser?.photoUrl).into(profileimage)
        profilename.text=currentUser?.displayName
        profileEmail.text=currentUser?.email


        return view
        return profileEmail
        return profileimage
        return profilename
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Profile_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                Profile_Fragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}