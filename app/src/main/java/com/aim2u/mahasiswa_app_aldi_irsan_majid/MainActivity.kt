package com.aim2u.mahasiswa_app_aldi_irsan_majid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.aim2u.mahasiswa_app_aldi_irsan_majid.adapter.DataAdapter
import com.aim2u.mahasiswa_app_aldi_irsan_majid.model.DataItem
import com.aim2u.mahasiswa_app_aldi_irsan_majid.model.ResponseData
import com.aim2u.mahasiswa_app_aldi_irsan_majid.network.NetworkModule
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val intent = Intent(this,InputActivity::class.java)
            startActivity(intent)
        }

        showData()
    }

    private fun showData(){
        val list_mahasiswa = NetworkModule.service().getData()
        list_mahasiswa.enqueue(object : Callback<ResponseData>{
            override fun onResponse(call: Call<ResponseData>, responseData: retrofit2.Response<ResponseData>) {
                if (responseData.isSuccessful){

                    val item = responseData.body()?.data
                    val adapter = DataAdapter(item, object : DataAdapter.OnClickListener{
                        override fun detail(item: DataItem?) {
                            val intent = Intent(applicationContext,InputActivity::class.java)
                            intent.putExtra("data",item)
                            startActivity(intent)
                        }

                        override fun hapus(item: DataItem?) {
                            AlertDialog.Builder(this@MainActivity).apply {
                                setTitle("Hapus Data")
                                setMessage("Yakin mau hapus data?")
                                setPositiveButton("Hapus"){ dialog, which ->
                                    hapusData(item?.idMahasiswa)
                                    dialog.dismiss()
                                }
                                setNegativeButton("Batal"){dialog, which ->
                                    dialog.dismiss()
                                }
                            }.show()

                        }

                    })

                    rv_main.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun hapusData(idMahasiswa: String?) {
        val hapus = NetworkModule.service().deleteData(idMahasiswa?:"")
        hapus.enqueue(object : Callback<ResponseData>{
            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Toast.makeText(applicationContext, t?.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                showData()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        showData()
    }
}