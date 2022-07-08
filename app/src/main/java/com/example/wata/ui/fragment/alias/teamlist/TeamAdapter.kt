package com.example.wata.ui.fragment.alias.teamlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wata.databinding.ItemTeamsBinding

class TeamAdapter (
    private var list: List<Team>,
    private val onItemClick: (Int) -> Unit,
) : RecyclerView.Adapter<TeamHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamHolder = TeamHolder(
        binding = ItemTeamsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onItemClick = onItemClick
    )

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}
