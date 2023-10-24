package com.ardwiinoo.prak_pmobile_101

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ardwiinoo.prak_pmobile_101.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var jenisKelamin: String = ""
    private var tampilSetuju: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSubmit.setOnClickListener {
                val nama = isinama.text.toString()

                if(rdb1.isChecked) {
                    jenisKelamin = "Laki-laki"
                }
                if(rdb2.isChecked) {
                    jenisKelamin = "Perempuan"
                }

                tampilSetuju = checkAgree.isChecked

                // pindah ke hasil activity (explicits)
//               startActivity(Intent(this@MainActivity, HasilActivity::class.java).apply {
//                   putExtra(HasilActivity.DATA_NAMA, nama)
//                   putExtra(HasilActivity.DATA_JENIS_KELAMIN, jenisKelamin)
//                   putExtra(HasilActivity.DATA_SETUJU, tampilSetuju)
//               })

                // implicit
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://tif.uad.ac.id")
                })
            }
        }
    }
}