package com.example.wata.ui.fragment.alias

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.wata.R
import com.example.wata.databinding.FragmentAliasRoundBinding

class RoundFragment : Fragment(R.layout.fragment_alias_round) {
    private var _binding: FragmentAliasRoundBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAliasRoundBinding.bind(view)

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}