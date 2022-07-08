package com.example.wata.ui.fragment.alias.teamlist

import androidx.recyclerview.widget.RecyclerView
import com.example.wata.databinding.ItemTeamsBinding

class TeamHolder(
    private val binding: ItemTeamsBinding,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(team: Team) {
        with(binding) {
            tvItem.text = team.name
            root.setOnClickListener {
                onItemClick(team.id)
            }
        }
    }
}
