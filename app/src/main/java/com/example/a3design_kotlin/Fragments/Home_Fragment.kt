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
import com.example.a3design_kotlin.Adapters.Home_Adapter
import com.example.a3design_kotlin.Model.Home_Model
import com.example.a3design_kotlin.R
import org.json.JSONException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //private final var JSON_URL:String="http://www.json-generator.com/api/json/get/cfIwTOEPAO?indent=2"
    private var requestQueue: RequestQueue?=null
    private var arrayList: ArrayList<Home_Model>?=null
    private var recyclerView1: RecyclerView?=null
    private var JSON_URL:String="http://www.json-generator.com/api/json/get/cjJDBoxIEi?indent=2"



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
        var view:View= inflater.inflate(R.layout.fragment_home_, container, false)
        recyclerView1=view.findViewById(R.id.Home_Recycler_View)
        recyclerView1?.layoutManager=LinearLayoutManager(context)
        arrayList=ArrayList()

        jsonrequest()
        return view
    }

    private fun jsonrequest() {
        val request=JsonObjectRequest(Request.Method.GET,JSON_URL,null,Response.Listener {
            response -> try {
            val jsonArray=response.getJSONArray("Home")
            for (i in 0 until jsonArray.length()){
                val jsonObject=jsonArray.getJSONObject(i)
                var home:Home_Model=Home_Model()
                home.Sub_headertxt1=(jsonObject.getString("Sub_headertxt1"))
                home.Desc_Text1=(jsonObject.getString("Desc_Text1"))
                home.Desc_Text2=(jsonObject.getString("Desc_Text2"))

                home.Header1=(jsonObject.getString("Header1"))

                home.Image_View1=(jsonObject.getString("Image_View1"))
                home.Desc_Text3=(jsonObject.getString("Desc_Text3"))
                home.Image_View2=(jsonObject.getString("Image_View2"))
                home.Desc_Text4=(jsonObject.getString("Desc_Text4"))

                home.Header2=(jsonObject.getString("Header2"))

                home.Text1=(jsonObject.getString("Text1"))
                home.Text2=(jsonObject.getString("Text2"))
                home.Text3=(jsonObject.getString("Text3"))
                home.Text4=(jsonObject.getString("Text4"))

                home.Header3=(jsonObject.getString("Header3"))

                home.Desc_Text5=(jsonObject.getString("Desc_Text5"))

                home.footer=(jsonObject.getString("footer"))

                arrayList?.add(home)

                recyclerView1?.adapter=Home_Adapter(arrayList!!)

            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
        },Response.ErrorListener {
            error -> error.printStackTrace()
        })

        requestQueue=Volley.newRequestQueue(context)
        requestQueue?.add(request)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}