package com.ardwiinoo.prak_pmobile_101

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ardwiinoo.prak_pmobile_101.databinding.ItemBarangBinding
import com.ardwiinoo.prak_pmobile_101.datasource.model.Hewan

class HewanAdapter(private val listHewan: List<Hewan>): RecyclerView.Adapter<HewanAdapter.HewanViewHolder>() {

     class HewanViewHolder(val binding: ItemBarangBinding): RecyclerView.ViewHolder(binding.root) {
       fun bind(itemHewan: Hewan) {
           binding.apply {
               tvNama.text = itemHewan.nama
               tvJenis.text = itemHewan.jenis
           }
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HewanViewHolder {
        val binding = ItemBarangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HewanViewHolder(binding)
    }

    override fun getItemCount(): Int = listHewan.size

    override fun onBindViewHolder(holder: HewanViewHolder, position: Int) {
        val itemHewan = listHewan[position]
        holder.bind(itemHewan)
    }
}