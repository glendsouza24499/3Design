package com.example.a3design_kotlin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.a3design_kotlin.Adapters.About_Us_Apdater
import com.example.a3design_kotlin.Model.About_Us_Model
import com.example.a3design_kotlin.R
import org.json.JSONArray
import org.json.JSONException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [About_Us_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class About_Us_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var arrayList: ArrayList<About_Us_Model>?=null
    private var recyclerView: RecyclerView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View= inflater.inflate(R.layout.fragment_about_us_, container, false)
        recyclerView=view.findViewById(R.id.About_Us_Recycler_View)
        recyclerView?.layoutManager=LinearLayoutManager(context)
        arrayList=ArrayList()

        dataInput()
        return view
    }

    private fun dataInput() {
        var aboutus:About_Us_Model= About_Us_Model()
        aboutus.headerM="ABOUT US"
        aboutus.midtxtM1="Here at 3Design our main goal is to make an online stage that will help carry everybody closer to the ever-growing prospects of 3D printing. We trust you don’t need to be an expert 3D modeler neither one of the you need your own 3D printer to have the option to take an interest in this powerfully creating world. You can customize and arrange custom items for yourself, your companions, or even your business clients which we print with 3D printers and convey to you. We just sit tight for your innovativeness!"
        aboutus.midtxtM2="There are an ever increasing number of organizations on the planet that offer 3D displaying or 3D printing as a help. Be that as it may, they may require more mastery and experience, so they are not available to everybody. At 3Design we offer an answer for these requirements. We do the 3D printing assignments for you and you don’t need to enlist an architect as we have done the underlying displaying steps. You simply need to make it remarkable as indicated by your own thoughts. We accept that what you are making are increasingly important and furthermore making others a lot more joyful than the staggering mass-selling items."
        aboutus.midtxtM3="The organizers of 3Design have over 20 years of demonstrating and business mastery and continually looking for and actualizing all the more energizing and intriguing answers for guests to the site. The nature of inventiveness and uniqueness are such qualities for us that consistently brings us new difficulties to guarantee that we constantly improve our administrations."
        aboutus.footerM="Copyright © 2021 | 3Design"
        arrayList?.add(aboutus)

        recyclerView?.adapter=About_Us_Apdater(arrayList!!)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment About_Us_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            About_Us_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}