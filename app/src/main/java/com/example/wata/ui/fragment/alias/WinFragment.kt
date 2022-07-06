package com.example.wata.ui.fragment.alias

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentAliasRoundBinding
import com.example.wata.databinding.FragmentAliasWinBinding

class WinFragment : Fragment(R.layout.fragment_alias_win) {
    private var _binding: FragmentAliasWinBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAliasWinBinding.bind(view)

        with(binding) {
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