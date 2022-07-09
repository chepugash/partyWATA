package com.example.wata.ui.fragment.alias.teamlist

object TeamRepository {

    var teams = arrayListOf<Team>(
        Team(
            0,
            "Команда 1",
            0
        ),
        Team(
            1,
            "Команда 2",
            0
        )
    )

    public fun clearTeams() {
        for (i in 0 until teams.count()) {
            teams.clear()
        }
        teams = arrayListOf<Team>(
            Team(
                0,
                "Команда 1",
                0
            ),
            Team(
                1,
                "Команда 2",
                0
            )
        )
    }

}
