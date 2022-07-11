package com.example.wata.ui.fragment.whoami.playerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wata.databinding.ItemPlayerBinding
import com.example.wata.ui.models.PlayerWhoAmI

class PlayerAdapter(
    private val list: ArrayList<PlayerWhoAmI>,
    private val onItemClick: (Int) -> Unit,
): RecyclerView.Adapter<PlayerHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerHolder = PlayerHolder(
        binding = ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onItemClick = onItemClick
    )

    override fun onBindViewHolder(
        holder: PlayerHolder,
        position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}