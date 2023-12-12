package com.ardwiinoo.prak_pmobile_101.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ardwiinoo.prak_pmobile_101.databinding.ItemPekerjaanBinding
import com.ardwiinoo.prak_pmobile_101.model.ModelPekerjaan

class AdapterPekerjaan(private val data: List<ModelPekerjaan>) :
    RecyclerView.Adapter<AdapterPekerjaan.PekerjaanViewHolder>() {
    class PekerjaanViewHolder(private val binding: ItemPekerjaanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: ModelPekerjaan) {
            binding.tvNama.text = employee.nama
            binding.tvShift.text = employee.shift
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PekerjaanViewHolder {
        val binding = ItemPekerjaanBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PekerjaanViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PekerjaanViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }
    override fun getItemCount(): Int = data.size
}
