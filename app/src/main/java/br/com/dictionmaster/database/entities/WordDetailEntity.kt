package br.com.dictionmaster.database.entities

import androidx.room.Entity
import java.util.UUID

@Entity(tableName = "Words")
class WordDetailEntity(
    val id: String = UUID.randomUUID().toString(),
    val word: String,
    val json: String
)