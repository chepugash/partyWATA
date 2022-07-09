package com.example.wata.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentMenuBinding
import com.example.wata.ui.repository.DataBaseCommand


class MenuFragment : Fragment(R.layout.fragment_menu) {
    private var _binding : FragmentMenuBinding ?= null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMenuBinding.bind(view)
        with(binding) {

            btnTeamsFrag.setOnClickListener {
                val dbHandler = DataBaseCommand.WordsDBOpenHelper( requireContext(), null)
                val cursor = dbHandler.getName(1)
                Log.e("plssss", "$cursor")
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
    fun find() {
        id
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}