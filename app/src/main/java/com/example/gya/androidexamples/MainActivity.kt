package com.example.gya.androidexamples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {

    private val recycler by lazy { findViewById<RecyclerView>(R.id.recycler) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = object : RecyclerView.Adapter<ExampleViewHolder>() {

            override fun getItemCount(): Int = Example.values().size

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                ExampleViewHolder.inflate(parent)

            override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
                val example = Example.values().reversed()[position]
                holder.bind(example)
                holder.itemView.setOnClickListener { example.action(holder.itemView.context) }
            }
        }
    }

}
