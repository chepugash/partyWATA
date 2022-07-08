package com.example.wata.ui.models

import java.lang.reflect.Constructor

data class Player(
    val id: Int,
    var name: String
) {
    constructor() : this(-1, "")
}
