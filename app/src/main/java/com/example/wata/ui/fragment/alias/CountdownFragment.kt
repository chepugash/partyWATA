package com.example.wata.ui.fragment.alias

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentAliasCountdownBinding
import com.example.wata.databinding.FragmentAliasRoundBinding
import com.example.wata.ui.fragment.alias.teamlist.TeamRepository

class CountdownFragment : Fragment(R.layout.fragment_alias_countdown) {
    private var _binding: FragmentAliasCountdownBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAliasCountdownBinding.bind(view)

        with(binding) {
            val team = TeamRepository.teams[0]
            val round_time = 15
            val point_for_win = 50
            val round = 1
            tvTeam.text = team.name
            object : CountDownTimer(4000, 1000) {
                override fun onTick(milliseconds: Long) {
                    val s: Long = milliseconds % 60000 / 1000
                    tvCountdown.text = String.format(s.toString())
                }
                override fun onFinish() {
                    val action = CountdownFragmentDirections.actionCountdownFragmentToRoundFragment(team.id, round_time, point_for_win, round)
                    binding.root.findNavController().navigate(action)
                }
            }.start()
        }
    }
}