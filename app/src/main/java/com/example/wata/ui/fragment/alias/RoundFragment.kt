package com.example.wata.ui.fragment.alias

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wata.R
import com.example.wata.databinding.FragmentAliasRoundBinding
import java.lang.Integer.max

class RoundFragment : Fragment(R.layout.fragment_alias_round) {
    private lateinit var chronometer: Chronometer
    private var _binding: FragmentAliasRoundBinding? = null
    private val binding get() = _binding!!

    private var game_score = 40
    private val points_for_win = 50

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAliasRoundBinding.bind(view)

        var score = 0
        var counter = 0
        var word = "Слово $counter"

        with(binding) {
            tvScore.text = score.toString()
            tvWord.text = word
            btnTrue.setOnClickListener {
                score++
                game_score++
                counter++
                word = "Слово $counter"
                tvScore.text = score.toString()
                tvWord.text = word
            }
            btnFalse.setOnClickListener {
                counter++
                score--
                game_score--
                tvScore.text = score.toString()
                word = "Слово $counter"
                tvWord.text = word
            }
        }

        initChronometer()
    }

    fun initChronometer() {
        chronometer = binding.chrTimer
        chronometer.isCountDown = true
        chronometer.base = SystemClock.elapsedRealtime() + 60000
        chronometer.start()
        chronometer.setOnChronometerTickListener {
            if (chronometer.text.toString() == "00:00") {
                chronometer.stop()
                if (game_score >= points_for_win) {
                    findNavController().navigate(R.id.action_roundFragment_to_winFragment)
                } else {
                    findNavController().navigate(R.id.action_roundFragment_to_preroundFragment)
                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}