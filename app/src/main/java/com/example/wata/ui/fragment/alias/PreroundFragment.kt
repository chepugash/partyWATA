package com.example.wata.ui.fragment.alias

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wata.R
import com.example.wata.databinding.FragmentAliasPreroundBinding
import com.example.wata.ui.fragment.alias.teamlist.TeamRepository

class PreroundFragment : Fragment(R.layout.fragment_alias_preround) {
    private var _binding: FragmentAliasPreroundBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAliasPreroundBinding.bind(view)

        val args by navArgs<PreroundFragmentArgs>()

        var team_id = args.teamId
        val round_time = args.roundTime
        val points_for_win = args.pointsForWin
        val round_score = args.roundScore
        var round = args.round

        with(binding) {
            if (team_id == 99) {
                team_id = 0
            } else if (team_id == TeamRepository.teams.count() - 1) {
                TeamRepository.teams[team_id].points += round_score

                // check winner
                var max_points = 0
                var counter = 0
                var max_pointer_id = 0
                for (i in 0 until TeamRepository.teams.count()) {
                    if (TeamRepository.teams[i].points >= points_for_win && TeamRepository.teams[i].points > max_points) {
                        counter = 1
                        max_pointer_id = TeamRepository.teams[i].id
                        max_points = TeamRepository.teams[i].points
                    } else if (TeamRepository.teams[i].points >= points_for_win && TeamRepository.teams[i].points == max_points) {
                        counter++
                    }
                }
                if (counter == 1) {
                    val action = PreroundFragmentDirections.actionPreroundFragmentToWinFragment(
                        max_pointer_id
                    )
                    binding.root.findNavController().navigate(action)
                }
                // winner checked

                team_id = 0
                round += 1

            } else {
                TeamRepository.teams[team_id].points += round_score
                team_id += 1
            }
            tvTeamPoints.text = TeamRepository.teams[team_id].points.toString()
            tvTeamInRound.text = TeamRepository.teams[team_id].name
            tvRound.text = round.toString()
            tvPointsForWin.text = points_for_win.toString()

            btnStart.setOnClickListener {
                val action = PreroundFragmentDirections.actionPreroundFragmentToRoundFragment(team_id, round_time, points_for_win, round)
                binding.root.findNavController().navigate(action)
            }
        }

    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}