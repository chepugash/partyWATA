package com.example.wata.ui.fragment.tod.playerslisttod

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.wata.databinding.ItemPlayerTodBinding
import com.example.wata.ui.models.PlayerToD

class PlayerHolder(
    private val binding: ItemPlayerTodBinding,
    private val onPlayerDelete: (Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun onBind(playerToD: PlayerToD, index: Int) {
        with(binding) {
            etPlayerName.setText(playerToD.name)
            etPlayerName.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    playerToD.name = etPlayerName.text.toString()
                }
            })

            imDeletePlayer.setOnClickListener {
                onPlayerDelete(index)
            }
        }
    }

}