package com.ardwiinoo.prak_pmobile_101

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ardwiinoo.prak_pmobile_101.databinding.ItemBarangBinding
import com.ardwiinoo.prak_pmobile_101.datasource.local.entity.Barang

class BarangAdapter(private val barangList: List<Barang>): RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

    class BarangViewHolder(val binding: ItemBarangBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(itemBarang: Barang) {
            binding.apply {
                tvNama.text = itemBarang.nama.toString()
                tvJenis.text = itemBarang.jenis.toString()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("barang_id", itemBarang.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val binding = ItemBarangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BarangViewHolder(binding)
    }

    override fun getItemCount(): Int = barangList.size

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val itemBarang = barangList[position]
        holder.bind(itemBarang)
    }
}