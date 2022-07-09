package com.example.wata.ui.fragment.alias

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wata.R
import com.example.wata.databinding.FragmentAliasRoundBinding
import com.example.wata.databinding.FragmentAliasWinBinding
import com.example.wata.ui.fragment.alias.teamlist.TeamRepository
import kotlin.reflect.KProperty

class WinFragment : Fragment(R.layout.fragment_alias_win) {
    private var _binding: FragmentAliasWinBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAliasWinBinding.bind(view)

        with(binding) {
            val args by navArgs<WinFragmentArgs>()
            tvTeam.text = TeamRepository.teams[args.teamId].name

            btnMenu.setOnClickListener {
                findNavController().navigate(R.id.action_winFragment_to_menuFragment)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

