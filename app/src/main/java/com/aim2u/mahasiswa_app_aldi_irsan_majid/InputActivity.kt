package com.aim2u.mahasiswa_app_aldi_irsan_majid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aim2u.mahasiswa_app_aldi_irsan_majid.model.DataItem
import com.aim2u.mahasiswa_app_aldi_irsan_majid.model.ResponseData
import com.aim2u.mahasiswa_app_aldi_irsan_majid.network.NetworkModule
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val getData = intent.getParcelableExtra<DataItem>("data")

        if(getData != null){
            edit_nama.setText(getData.mahasiswaNama)
            edit_nohp.setText(getData.mahasiswaNohp)
            edit_alamat.setText(getData.mahasiswaAlamat)

            btn_input.text = "Update"
        }

        when(btn_input.text){
            "Update" -> {
                btn_input.setOnClickListener{
                    updateData(getData.idMahasiswa,edit_nama.text.toString(),edit_nohp.text.toString(),edit_alamat.text.toString())
                }
            } else -> {
                btn_input.setOnClickListener{
                    insertData(edit_nama.text.toString(),edit_nohp.text.toString(),edit_alamat.text.toString())
                }
            }
        }


        btn_batal.setOnClickListener {
            finish()
        }
    }

    private fun insertData(nama : String?, nohp : String?, alamat : String?){
        val input = NetworkModule.service().insertData(nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseData>{
            override fun onResponse(call: Call<ResponseData>, responseData: retrofit2.Response<ResponseData>) {
                if(responseData.isSuccessful) {
                    Toast.makeText(applicationContext, "Data Berhasil Disimpan", Toast.LENGTH_SHORT)
                        .show()
                }
                finish()
            }
            
            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateData(id : String?, nama : String?, nohp : String?, alamat : String?){
        val input = NetworkModule.service().updateData(id ?: "", nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseData>{
            override fun onResponse(call: Call<ResponseData>, responseData: retrofit2.Response<ResponseData>) {
                if(responseData.isSuccessful) {
                    Toast.makeText(applicationContext, "Data Berhasil Diupdate", Toast.LENGTH_SHORT)
                        .show()
                }
                finish()
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}