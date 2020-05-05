package com.example.covid19_india.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19_india.R
import com.example.covid19_india.Services.Services
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var statesAdapter : StatesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statesAdapter = StatesAdapter(this, Services.states)
        states_view.adapter = statesAdapter
        val layoutManager = LinearLayoutManager(this)
        states_view.layoutManager = layoutManager

        Services.getStates(this){ complete->
            if(complete){
                statesAdapter.notifyDataSetChanged()
            }
        }
        Services.getIndia(this){
            if(it){
                findViewById<TextView>(R.id.a_no).text = Services.india.a_no
                findViewById<TextView>(R.id.c_no).text = Services.india.c_no
                findViewById<TextView>(R.id.r_no).text = Services.india.r_no
                findViewById<TextView>(R.id.d_no).text = Services.india.d_no
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }
}
