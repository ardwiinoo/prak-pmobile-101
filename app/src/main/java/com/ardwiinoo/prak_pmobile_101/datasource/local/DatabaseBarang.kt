package com.ardwiinoo.prak_pmobile_101.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ardwiinoo.prak_pmobile_101.datasource.local.dao.BarangDao
import com.ardwiinoo.prak_pmobile_101.datasource.local.entity.Barang

@Database(
    entities = [Barang::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseBarang: RoomDatabase() {

    abstract fun barangDao(): BarangDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseBarang? = null

        fun getDatabase(context: Context): DatabaseBarang {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseBarang::class.java,
                    "db_barang_postest"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
