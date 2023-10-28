package com.ardwiinoo.prak_pmobile_101

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ardwiinoo.prak_pmobile_101.databinding.ActivityMainBinding
import com.ardwiinoo.prak_pmobile_101.datasource.local.DatabaseBarang
import com.ardwiinoo.prak_pmobile_101.datasource.local.dao.BarangDao
import com.ardwiinoo.prak_pmobile_101.datasource.local.entity.Barang
import com.ardwiinoo.prak_pmobile_101.utils.AppExecutor

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: DatabaseBarang
    private lateinit var barangDao: BarangDao
    private lateinit var appExecutor: AppExecutor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseBarang.getDatabase(this)
        barangDao = db.barangDao()
        appExecutor = AppExecutor()

        binding.apply {
            fabAdd.setOnClickListener {
                appExecutor.diskIO.execute {

                    insertDummyData()
                }

                val listBarang: LiveData<List<Barang>> = barangDao.getAllBarang()
                listBarang.observe(this@MainActivity, Observer { list ->

                    val namaBarang = list.map {
                        it.nama
                    }

                    lvRoomDb.adapter = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_list_item_1,
                        namaBarang
                    )

                    lvRoomDb.setOnItemClickListener { _, _, position, _ ->
                        val selectedBarang = list[position]

                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra("barang_id", selectedBarang.id)

                        startActivity(intent)
                    }
                })
            }
        }
    }

    private fun insertDummyData() {

        val listNamaBarang = listOf<String>(
            "Meja", "Kursi", "Komputer", "Laptop"
        )

        val listJenisBarang = listOf<String>(
            "Furnitur", "Furnitur", "Elektronik", "Elektronik"
        )

        val listHargaBarang = listOf<Int>(
            850000, 200000, 5000000, 6000000
        )

        val listStatusBarang = listOf<Int>(
            0, 1, 0, 1
        )

        for(i in 0 .. 3) {
            val newBarang = Barang(
                nama = listNamaBarang[i],
                jenis = listJenisBarang[i],
                harga = listHargaBarang[i],
                status = listStatusBarang[i]
            )

            barangDao.insert(newBarang)
        }
    }
}