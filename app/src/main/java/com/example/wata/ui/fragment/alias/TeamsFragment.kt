package com.example.wata.ui.fragment.alias

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentAliasTeamsBinding
import com.example.wata.ui.fragment.alias.teamlist.Team
import com.example.wata.ui.fragment.alias.teamlist.TeamAdapter
import com.example.wata.ui.fragment.alias.teamlist.TeamRepository

class TeamsFragment : Fragment(R.layout.fragment_alias_teams) {
    private var _binding: FragmentAliasTeamsBinding? = null
    private val binding get() = _binding!!

    private var adapter: TeamAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAliasTeamsBinding.bind(view)
        initAdapter()
        with(binding) {
            ibtnSweetHome.setOnClickListener {
                findNavController().navigate(
                    R.id.action_teamsFragment_to_menuFragment
                )
            }
            btnPlus.setOnClickListener {
                if (TeamRepository.teams.size < 4) {
                    val prevId: Int = TeamRepository.teams[TeamRepository.teams.size - 1].id
                    TeamRepository.teams.add(Team(prevId + 1, "Новая команда", 0))
                    adapter?.notifyDataSetChanged()
                }
            }
            btnContinue.setOnClickListener {
                findNavController().navigate(
                    R.id.action_teamsFragment_to_settingsFragment
                )
            }
        }
    }

    private fun initAdapter() {
        adapter = TeamAdapter(
            TeamRepository.teams
        ) {
            val id = it
            val weightInput = EditText(activity)
            weightInput.inputType = InputType.TYPE_CLASS_TEXT
            val myDialog: AlertDialog = AlertDialog.Builder(activity)
                .setTitle("Введите название команды:")
                .setView(weightInput)
                .setPositiveButton("OK") { _, _ ->
                    for (i in 0 until TeamRepository.teams.size) {
                        if (TeamRepository.teams[i].id == id) {
                            if (validate(weightInput.text.toString())) {
                                TeamRepository.teams[i].name = weightInput.text.toString()
                                adapter?.notifyDataSetChanged()
                                break
                            } else {
                                Toast.makeText(
                                    activity,
                                    "Некорректное количество символов",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
                .setNegativeButton("Удалить команду") { _, _ ->
                    if (TeamRepository.teams.size > 2) {
                        for (i in 0 until TeamRepository.teams.size) {
                            if (TeamRepository.teams[i].id == id) {
                                TeamRepository.teams.remove(TeamRepository.teams[i])
                                adapter?.notifyDataSetChanged()
                                break
                            }
                        }
                    }
                }
                .create()
            myDialog.show()
        }
        binding.rvTeams.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validate(name: String): Boolean {
        if (name.length in 1..20) {
            return true
        }
        return false
    }

}
