package com.ardwiinoo.prak_pmobile_101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.ardwiinoo.prak_pmobile_101.adapter.Adapter
import com.ardwiinoo.prak_pmobile_101.databinding.ActivityMainBinding
import com.ardwiinoo.prak_pmobile_101.model.ModelKaryawan
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AndroidNetworking.initialize(applicationContext)
        val layoutManager = LinearLayoutManager(this)
        binding.rvEmployee.layoutManager = layoutManager

        AndroidNetworking.get("https://praktikum10consumingtest.000webhostapp.com/index.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    // Tangani respons JSONArray di sini
                    val employees = mutableListOf<ModelKaryawan>()
                    for (i in 0 until response.length()) {
                        val employee = response.getJSONObject(i)
                        val employeeId = employee.getString("employee_id")
                        val employeeName = employee.getString("employee_name")
                        val jabatan = employee.getString("jabatan")
                        val tglKerja = employee.getString("tgl_kerja")
                        val gaji = employee.getString("gaji")
                        employees.add(ModelKaryawan(employeeId,employeeName,jabatan,tglKerja,gaji))
                    }
                    val adapter = Adapter(employees)
                    binding.rvEmployee.adapter = adapter
                }
                override fun onError(anError: com.androidnetworking.error.ANError) {
                    // Tangani kesalahan di sini
                }
            })
    }
}