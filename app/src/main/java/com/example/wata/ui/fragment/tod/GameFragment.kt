package com.example.wata.ui.fragment.tod

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wata.R
import com.example.wata.databinding.FragmentTodGameBinding
import com.example.wata.ui.fragment.tod.resources.DareRepository
import com.example.wata.ui.fragment.tod.resources.TruthRepository

class GameFragment : Fragment(R.layout.fragment_tod_game) {
    private var _binding: FragmentTodGameBinding? = null
    private val binding get() = _binding!!
    private val dareString = "Действие"
    private val truthString = "Правда"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTodGameBinding.bind(view)

        with(binding) {
            val args by navArgs<GameFragmentArgs>()
            tvTitle.text = args.truthOrDare
            if (tvTitle.text.equals(truthString)) {
                tvTaskOrQuestion.text = TruthRepository.truthList[args.random]
            } else {
                tvTaskOrQuestion.text = DareRepository.dareList[args.random]
            }

            imRefresh.setOnClickListener {
                refresh(tvTitle.text.toString())
            }
            imPlayTod.setOnClickListener {
                findNavController().navigate(R.id.choiceFragment)
            }
            tvNamePlayerGame.text = PlayersRepository.players[PlayersRepository.players.size - 1].name
        }
    }

    override fun onDestroy() {
        _binding= null
        super.onDestroy()
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

    private fun refresh(choice: String) {
        if (choice == dareString) {
            binding.tvTaskOrQuestion.text = DareRepository.dareList[(0 until DareRepository.dareList.size).random()]
        } else {
            binding.tvTaskOrQuestion.text = TruthRepository.truthList[(0 until TruthRepository.truthList.size).random()]
        }
    }

}