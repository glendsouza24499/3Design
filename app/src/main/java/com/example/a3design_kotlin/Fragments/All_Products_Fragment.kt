package com.example.a3design_kotlin.Fragments

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.a3design_kotlin.Adapters.All_Products_Adapters
import com.example.a3design_kotlin.Model.All_Products_Model
import com.example.a3design_kotlin.R
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [All_Products_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class All_Products_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //to be defined in class here
    private var recyclerView:RecyclerView?=null
    private var gridLayoutManager:GridLayoutManager?=null
    val arrayList=ArrayList<All_Products_Model>()
    val displayList=ArrayList<All_Products_Model>()
    //private var JSON_URL:String="http://www.json-generator.com/api/json/get/cjMVqfIXoy?indent=2" // without url links  json genrator
    //private var JSON_URL:String="https://api.jsonbin.io/b/602ace98f460fe73a1972557"   //with url links from JSONbin.io
    //private var JSON_URL:String="http://www.json-generator.com/api/json/get/clitZsYPZu?indent=2"  //JSON GENERATOR LINK with urls test
    private var JSON_URL:String="http://www.json-generator.com/api/json/get/bOHxXVvwFu?indent=2" //JSON GENERATOR LINK with urls
    private var requestQueue: RequestQueue?=null


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
        var view:View= inflater.inflate(R.layout.fragment_all_products_, container, false)
        recyclerView=view.findViewById(R.id.All_Products_RecyclerView)
        gridLayoutManager=GridLayoutManager(context,2,LinearLayoutManager.VERTICAL,false)
        recyclerView?.layoutManager=gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        //arrayList= ArrayList()

        jsonrequest()

        //allProductsAdapters= All_Products_Adapters(context!!,arrayList!!)

        return view
    }

    private fun jsonrequest() {
        var request=JsonObjectRequest(Request.Method.GET,JSON_URL,null,Response.Listener { response ->
                try {
                        var jsonArray=response.getJSONArray("ALL_PRODUCTS")
                        for (i in 0 until jsonArray.length()){
                            var jsonObject=jsonArray.getJSONObject(i)
                                var allproducts= All_Products_Model()
                                allproducts.ImageM1=jsonObject.getString("ImageJ1")
                                allproducts.text_typeM1=jsonObject.getString("text_typeJ1")
                                allproducts.text_nameM1=jsonObject.getString("text_nameJ1")
                                allproducts.text_priceM1=jsonObject.getString("text_priceJ1")
                                allproducts.productdetail_urlM=jsonObject.getString("productdetail_urlJ")
                                displayList.add(allproducts)

                                recyclerView?.adapter=All_Products_Adapters(displayList)

                        }
                }catch (e:JSONException){
                    e.printStackTrace()
                }
            },Response.ErrorListener { error ->
            error.printStackTrace()
        })
        //recyclerView?.adapter=All_Products_Adapters(arrayList!!)

        requestQueue=Volley.newRequestQueue(context)
        requestQueue?.add(request)
    }


//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.navigation_menu,menu)
//        val searchItem=menu!!.findItem(R.id.search)
//
//        if (searchItem!=null){
//            val searchView=searchItem.actionView as SearchView //SearchView choose the appcompat
//            val editText=searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
//            editText.hint="Search..."
//
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//
//                    if (newText!!.isNotEmpty()){
//
//                        displayList.clear()
//                        val search=newText.toLowerCase(Locale.getDefault())
//                        arrayList.forEach {
//                            if (it.text_typeM1!!.toLowerCase(Locale.getDefault())!!.contains(search)){
//                                displayList.add(it)
//                            }
//                        }
//                        recyclerView?.adapter!!.notifyDataSetChanged()
//                    }
//                    else{
//                        displayList.clear()
//                        displayList.add(allproducts)
//                        recyclerView?.adapter!!.notifyDataSetChanged()
//                    }
//
//                    return true
//                }
//
//            })
//        }
//
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
//    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment All_Products_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            All_Products_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}