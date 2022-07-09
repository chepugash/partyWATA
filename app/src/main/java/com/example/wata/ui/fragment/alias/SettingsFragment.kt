package com.example.wata.ui.fragment.alias

import android.os.Bundle;
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentAliasSettingsBinding
import com.example.wata.ui.fragment.alias.teamlist.TeamRepository

class SettingsFragment : Fragment(R.layout.fragment_alias_settings) {

    private var _binding: FragmentAliasSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAliasSettingsBinding.bind(view)
        with(binding) {
            seekBarTime.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    tvTime.text = progress.toString()
                }

                override fun onStartTrackingTouch(seekBarTime: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })
            seekBarWord.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    tvCounter.text = progress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })
            ibtnLeft.setOnClickListener {
                findNavController().navigate(
                    R.id.action_settingsFragment_to_teamsFragment
                )
            }
            btnContinue.setOnClickListener {
                val action = SettingsFragmentDirections.actionSettingsFragmentToPreroundFragment(
                    -1,
                    binding.tvTime.text.toString().toInt(),
                    binding.tvCounter.text.toString().toInt(),
                    0,
                    1
                )
                binding.root.findNavController().navigate(action)
            }
        }
    }

}
