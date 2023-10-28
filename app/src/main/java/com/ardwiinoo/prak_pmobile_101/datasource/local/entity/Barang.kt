package com.ardwiinoo.prak_pmobile_101.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
data class Barang (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "nama")
    var nama: String? = null,

    @ColumnInfo(name = "jenis")
    var jenis: String? = null,

    @ColumnInfo(name = "harga")
    var harga: Int? = null,

    @ColumnInfo(name = "status")
    var status: Int = 0
)