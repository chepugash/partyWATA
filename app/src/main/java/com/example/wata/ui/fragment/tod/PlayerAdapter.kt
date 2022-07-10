package com.example.wata.ui.fragment.tod

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wata.R
import com.example.wata.databinding.ItemPlayerTodBinding
import com.example.wata.ui.models.PlayerToD
import kotlin.collections.ArrayList

class PlayerAdapter(
    val playersToDList: ArrayList<PlayerToD> = PlayersRepository.players
) : RecyclerView.Adapter<PlayerHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerHolder = PlayerHolder(
        ItemPlayerTodBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.onBind(playersToDList[position])
    }

    override fun getItemCount(): Int {
        return playersToDList.size
    }

    fun addPlayer(player: PlayerToD) {
        playersToDList.add(player)
        notifyDataSetChanged()
    }

}