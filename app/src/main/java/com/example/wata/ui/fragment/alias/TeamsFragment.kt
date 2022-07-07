package com.example.wata.ui.fragment.alias

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
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
            val weightInput = EditText(activity)
            weightInput.inputType = InputType.TYPE_CLASS_TEXT
            val myDialog: AlertDialog = AlertDialog.Builder(activity)
                .setTitle("Введите название команды:")
                .setView(weightInput)
                .setPositiveButton("OK") { _, _ ->
                    TeamRepository.teams[it].name = weightInput.text.toString()
                    adapter?.notifyDataSetChanged()
                }
                .setNegativeButton("Удалить команду") { _, _ ->
                    if (TeamRepository.teams.size > 2) {
                        TeamRepository.teams.removeAt(it)
                        adapter?.notifyDataSetChanged()
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

}
