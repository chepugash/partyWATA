package com.example.wata.ui.fragment.whoami

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentWhoamiResultsBinding
import com.example.wata.ui.fragment.whoami.playerlist.PlayerRepository

class ResultsFragment : Fragment(R.layout.fragment_whoami_results) {

    private var _binding: FragmentWhoamiResultsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentWhoamiResultsBinding.bind(view)

        with(binding){

            // ВЫВОД ИГРОКОВ
            for(i in 0 until PlayerRepository.players.count()){
                tvPlayer.text = ("" + tvPlayer.text + "\n" + PlayerRepository.players[i].name)
            }

            // ВОЗВРАЩЕНИЕ К ИГРОКАМ
            btnRestart.setOnClickListener {
                findNavController().navigate(
                    R.id.action_resultsFragment_to_playersFragment
                )
            }

            // ВОЗВРАЩЕНИЕ В МЕНЮ
            btnExitMenu.setOnClickListener {
                findNavController().navigate(
                    R.id.action_resultsFragment_to_menuFragment
                )
            }
        }

        // Переворот экрана в вертикальную ориентацию
        if (savedInstanceState == null) {
            activity?.apply {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }

        // ПЕРЕХОД В PlayerFragment ПРИ НАЖАТИИ НА BACK
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(
                    R.id.action_resultsFragment_to_playersFragment
                )
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}