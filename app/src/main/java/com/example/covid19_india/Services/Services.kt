package com.example.covid19_india.Services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.covid19_india.Model.India
import com.example.covid19_india.Model.States
import com.example.covid19_india.Utilities.STATEWISE_URL
import org.json.JSONException

object Services {

    val states = mutableListOf<States>()
    lateinit var india: India

    fun getIndia(context: Context, complete: (Boolean) -> Unit){
        val url = "${STATEWISE_URL}"
        val indRequest = object : JsonObjectRequest(Method.GET, url, null, Response.Listener {
            response ->
            try {
                val indBody = response.getJSONArray("case_time_series")
                val item = indBody.getJSONObject(indBody.length()-1)
                val c_no = item.getString("totalconfirmed")
                val r_no = item.getString("totalrecovered")
                val d_no = item.getString("totaldeceased")
                val a_no = c_no.toInt() - r_no.toInt()
                india = India(c_no,a_no.toString(),r_no,d_no)
                complete(true)
            }catch (e: JSONException){
                Log.d("JSON",e.localizedMessage)
                complete(false)
            }
        },Response.ErrorListener {
            it.printStackTrace()
            Log.d("ERROR","${it.networkResponse}")
            complete(false)
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }
        Volley.newRequestQueue(context).add(indRequest)
    }

    fun getStates(context: Context, complete: (Boolean)-> Unit){
        val url = "${STATEWISE_URL}"
        val statesrequest = object : JsonObjectRequest(Method.GET, url, null, Response.Listener {
            response ->
            try {
                val stateBody = response.getJSONArray("statewise")
                for(i in 1 until stateBody.length()){
                    val item = stateBody.getJSONObject(i)
                    val name = item.getString("state")
                    val cases = item.getString("confirmed")
                    val newState = States(name,cases)
                    states.add(newState)
                    complete(true)
                }
            }catch (e: JSONException){
                Log.d("JSON", e.localizedMessage)
                complete(false)
            }
        },Response.ErrorListener {
            it.printStackTrace()
            Log.d("ERROR","${it.networkResponse}")
            complete(false)
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }
        Volley.newRequestQueue(context).add(statesrequest)
    }
}