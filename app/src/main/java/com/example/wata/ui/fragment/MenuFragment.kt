package com.example.wata.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentMenuBinding;
import com.example.wata.ui.fragment.alias.WinFragmentDirections
import com.example.wata.ui.fragment.alias.teamlist.TeamRepository
import com.example.wata.ui.repository.Repo

class MenuFragment : Fragment(R.layout.fragment_menu) {
    private var _binding : FragmentMenuBinding ?= null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMenuBinding.bind(view)
        val repo = Repo()
        repo.endOfPlay_for_repo()

        with(binding) {
            btnTeamsFrag.setOnClickListener {
                TeamRepository.clearTeams()
                findNavController().navigate(
                    R.id.action_menuFragment_to_teamsFragment
                )
            }
            btnPlayersFrag.setOnClickListener {
                findNavController().navigate(
                    R.id.action_menuFragment_to_playersFragment
                )
            }
            btnPlayers2Frag.setOnClickListener {
                findNavController().navigate(
                    R.id.action_menuFragment_to_playersFragment2
                )
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}