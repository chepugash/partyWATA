package com.example.wata.ui.fragment.whoami

import android.app.AlertDialog
import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentWhoamiGameBinding

class GameFragment : Fragment(R.layout.fragment_whoami_game) {

    private var binding: FragmentWhoamiGameBinding? = null
    lateinit var gameText: TextView
    lateinit var pause: Button
    lateinit var btnExit: Button
    lateinit var btnContinue: Button
    lateinit var playerText: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentWhoamiGameBinding.bind(view)
        gameText = view.findViewById(R.id.tv_game_text)
        pause = view.findViewById(R.id.bnt_pause)
        btnExit = view.findViewById(R.id.btn_exit)
        btnContinue = view.findViewById(R.id.btn_continue)
        playerText = view.findViewById(R.id.tv_player_text)

        // Переворот экрана в горизонтальную ориентацию
        if (savedInstanceState == null) {
            activity?.apply {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        }

        with(binding) {
            pause.setOnClickListener {

                pause.visibility = View.INVISIBLE
                btnContinue.visibility = View.VISIBLE
                btnExit.visibility = View.VISIBLE
                playerText.visibility = View.INVISIBLE

                val gameTextPause = gameText.text
                gameText.text = "Вы действительно хотите выйти?"

                btnExit.setOnClickListener {
                    findNavController().navigate(
                        R.id.action_gameFragment_to_playersFragment
                    )
                }

                btnContinue.setOnClickListener {
                    btnExit.visibility = View.INVISIBLE
                    btnContinue.visibility = View.INVISIBLE
                    pause.visibility = View.VISIBLE
                    playerText.visibility = View.VISIBLE
                    gameText.text = gameTextPause
                }
            }
        }

        // Запуск таймера подготовки
        object : CountDownTimer(4000, 1000) {
            override fun onTick(milliseconds: Long) {
                val s: Long = milliseconds % 60000 / 1000
                gameText.text = String.format(s.toString())
            }

            override fun onFinish() {

                gameText.text = "Железный человек"

            }
        }.start()




        // ПЕРЕХОД НА НОВЫЙ PlayerFragment ПРИ НАЖАТИИ НА BACK
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(
                    R.id.action_gameFragment_to_playersFragment
                )
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()

    }
}