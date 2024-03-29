package br.com.dictionmaster.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "Words")
class WordDetailEntity(
    @PrimaryKey
    val word: String,
    val json: String
)

