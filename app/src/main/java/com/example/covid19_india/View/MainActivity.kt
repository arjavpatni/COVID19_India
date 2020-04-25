package com.example.covid19_india.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19_india.R
import com.example.covid19_india.Services.StatesService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var statesAdapter : StatesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statesAdapter = StatesAdapter(this, StatesService.states)
        states_view.adapter = statesAdapter
        val layoutManager = LinearLayoutManager(this)
        states_view.layoutManager = layoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
