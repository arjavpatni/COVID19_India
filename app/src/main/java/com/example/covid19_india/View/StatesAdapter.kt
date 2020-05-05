package com.example.covid19_india.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19_india.Model.States
import com.example.covid19_india.R
import kotlinx.android.synthetic.main.activity_main.view.*

class StatesAdapter(val context: Context, var data: MutableList<States>): RecyclerView.Adapter<StatesAdapter.StatesViewHolder>() {

    class StatesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.name)
        val cases = itemView.findViewById<TextView>(R.id.cases)
        fun bind(item: States) {
            name?.text = item.name
            cases?.text = item.cases
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatesViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.state_layout, parent, false)
        return StatesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: StatesViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

}