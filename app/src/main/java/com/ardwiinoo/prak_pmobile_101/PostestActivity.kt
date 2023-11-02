package com.ardwiinoo.prak_pmobile_101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ardwiinoo.prak_pmobile_101.databinding.ActivityPostestBinding
import com.ardwiinoo.prak_pmobile_101.datasource.model.Hewan

class PostestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val layoutManager = GridLayoutManager(this@PostestActivity, 2)
            rvHewan.layoutManager = layoutManager

            val kumpulanHewan = listOf<Hewan> (
                Hewan(0, "Gagak", "Unggas"),
                Hewan(1, "Kelinci", "Mamalia"),
                Hewan(2, "Ayam", "Unggas"),
                Hewan(3, "Bebek", "Unggas"),
                Hewan(4, "Harimau", "Mamalia"),
            )

            val hewanAdapter = HewanAdapter(kumpulanHewan)
            rvHewan.adapter = hewanAdapter
        }
    }
}