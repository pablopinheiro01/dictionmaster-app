package br.com.dictionmaster.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.dictionmaster.database.dao.WordsDao

@Database(
    entities = [],
    version = 1
)
abstract class DictionMasterDatabase: RoomDatabase() {

    abstract fun wordsDao(): WordsDao

}