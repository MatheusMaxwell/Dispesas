package com.example.matheus.dispesas

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.reactivex.annotations.NonNull

class Entities {

    @Entity(tableName = "dispesa")
    data class Dispesa(
        @ColumnInfo(name="descricao")
        var descricao: String?,
        @ColumnInfo(name="tipo")
        var tipo: String?,
        @ColumnInfo(name = "valor")
        var valor: Float?,
        @ColumnInfo(name = "pago")
        var pago: Boolean,
        @ColumnInfo(name = "data")
        var data: String

    ){
        @NonNull
        @ColumnInfo(name="id")
        @PrimaryKey(autoGenerate = true)
        var id : Int = 0
        override fun toString(): String {
            return descricao!!
        }
    }

}