package com.example.findouple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(private val cards: List<Card>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]
        if (card.isRevealed) {
            holder.imageView.setImageResource(getImageResource(card.symbol))
        } else {
            holder.imageView.setImageResource(R.drawable.ic_launcher_background)
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    fun flipCard(position: Int) {
        val card = cards[position]
        card.isRevealed = !card.isRevealed
        notifyItemChanged(position)
    }

    private fun getImageResource(symbol: Char): Int {
        return when (symbol.toUpperCase()) {
            'A' -> R.drawable.baseline_color_lens_24
            'B' -> R.drawable.baseline_dark_mode_24
            'C' -> R.drawable.baseline_directions_run_24
            // Добавьте другие символы и соответствующие им ресурсы изображений
            else -> R.drawable.ic_launcher_background // Используйте дефолтное изображение для неизвестных символов
        }
    }
}
