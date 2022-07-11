package com.example.wata.ui.fragment.tod

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentTodPlayersBinding
import com.example.wata.ui.fragment.tod.playerslisttod.PlayerAdapter
import com.example.wata.ui.fragment.tod.playerslisttod.QueuePlayers
import com.example.wata.ui.fragment.tod.playerslisttod.PlayersRepository
import com.example.wata.ui.models.PlayerToD
import com.example.wata.ui.repository.Repo
import com.google.android.material.snackbar.Snackbar

class PlayersFragment : Fragment(R.layout.fragment_tod_players) {
    private var _binding: FragmentTodPlayersBinding? = null
    private val binding get() = _binding!!
    private var data: ArrayList<PlayerToD> = PlayersRepository.players
    private val adapter = PlayerAdapter(data) {
        index -> onDeletePlayer(index)
    }

    fun onDeletePlayer(index: Int) {
        data.removeAt(index)
        adapter.setPlayers(data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTodPlayersBinding.bind(view)
        initRcViewPlayers()
    }

    private fun initRcViewPlayers() {
        binding.apply {
            rvTodPlayers.adapter = adapter
            imAddPlayer.setOnClickListener {
                if (PlayersRepository.players.size < 10) {
                    val player = PlayerToD()
                    PlayersRepository.players.add(player)
                    adapter.notifyItemInserted(PlayersRepository.players.size)
                }
            }
            imPlayTod.setOnClickListener {
                if (adapter.playersToDList.size < 2) {
                    alertDialogLessThanTwoPlayers()
                } else if (checkPlayerName()) {
                    alertDialogNamelessPlayers()
                } else {
                    QueuePlayers.initQueue()
                    findNavController().navigate(
                        R.id.action_playersFragment2_to_choiceFragment
                    )
                }

            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    Repo.endOfPlay_for_repo()
                    QueuePlayers.clearQueue()
                    findNavController().navigate(R.id.menuFragment)

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

    private fun alertDialogLessThanTwoPlayers() {
        val builder = this.context?.let { AlertDialog.Builder(it) }
        builder?.setTitle("Внимание!!")
        builder?.setMessage("Чтобы начать игру должно быть минимум 2 игроков")
        builder?.setNegativeButton("OK") { _, _ -> }
        builder?.show()
    }

    private fun alertDialogNamelessPlayers() {
        val builder = this.context?.let { AlertDialog.Builder(it) }
        builder?.setTitle("Внимание!!")
        builder?.setMessage("Для начала игры необходимо дать имена всем игрокам")
        builder?.setNegativeButton("OK") { _, _ -> }
        builder?.show()
    }

    private fun checkPlayerName(): Boolean {
        for (i in 0 until adapter.playersToDList.size) {
            if (adapter.playersToDList[i].name == "") {
                return true
            }
        }
        return false
    }

}