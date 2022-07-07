package com.example.wata.ui.fragment.alias.teamlist

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.text.InputType
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.wata.databinding.ItemTeamsBinding
import com.example.wata.ui.fragment.alias.TeamsFragment

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
