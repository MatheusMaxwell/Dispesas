package com.example.matheus.dispesas

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(version = 1, entities = arrayOf(Entities.Dispesa::class))
abstract class AppDataBase : RoomDatabase(){
    abstract fun DispesaDAO(): DispesaDAO

}