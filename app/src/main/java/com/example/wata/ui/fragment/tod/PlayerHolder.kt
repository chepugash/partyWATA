package com.example.wata.ui.fragment.tod

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.wata.databinding.ItemPlayerTodBinding
import com.example.wata.ui.models.PlayerToD

class PlayerHolder(
    private val binding: ItemPlayerTodBinding
): RecyclerView.ViewHolder(binding.root) {

    fun onBind(playerToD: PlayerToD) {
        with(binding) {
            val defaultName: String = playerToD.name
            var newName: String
            etPlayerName.setText(defaultName)
            etPlayerName.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    newName = etPlayerName.text.toString()
                    if (newName != "") {
                        playerToD.name = newName
                    } else {
                        playerToD.name = defaultName
                    }
                }

            })

        }
    }

}