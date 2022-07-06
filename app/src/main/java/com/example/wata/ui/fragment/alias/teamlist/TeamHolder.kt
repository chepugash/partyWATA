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
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(team: Team) {
        with(binding) {
            tvItem.text = team.name
            root.setOnClickListener {
            var myDialog: AlertDialog.Builder = AlertDialog.Builder(tvItem.context)
                myDialog.setTitle("Введите название команды")
                val weightInput: EditText = EditText(tvItem.context)
                weightInput.setInputExtras(InputType.TYPE_CLASS_TEXT)
                myDialog.setView(weightInput)

                myDialog.setPositiveButton("OK",null)
                    myDialog.show().getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                        team.name= weightInput.text.toString()
                        tvItem.text = weightInput.text
                    }
            }
        }
    }
}
