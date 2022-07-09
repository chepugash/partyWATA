package com.example.wata.ui.fragment.whoami

import android.app.AlertDialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentWhoamiPlayersBinding
import com.example.wata.ui.fragment.whoami.playerlist.PlayerAdapter
import com.example.wata.ui.fragment.whoami.playerlist.PlayerRepository
import com.example.wata.ui.models.PlayerWhoAmI

class PlayersFragment : Fragment(R.layout.fragment_whoami_players) {

    private var _binding: FragmentWhoamiPlayersBinding? = null
    private val binding get() = _binding!!
    private var adapter: PlayerAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentWhoamiPlayersBinding.bind(view)

        initRcViewPlayers()

        // Переворот экрана в вертикальную ориентацию
        if (savedInstanceState == null) {
            activity?.apply {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }

        _binding = FragmentWhoamiPlayersBinding.bind(view)

        // ПЕРЕХОД В GameFragment
        with(binding) {
            btnPlayerStart.setOnClickListener {
                findNavController().navigate(
                    R.id.action_playersFragment_to_gameFragment
                )
            }
        }

        // ПЕРЕХОД В МЕНЮ
        with(binding) {
            btnExit.setOnClickListener {
                findNavController().navigate(
                    R.id.action_playersFragment_to_menuFragment
                )
            }
        }
        with(binding){
            btnPlus.setOnClickListener {
                if (PlayerRepository.players.size < 10) {
                    val prevId: Int = PlayerRepository.players[PlayerRepository.players.size - 1].id
                    val idName: Int = prevId+2
                    PlayerRepository.players.add(PlayerWhoAmI(prevId + 1, "Игрок $idName", "Слово $idName"))
                    adapter?.notifyDataSetChanged()
                }
            }
        }
    }


    private fun initRcViewPlayers() {
        adapter = PlayerAdapter(
            PlayerRepository.players
        ){
            val weightInput = EditText(activity)
            weightInput.inputType = InputType.TYPE_CLASS_TEXT
            val myDialog: AlertDialog = AlertDialog.Builder(activity)
                .setTitle("Введите имя игрока:")
                .setView(weightInput)
                .setPositiveButton("OK") { _, _ ->
                    PlayerRepository.players[it].name = weightInput.text.toString()
                    adapter?.notifyDataSetChanged()
                }
                .setNegativeButton("Удалить игрока") { _, _ ->
                    if (PlayerRepository.players.size > 2) {
                        PlayerRepository.players.removeAt(it)
                        adapter?.notifyDataSetChanged()
                    }
                }
                .create()
            myDialog.show()
        }
        binding.rvPlayer.adapter = adapter

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}