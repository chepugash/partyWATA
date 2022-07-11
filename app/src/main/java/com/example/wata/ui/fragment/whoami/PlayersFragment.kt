package com.example.wata.ui.fragment.whoami

import android.app.AlertDialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentWhoamiPlayersBinding
import com.example.wata.ui.fragment.whoami.playerlist.PlayerAdapter
import com.example.wata.ui.fragment.whoami.resources.PlayerRepository
import com.example.wata.ui.fragment.whoami.resources.PlayerWinRepository
import com.example.wata.ui.fragment.whoami.resources.WordRepository
import com.example.wata.ui.models.PlayerWhoAmI
import kotlin.random.Random

class PlayersFragment : Fragment(R.layout.fragment_whoami_players) {

    private var _binding: FragmentWhoamiPlayersBinding? = null
    private val binding get() = _binding!!
    private var adapter: PlayerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentWhoamiPlayersBinding.bind(view)

        PlayerWinRepository.players.clear()
        PlayerRepository.players.clear()

        var rnds = Random(System.nanoTime())
        PlayerRepository.players.add(
            PlayerWhoAmI(
                0,
                "Игрок 1",
                WordRepository.wordList[(0 until WordRepository.wordList.size).random(rnds)]
            )
        )
        rnds = Random(System.nanoTime())
        PlayerRepository.players.add(
            PlayerWhoAmI(
                1,
                "Игрок 2",
                WordRepository.wordList[(0 until WordRepository.wordList.size).random(rnds)]
            )
        )
        initRcViewPlayers()

        // Переворот экрана в вертикальную ориентацию
        if (savedInstanceState == null) {
            activity?.apply {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }

        // ПЕРЕХОД В GameFragment
        with(binding) {
            btnPlayerStart.setOnClickListener {
                if (PlayerRepository.players.size < 2) {
                    Toast.makeText(
                        activity,
                        "Нужно 2 и более игроков",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    findNavController().navigate(
                        R.id.action_playersFragment_to_gameFragment
                    )
                }
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

        with(binding) {
            btnPlus.setOnClickListener {
                if (PlayerRepository.players.size < 10) {
                    val prevId: Int = PlayerRepository.players[PlayerRepository.players.size - 1].id
                    var idName: Int = prevId + 2
                    val rndsg = Random(System.nanoTime())
                    PlayerRepository.players.add(
                        PlayerWhoAmI(
                            prevId + 1,
                            "Игрок $idName",
                            WordRepository.wordList[(0 until WordRepository.wordList.size).random(
                                rndsg
                            )]
                        )
                    )
                    idName += 1
                    adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initRcViewPlayers() {
        adapter = PlayerAdapter(
            PlayerRepository.players
        ) {
            val id = it
            val weightInput = EditText(activity)
            weightInput.inputType = InputType.TYPE_CLASS_TEXT
            val myDialog: AlertDialog = AlertDialog.Builder(activity)
                .setTitle("Введите имя игрока:")
                .setView(weightInput)
                .setPositiveButton("OK") { _, _ ->
                    for (i in 0 until PlayerRepository.players.size) {
                        if (PlayerRepository.players[i].id == id) {
                            if (validate(weightInput.text.toString())) {
                                PlayerRepository.players[i].name = weightInput.text.toString()
                                adapter?.notifyDataSetChanged()
                                break
                            } else {
                                Toast.makeText(
                                    activity,
                                    "Некорректное количество символов",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
                .setNegativeButton("Удалить игрока") { _, _ ->
                    if (PlayerRepository.players.size > 2) {
                        for (i in 0 until PlayerRepository.players.size) {
                            if (PlayerRepository.players[i].id == id) {
                                PlayerRepository.players.remove(PlayerRepository.players[i])
                                adapter?.notifyDataSetChanged()
                                break
                            }
                        }
                    }
                }
                .create()
            myDialog.show()
        }
        binding.rvPlayer.adapter = adapter

        // ПЕРЕХОД НА НОВЫЙ menuFragment ПРИ НАЖАТИИ НА BACK
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(
                    R.id.action_playersFragment_to_menuFragment
                )
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

    private fun validate(name: String): Boolean {
        if (name.length in 1..20) {
            return true
        }
        return false
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}