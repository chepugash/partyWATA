package com.example.wata.ui.fragment.alias

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentAliasCountdownBinding
import com.example.wata.databinding.FragmentAliasRoundBinding

class CountdownFragment : Fragment(R.layout.fragment_alias_countdown) {
    private var _binding: FragmentAliasCountdownBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAliasCountdownBinding.bind(view)

        with(binding) {
            object : CountDownTimer(4000, 1000) {
                override fun onTick(milliseconds: Long) {
                    val s: Long = milliseconds % 60000 / 1000
                    tvCountdown.text = String.format(s.toString())
                }
                override fun onFinish() {
                    findNavController().navigate(R.id.action_countdownFragment_to_roundFragment)
                }
            }.start()
        }
    }
}