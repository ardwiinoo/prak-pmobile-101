package com.ardwiinoo.prak_pmobile_101

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ardwiinoo.prak_pmobile_101.databinding.ActivityHasilBinding

class HasilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHasilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra(DATA_NAMA)
        val jenisKelamin = intent.getStringExtra(DATA_JENIS_KELAMIN)
        val resultSetuju = intent.getBooleanExtra(DATA_SETUJU, true)

        binding.apply {
            tampilNama.text = nama
            tampilJenisKelamin.text = jenisKelamin

//            if(jenisKelamin == "Laki-laki") {
//                tampilJenisKelamin.setBackgroundColor(Color.GRAY)
//            }
//            if(jenisKelamin == "Perempuan") {
//                tampilJenisKelamin.setBackgroundColor(Color.MAGENTA)
//            }

            when(jenisKelamin) {
                "Laki-laki" -> {
                    tampilJenisKelamin.setBackgroundColor(Color.GRAY)
                }
                "Perempuan" -> {
                    tampilJenisKelamin.setBackgroundColor(Color.MAGENTA)
                }
            }

//            if(resultSetuju) {
//                tampilSetuju.apply {
//                    text = "Data yang diisikan benar"
//                    setBackgroundColor(Color.GREEN)
//                }
//            } else {
//                tampilSetuju.apply {
//                    text = "Ada yang salah"
//                    setBackgroundColor(Color.RED)
//                }
//            }

            when(resultSetuju) {
                true -> {
                    tampilSetuju.apply {
                        text = "Data yang diisikan benar"
                        setBackgroundColor(Color.GREEN)
                    }
                }
                false -> {
                    tampilSetuju.apply {
                        text = "Ada yang salah"
                        setBackgroundColor(Color.RED)
                    }
                }
            }
        }
    }

    companion object {
        const val DATA_NAMA = "data_nama"
        const val DATA_JENIS_KELAMIN = "data_jenis_kelamin"
        const val DATA_SETUJU = "data_setuju"
    }
}