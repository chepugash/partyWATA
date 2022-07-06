package com.example.wata.ui.fragment.whoami

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentWhoamiPlayersBinding

class PlayersFragment : Fragment(R.layout.fragment_whoami_players) {

    private var _binding: FragmentWhoamiPlayersBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Переворот экрана в вертикальную ориентацию
        if (savedInstanceState == null) {
            activity?.apply {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }

        _binding = FragmentWhoamiPlayersBinding.bind(view)

        with(binding) {
            btnPlayerStart.setOnClickListener {
                findNavController().navigate(
                    R.id.action_playersFragment_to_gameFragment
                )
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}