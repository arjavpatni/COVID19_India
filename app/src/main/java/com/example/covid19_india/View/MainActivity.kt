package com.example.covid19_india.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19_india.R
import com.example.covid19_india.Services.Services
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.state_layout.*

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
        name.setOnClickListener(){
            val intent = Intent(this, DistrictsActivity::class.java)
            intent.putExtra("state_name",name.text.toString())
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }
}
