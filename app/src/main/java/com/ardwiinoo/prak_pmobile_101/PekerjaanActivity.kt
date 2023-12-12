package com.ardwiinoo.prak_pmobile_101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ardwiinoo.prak_pmobile_101.adapter.AdapterPekerjaan
import com.ardwiinoo.prak_pmobile_101.databinding.ActivityPekerjaanBinding
import com.ardwiinoo.prak_pmobile_101.model.ModelPekerjaan
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PekerjaanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPekerjaanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPekerjaanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvPekerjaan.layoutManager = LinearLayoutManager(this)
        binding.rvPekerjaan.setHasFixedSize(true)
        showData()
        tambahData()
    }
    private fun showData() {
        val dataRef = FirebaseDatabase.getInstance("YOUR_FIREBASE_URL").getReference("Pekerjaan")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val pekerjaanList = mutableListOf<ModelPekerjaan>()
                val adapter = AdapterPekerjaan(pekerjaanList)
                for (dataSnapshot in snapshot.children) {
                    val pekerjaanKey = dataSnapshot.getValue(ModelPekerjaan::class.java)
                    pekerjaanKey?.let {
                        pekerjaanList.add(it)
                        Log.d("cek data firebase", it.toString())
                    }
                }
                if (pekerjaanList.isNotEmpty()) {
                    binding.rvPekerjaan.adapter = adapter
                } else {
                    Toast.makeText(this@PekerjaanActivity, "Data not found",
                        Toast.LENGTH_LONG).show()
                }
            }
            override fun onCancelled(snapshot: DatabaseError) {
                // Handle onCancelled
            }
        })
    }
    private fun tambahData(){
        val database: FirebaseDatabase = FirebaseDatabase.getInstance("YOUR_FIREBASE_URL")
        val pekerjaanRef: DatabaseReference = database.getReference("Pekerjaan")
        binding.fabAddPekerjaan.setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.upload_dialog, null)
            MaterialAlertDialogBuilder(this)
                .setTitle("Tambah Pekerjaan")
                .setView(dialogView)
                .setPositiveButton("Tambah") { dialog, _ ->
                    val jobName =
                        dialogView.findViewById<EditText>(R.id.editTextJobName).text.toString()
                    val shift = dialogView.findViewById<EditText>(R.id.editTextShift).text.toString()
                    val pekerjaanData = HashMap<String, Any>()
                    pekerjaanData["nama"] = jobName
                    pekerjaanData["shift"] = shift
                    val newPekerjaanRef = pekerjaanRef.push()
                    newPekerjaanRef.setValue(pekerjaanData)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}
