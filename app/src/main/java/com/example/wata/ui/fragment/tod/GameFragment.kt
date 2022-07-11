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
import com.example.wata.ui.repository.Repo

class GameFragment : Fragment(R.layout.fragment_tod_game) {
    private var _binding: FragmentTodGameBinding? = null
    private val binding get() = _binding!!
    private val dareString = "Действие"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTodGameBinding.bind(view)

        with(binding) {
            val args by navArgs<GameFragmentArgs>()
            tvTitleMode.text = args.truthOrDare
            tvTaskOrQuestion.text = args.questionsActions
            tvTitlePlayerName.text = args.playerName

            btnRefresh.setOnClickListener {
                refresh(tvTitleMode.text.toString())
            }
            btnContinue.setOnClickListener {
                findNavController().navigate(R.id.choiceFragment)
            }
            imPlayers.setOnClickListener {
                findNavController().navigate(R.id.playersFragment2)
            }
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
            binding.tvTaskOrQuestion.text = Repo.getActionTOD()
        } else {
            binding.tvTaskOrQuestion.text = Repo.getQuestionTOD()
        }
    }

}