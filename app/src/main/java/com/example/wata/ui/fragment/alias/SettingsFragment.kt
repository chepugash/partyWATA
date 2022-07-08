package com.example.wata.ui.fragment.alias

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentAliasPreroundBinding
import com.example.wata.databinding.FragmentAliasRoundBinding
import com.example.wata.databinding.FragmentAliasSettingsBinding
import com.example.wata.ui.fragment.alias.SettingsFragmentDirections.Companion.actionSettingsFragmentToPreroundFragment

class SettingsFragment : Fragment(R.layout.fragment_alias_settings) {
    private var _binding: FragmentAliasSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAliasSettingsBinding.bind(view)
        val action = SettingsFragmentDirections.actionSettingsFragmentToPreroundFragment(99, 5, 10, 0, 1)
        binding.root.findNavController().navigate(action)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}