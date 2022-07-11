package com.example.wata.ui.fragment.tod.playerslisttod

import com.example.wata.ui.models.PlayerToD
import java.util.*

object QueuePlayers {
    var queue: Queue<PlayerToD> = LinkedList()

    fun initQueue() {
        clearQueue()
        for (i in 0 until PlayersRepository.players.size) {
            queue.add(PlayersRepository.players[i])
        }
    }
    fun clearQueue() {
        queue.clear()
    }
}