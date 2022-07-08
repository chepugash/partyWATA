package com.example.wata.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentMenuBinding;

class MenuFragment : Fragment(R.layout.fragment_menu) {
    private var _binding : FragmentMenuBinding ?= null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMenuBinding.bind(view)

        with(binding) {
            btnTeamsFrag.setOnClickListener {
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}