package com.aim2u.mahasiswa_app_aldi_irsan_majid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aim2u.mahasiswa_app_aldi_irsan_majid.R
import com.aim2u.mahasiswa_app_aldi_irsan_majid.model.DataItem
import kotlinx.android.synthetic.main.list_item.view.*

class DataAdapter (val data: List<DataItem>?, val itemClick:OnClickListener): RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.nama.text = item?.mahasiswaNama
        holder.nohp.text = item?.mahasiswaNohp
        holder.alamat.text = item?.mahasiswaAlamat

        holder.view.setOnClickListener {
            itemClick.detail(item)
        }

        holder.btnHapus.setOnClickListener{
            itemClick.hapus(item)
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val nama = view.textNama
        val nohp = view.textNohp
        val alamat = view.textAlamat
        val btnHapus = view.btn_hapus
    }

    interface OnClickListener{
        fun detail(item: DataItem?)
        fun hapus(item: DataItem?)
    }
}