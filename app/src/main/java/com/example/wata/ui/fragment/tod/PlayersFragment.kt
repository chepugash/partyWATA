package com.example.wata.ui.fragment.tod

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentTodPlayersBinding
import com.example.wata.ui.fragment.whoami.playerlist.PlayerAdapter
import com.example.wata.ui.models.PlayerToD

class PlayersFragment : Fragment(R.layout.fragment_tod_players) {
    private var _binding: FragmentTodPlayersBinding? = null
    private val binding get() = _binding!!
    private val adapter = PlayerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTodPlayersBinding.bind(view)
        initRcViewPlayers()
    }

    private fun initRcViewPlayers() {
        binding.apply {
            rvTodPlayers.adapter = adapter
            imAddPlayer.setOnClickListener {
                val player = PlayerToD("Игрок ${adapter.playersToDList.size + 1}")
                adapter.addPlayer(player)
            }
            imPlayTod.setOnClickListener {
                findNavController().navigate(
                    R.id.action_playersFragment2_to_choiceFragment
                )
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.menuFragment)
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