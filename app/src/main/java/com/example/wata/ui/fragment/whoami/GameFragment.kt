package com.example.wata.ui.fragment.whoami

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wata.R
import com.example.wata.databinding.FragmentWhoamiGameBinding
import com.example.wata.ui.fragment.whoami.playerlist.PlayerRepository

class GameFragment : Fragment(R.layout.fragment_whoami_game) {

    private var binding: FragmentWhoamiGameBinding? = null
    lateinit var gameText: TextView
    lateinit var pause: Button
    lateinit var btnExit: Button
    lateinit var btnContinue: Button
    lateinit var playerText: TextView
    lateinit var guessedRight: Button
    lateinit var guessedWrong: Button
    lateinit var square: LinearLayout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentWhoamiGameBinding.bind(view)
        gameText = view.findViewById(R.id.tv_game_text)
        pause = view.findViewById(R.id.bnt_pause)
        btnExit = view.findViewById(R.id.btn_exit)
        btnContinue = view.findViewById(R.id.btn_continue)
        playerText = view.findViewById(R.id.tv_player_text)
        guessedRight = view.findViewById(R.id.btn_guessed_right)
        guessedWrong = view.findViewById(R.id.btn_guessed_wrong)
        square = view.findViewById(R.id.ll_square)

        guessedRight.visibility = View.INVISIBLE
        guessedWrong.visibility = View.INVISIBLE
        pause.visibility = View.INVISIBLE
        playerText.visibility = View.INVISIBLE
        square.visibility = View.INVISIBLE

        // Переворот экрана в горизонтальную ориентацию
        if (savedInstanceState == null) {
            activity?.apply {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        }

        // Пауза
        pause.setOnClickListener {
            pause.visibility = View.INVISIBLE
            btnContinue.visibility = View.VISIBLE
            btnExit.visibility = View.VISIBLE
            playerText.visibility = View.INVISIBLE
            guessedWrong.visibility = View.INVISIBLE
            guessedRight.visibility = View.INVISIBLE
            square.visibility = View.INVISIBLE

            val gameTextPause = gameText.text
            gameText.text = "Вы действительно хотите выйти?"

            btnExit.setOnClickListener {
                findNavController().navigate(
                    R.id.action_gameFragment_to_resultsFragment
                )
            }

            btnContinue.setOnClickListener {
                btnExit.visibility = View.INVISIBLE
                btnContinue.visibility = View.INVISIBLE
                pause.visibility = View.VISIBLE
                playerText.visibility = View.VISIBLE
                guessedWrong.visibility = View.VISIBLE
                guessedRight.visibility = View.VISIBLE
                gameText.text = gameTextPause
                square.visibility = View.VISIBLE
            }
        }

        while(PlayerRepository.players.isNotEmpty()){
            for(i in 0 until PlayerRepository.players.size){

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
                guessedRight.visibility = View.VISIBLE
                guessedWrong.visibility = View.VISIBLE
                playerText.visibility = View.VISIBLE
                pause.visibility = View.VISIBLE
                square.visibility = View.VISIBLE
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