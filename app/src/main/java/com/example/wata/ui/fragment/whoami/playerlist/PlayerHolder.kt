package com.example.wata.ui.fragment.whoami.playerlist

import androidx.recyclerview.widget.RecyclerView
import com.example.wata.databinding.ItemPlayerBinding
import com.example.wata.ui.models.PlayerWhoAmI

class PlayerHolder(
    private val binding: ItemPlayerBinding,
    private val onItemClick: (Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun onBind(player: PlayerWhoAmI) {
        with(binding) {
            etPlayerName.text = player.name
            root.setOnClickListener{
                onItemClick(player.id)
            }
        }
    }
}