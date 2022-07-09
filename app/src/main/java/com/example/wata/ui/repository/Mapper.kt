package com.example.wata.ui.repository

interface Mapper<T, R> {

    fun toDTO(from: T): R

    fun toEntity(from: R): T

}