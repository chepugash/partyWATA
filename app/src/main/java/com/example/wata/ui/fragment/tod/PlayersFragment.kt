package com.example.wata.ui.fragment.tod

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.wata.R
import com.example.wata.databinding.FragmentTodPlayersBinding

class PlayersFragment : Fragment(R.layout.fragment_tod_players) {
    private var _binding: FragmentTodPlayersBinding? = null
    private val binding get() = _binding!!
//    private val adapter = PlayerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // window.statusBarColor = ContextCompat.getColor(this, R.color.colorName)
        _binding = FragmentTodPlayersBinding.bind(view)
//        initRcViewPlayers()
    }

//    private fun initRcViewPlayers() {
//        adapter.addPlayer(Player())
//        adapter.addPlayer(Player())
//        binding.apply {
//            rvTodPlayers.adapter = adapter
//            imAddPlayer.setOnClickListener {
//                val player = Player()
//                adapter.addPlayer(player)
//            }
//        }
//    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}