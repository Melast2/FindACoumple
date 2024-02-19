package com.example.findouple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), CardAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 4)
        adapter = CardAdapter(createCards(), this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        adapter.flipCard(position)
    }

    private fun createCards(): List<Card> {
        val symbols = listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L')
        val cards = mutableListOf<Card>()
        for (i in 0 until 12) {
            cards.add(Card(i, symbols[i / 2]))
        }
        cards.shuffle()
        return cards
    }
}