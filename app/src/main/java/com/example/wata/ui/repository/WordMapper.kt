package com.example.wata.ui.repository

object WordMapper: Mapper<WordEntity, Word> {
    override fun toDTO(from: WordEntity): Word {
        return Word(
            id = from.id,
            name = from.name
        )
    }
    override fun toEntity(from: Word): WordEntity {
        return WordEntity(
            id = from.id,
            name = from.name
        )
    }
}