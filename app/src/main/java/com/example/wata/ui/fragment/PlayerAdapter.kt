package com.example.wata.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wata.R
import com.example.wata.ui.models.Player

class PlayerAdapter: RecyclerView.Adapter<PlayerHolder>() {

    private val playersList = ArrayList<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bind(playersList[position])
    }

    override fun getItemCount(): Int {
        return playersList.size
    }

    fun addPlayer(player: Player) {
        playersList.add(player)
        notifyDataSetChanged()
    }

}