package com.example.wata.ui.fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.wata.databinding.ItemPlayerBinding
import com.example.wata.ui.models.Player

class PlayerHolder(item: View): RecyclerView.ViewHolder(item) {
    private val binding = ItemPlayerBinding.bind(item)

    fun bind(player: Player) = with(binding) {

    }

}