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

        var teamId = args.teamId
        val roundTime = args.roundTime
        val pointsForWin = args.pointsForWin
        val roundScore = args.roundScore
        var round = args.round

        with(binding) {

            if (teamId == -1) {
                teamId = TeamRepository.teams[0].id
            } else if (teamId < TeamRepository.teams[TeamRepository.teams.count() - 1].id) {
                for (i in 0 until TeamRepository.teams.count()) {
                    if (teamId == TeamRepository.teams[i].id) {
                        TeamRepository.teams[i].points += roundScore
                        teamId = TeamRepository.teams[i + 1].id
                    }
                    break
                }
            } else if (teamId == TeamRepository.teams[TeamRepository.teams.count() - 1].id) {
                round++
                TeamRepository.teams[TeamRepository.teams.count() - 1].points += roundScore
                teamId = TeamRepository.teams[0].id

                var counter = 0         // сколько команд с макс очками
                var maxPoints = 0       // макс очки

                for (i in 0 until TeamRepository.teams.count()) {
                    if (TeamRepository.teams[i].points > maxPoints &&
                        TeamRepository.teams[i].points >= pointsForWin) {
                        maxPoints = TeamRepository.teams[i].points
                        counter = 1
                    } else if (TeamRepository.teams[i].points == maxPoints &&
                        TeamRepository.teams[i].points >= pointsForWin) {
                        counter++
                    }
                }

                if (counter == 1) {
                    for (i in 0 until TeamRepository.teams.count()) {
                        if (TeamRepository.teams[i].points == maxPoints) {
                            val action = PreroundFragmentDirections
                                .actionPreroundFragmentToWinFragment(
                                    TeamRepository.teams[i].id
                                )
                            root.findNavController().navigate(action)
                        }
                    }
                }

            }




            tvRound.text = "Раунд: $round"
            tvPointsForWin.text = pointsForWin.toString()
            tvTeamFirst.text = "${TeamRepository.teams[0].name}: ${TeamRepository.teams[0].points}"
            tvTeamSecond.text = "${TeamRepository.teams[1].name}: ${TeamRepository.teams[1].points}"
            if (TeamRepository.teams.size >= 3) {
                tvTeamThird.text =
                    "${TeamRepository.teams[2].name}: ${TeamRepository.teams[2].points}"
                tvTeamThird.visibility = View.VISIBLE
            }
            if (TeamRepository.teams.size >= 4) {
                tvTeamFourth.text =
                    "${TeamRepository.teams[3].name}: ${TeamRepository.teams[3].points}"
                tvTeamFourth.visibility = View.VISIBLE
            }
            btnStart.setOnClickListener {
                val action = PreroundFragmentDirections.actionPreroundFragmentToRoundFragment(
                    teamId,
                    roundTime,
                    pointsForWin,
                    round
                )
                root.findNavController().navigate(action)
            }
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
