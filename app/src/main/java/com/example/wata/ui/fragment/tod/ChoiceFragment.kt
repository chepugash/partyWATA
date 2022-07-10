package com.example.wata.ui.fragment.tod

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentTodChoiceBinding
import com.example.wata.ui.fragment.tod.resources.DareRepository
import com.example.wata.ui.fragment.tod.resources.TruthRepository
import com.example.wata.ui.models.PlayerToD

class ChoiceFragment : Fragment(R.layout.fragment_tod_choice) {
    private var _binding: FragmentTodChoiceBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTodChoiceBinding.bind(view)
        with(binding) {
            btnTruth.setOnClickListener {
                val randomDare: Int = (0 until TruthRepository.truthList.size).random()
                val action = ChoiceFragmentDirections.actionChoiceFragmentToGameFragment2(btnTruth.text.toString(), randomDare)
                findNavController().navigate(action)
            }
            btnDare.setOnClickListener {
                val randomDare: Int = (0 until DareRepository.dareList.size).random()
                val action = ChoiceFragmentDirections.actionChoiceFragmentToGameFragment2(btnDare.text.toString(), randomDare)
                findNavController().navigate(action)
            }
            val playerToD: PlayerToD = PlayersRepository.players[0]
            tvNamePlayerChoice.text = PlayersRepository.players[0].name
            PlayersRepository.players.removeAt(0)
            PlayersRepository.players.add(playerToD)

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.playersFragment2)
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