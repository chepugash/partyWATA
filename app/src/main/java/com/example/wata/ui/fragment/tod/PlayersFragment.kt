package com.example.wata.ui.fragment.tod

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentTodPlayersBinding
import com.example.wata.ui.models.PlayerToD

class PlayersFragment : Fragment(R.layout.fragment_tod_players) {
    private var _binding: FragmentTodPlayersBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PlayerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTodPlayersBinding.bind(view)
        adapter = PlayerAdapter(PlayersRepository.players)
        initRcViewPlayers()
    }

    private fun initRcViewPlayers() {
        binding.apply {
            rvTodPlayers.adapter = adapter

            if (adapter.playersToDList.size > 7) {
                val params: ViewGroup.LayoutParams = binding.rvTodPlayers.layoutParams
                params.height = 0
                binding.rvTodPlayers.layoutParams = params
            }

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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}