package com.example.wata.ui.fragment.alias

import android.os.Bundle
import android.os.CountDownTimer
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

        val team_id = 0
        val round_time = 10
        val points_for_win = 50
        val round = 1

        var score = 0

        var counter = Random.nextInt(0, 100)
        var word = "Слово $counter"

        with(binding) {
            object : CountDownTimer(4000, 1000) {
                override fun onTick(milliseconds: Long) {
                    val s: Long = milliseconds % 60000 / 1000
                    tvCountdown.text = String.format(s.toString())
                }
                override fun onFinish() {
                    ivBottom.visibility = View.VISIBLE
                    chrTimer.visibility = View.VISIBLE
                    tvWord.visibility = View.VISIBLE
                    btnTrue.visibility = View.VISIBLE
                    btnFalse.visibility = View.VISIBLE
                    tvCountdown.visibility = View.INVISIBLE
                    initChronometer(round_time)
                }
            }.start()

            tvScore.text = score.toString()
            tvWord.text = word
            tvTeam.text = TeamRepository.teams[team_id].name

            btnTrue.setOnClickListener {
                score++
                if (chrTimer.visibility == View.INVISIBLE) {
                    val action = RoundFragmentDirections.actionRoundFragmentToPreroundFragment(team_id, round_time, points_for_win, score, round)
                    binding.root.findNavController().navigate(action)
                } else {
                    counter = Random.nextInt(0, 100)
                    word = "Слово $counter"
                    tvScore.text = score.toString()
                    tvWord.text = word
                }
            }

            btnFalse.setOnClickListener {
                score--
                if (chrTimer.visibility == View.INVISIBLE) {
                    val action = RoundFragmentDirections.actionRoundFragmentToPreroundFragment(team_id, round_time, points_for_win, score, round)
                    binding.root.findNavController().navigate(action)
                } else {
                    counter = Random.nextInt(0, 100)
                    tvScore.text = score.toString()
                    word = "Слово $counter"
                    tvWord.text = word
                }
            }
        }
    }

    private fun initChronometer(round_time: Int) {
        chronometer = binding.chrTimer
        chronometer.isCountDown = true
        chronometer.base = SystemClock.elapsedRealtime() + round_time * 1000
        chronometer.start()
        chronometer.setOnChronometerTickListener {
            if (chronometer.text.toString() == "00:00" || chronometer.text.toString()[3] == ':') {
                chronometer.stop()
                chronometer.visibility = View.INVISIBLE
                binding.tvLastWord.visibility = View.VISIBLE
            }
        }
    }

    override fun onStop() {
        super.onStop()
        binding.chrTimer.stop()
    }

    override fun onResume() {
        super.onStart()
        binding.chrTimer.start()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}