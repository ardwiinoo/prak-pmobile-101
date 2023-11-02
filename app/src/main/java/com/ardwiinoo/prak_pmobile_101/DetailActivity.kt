package com.ardwiinoo.prak_pmobile_101

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ardwiinoo.prak_pmobile_101.databinding.ActivityDetailBinding
import com.ardwiinoo.prak_pmobile_101.datasource.local.DatabaseBarang
import com.ardwiinoo.prak_pmobile_101.datasource.local.dao.BarangDao
import com.ardwiinoo.prak_pmobile_101.utils.AppExecutor

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var appExecutors: AppExecutor
    private lateinit var barangDao: BarangDao

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        barangDao = DatabaseBarang.getDatabase(this).barangDao()
        appExecutors = AppExecutor()

        val barangId = intent.getIntExtra("barang_id", -1)

        if(barangId != -1) {
            appExecutors.diskIO.execute {
                val barang = barangDao.getBarangById(barangId)

                binding.apply {
                    etNama.setText(barang.nama)
                    etJenis.setText(barang.jenis)
                    etharga.setText(barang.harga.toString())
                    tvStatus.setText("Status: ${
                        if(barang.status == 1) {
                            "tersedia"
                        } else {
                            "tidak tersedia"
                        }
                    }")

                    btnUpdate.setOnClickListener {
                        val updateBarang = barang.copy(
                            nama = etNama.text.toString(),
                            jenis = etJenis.text.toString(),
                            harga = etharga.text.toString().toInt()
                        )

                        appExecutors.diskIO.execute {
                            barangDao.update(updateBarang)

                            finish()
                        }
                    }

                    btnDelete.setOnClickListener {
                        appExecutors.diskIO.execute {
                            barangDao.delete(barang)

                            finish()
                        }
                    }

                    btnHewan.setOnClickListener {
                        val intent = Intent(this@DetailActivity, PostestActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }

    }
}