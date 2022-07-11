package com.example.wata.ui.fragment.tod

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentTodChoiceBinding
import com.example.wata.ui.fragment.tod.playerslisttod.QueuePlayers
import com.example.wata.ui.models.PlayerToD
import com.example.wata.ui.repository.Repo

class ChoiceFragment : Fragment(R.layout.fragment_tod_choice) {
    private var _binding: FragmentTodChoiceBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTodChoiceBinding.bind(view)

        with(binding) {

            val playerToD: PlayerToD = QueuePlayers.queue.poll()
            tvNamePlayerChoice.text = playerToD.name
            QueuePlayers.queue.add(playerToD)

            btnTruth.setOnClickListener {
                val playerName = playerToD.name
                val questionTOD = Repo.getQuestionTOD()
                val action = ChoiceFragmentDirections.actionChoiceFragmentToGameFragment2(btnTruth.text.toString(), questionTOD, playerName)
                findNavController().navigate(action)
            }
            btnDare.setOnClickListener {
                val playerName = playerToD.name
                val actionTOD = Repo.getActionTOD()
                val action = ChoiceFragmentDirections.actionChoiceFragmentToGameFragment2(btnDare.text.toString(), actionTOD, playerName)
                findNavController().navigate(action)
            }
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