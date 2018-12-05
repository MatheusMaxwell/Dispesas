package com.example.matheus.dispesas

import android.arch.persistence.room.*

@Dao
interface DispesaDAO {

    @Query("SELECT * FROM dispesa")
    fun getAllDispesas(): List<Entities.Dispesa>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDispesa(vararg users: Entities.Dispesa)

    @Update
    fun updateDispesa(user: Entities.Dispesa)

    @Delete
    fun deleteDispesa(user: Entities.Dispesa)

}