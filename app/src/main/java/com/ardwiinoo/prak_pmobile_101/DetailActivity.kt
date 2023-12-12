package com.ardwiinoo.prak_pmobile_101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ardwiinoo.prak_pmobile_101.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val namaKry = intent.getStringExtra(DATA_NAMA)
        val jabatanKry = intent.getStringExtra(DATA_JABATAN)
        val tglMasuk = intent.getStringExtra(DATA_TGL_MASUK)
        val gajiKry = intent.getStringExtra(DATA_GAJI)
        binding.apply {
            tvNamaKaryawan.text = namaKry
            tvJabatanKaryawan.text = jabatanKry
            tvTanggalKerja.text = tglMasuk
            tvGaji.text = gajiKry
        }
    }
    companion object{
        const val DATA_NAMA = "data_nama"
        const val DATA_JABATAN = "data_jabatan"
        const val DATA_TGL_MASUK = "data_tgl_masuk"
        const val DATA_GAJI = "data_gaji"
    }
}