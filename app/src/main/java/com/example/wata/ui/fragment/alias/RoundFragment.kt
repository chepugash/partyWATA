package com.example.wata.ui.fragment.alias

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wata.R
import com.example.wata.databinding.FragmentAliasRoundBinding
import com.example.wata.ui.fragment.alias.teamlist.TeamRepository
import kotlin.random.Random

class RoundFragment : Fragment(R.layout.fragment_alias_round) {
    private lateinit var chronometer: Chronometer
    private var _binding: FragmentAliasRoundBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAliasRoundBinding.bind(view)

        val args by navArgs<RoundFragmentArgs>()
        val team_id = args.teamId
        val round_time = args.roundTime
        val points_for_win = args.pointsForWin

        var score = 0

        var counter = Random.nextInt(0, 100)
        var word = "Слово $counter"

        with(binding) {
            tvScore.text = score.toString()
            tvWord.text = word
            tvTeam.text = TeamRepository.teams[team_id].name
            btnTrue.setOnClickListener {
                score++
                counter = Random.nextInt(0, 100)
                word = "Слово $counter"
                tvScore.text = score.toString()
                tvWord.text = word
            }
            btnFalse.setOnClickListener {
                counter = Random.nextInt(0, 100)
                score--
                tvScore.text = score.toString()
                word = "Слово $counter"
                tvWord.text = word
            }
        }

        initChronometer(team_id, round_time, points_for_win, score)
    }

    private fun initChronometer(team_id: Int, round_time: Int, points_for_win: Int, score: Int) {
        chronometer = binding.chrTimer
        chronometer.isCountDown = true
        chronometer.base = SystemClock.elapsedRealtime() + round_time * 1000
        chronometer.start()
        chronometer.setOnChronometerTickListener {
            if (chronometer.text.toString() == "00:00") {
                chronometer.stop()
                val action = RoundFragmentDirections.actionRoundFragmentToPreroundFragment(team_id, round_time, points_for_win, score)
                binding.root.findNavController().navigate(action)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}