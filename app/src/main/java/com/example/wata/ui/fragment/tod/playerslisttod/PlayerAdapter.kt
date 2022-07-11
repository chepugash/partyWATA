package com.example.wata.ui.fragment.tod.playerslisttod

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wata.databinding.ItemPlayerTodBinding
import com.example.wata.ui.models.PlayerToD
import kotlin.collections.ArrayList

class PlayerAdapter(
    val playersToDList: ArrayList<PlayerToD>,
    private val onPlayerDelete: (Int) -> Unit
) : RecyclerView.Adapter<PlayerHolder>() {

    private var listPlayers = playersToDList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerHolder = PlayerHolder(
        binding = ItemPlayerTodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onPlayerDelete
    )

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.onBind(playersToDList[position], position)
    }

    override fun getItemCount(): Int {
        return listPlayers.size
    }

    fun setPlayers(players: ArrayList<PlayerToD>) {
        listPlayers = players
        notifyDataSetChanged()
    }

}