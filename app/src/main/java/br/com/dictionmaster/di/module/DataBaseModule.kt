package br.com.dictionmaster.di.module

import android.content.Context
import androidx.room.Room
import br.com.dictionmaster.database.DictionMasterDatabase
import br.com.dictionmaster.database.dao.WordsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DATABASE_NAME = "diction_master.db"
@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DictionMasterDatabase {
        return Room.databaseBuilder(
            context,
            DictionMasterDatabase::class.java,
            DATABASE_NAME)
            .fallbackToDestructiveMigration()//delete old version and recriate with new version
            .build()
    }

    @Provides
    fun provideWordsDao(db: DictionMasterDatabase): WordsDao {
        return db.wordsDao()
    }



}