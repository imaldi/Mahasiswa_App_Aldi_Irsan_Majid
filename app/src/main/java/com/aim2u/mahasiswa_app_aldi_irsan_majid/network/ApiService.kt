package com.aim2u.mahasiswa_app_aldi_irsan_majid.network

import com.aim2u.mahasiswa_app_aldi_irsan_majid.model.ResponseData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //getAll
    @GET("getData.php")
    fun getData(): Call<ResponseData>

    //get data by id
    @GET("getData.php")
    fun getDataById(@Query("id") id_mahasiswa : String): Call<ResponseData>

    //insert
    @FormUrlEncoded
    @POST("insert.php")
    fun insertData( @Field("nama") mahasiswa_nama : String,
                    @Field("nohp") mahasiswa_nohp : String,
                    @Field("alamat") mahasiswa_alamat : String): Call<ResponseData>

    @FormUrlEncoded
    @POST("update.php")
    fun updateData( @Field("id") id_mahasiswa : String,
                    @Field("nama") mahasiswa_nama : String,
                    @Field("nohp") mahasiswa_nohp : String,
                    @Field("alamat") mahasiswa_alamat : String): Call<ResponseData>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData( @Field("id") id_mahasiswa : String): Call<ResponseData>
}