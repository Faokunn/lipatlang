package com.example.gymrat.api

import com.example.gymrat.Models.DefaultResponse
import com.example.gymrat.Models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("api/auth/register")
    fun createUser(
        @Field("username") username:String,
        @Field("email") email:String,
        @Field("age") age:String,
        @Field("gender") gender:String,
        @Field("password") password:String,
        @Field("confirm_password") cpassword:String
    ):Call<DefaultResponse>

    @FormUrlEncoded
    @POST("api/auth/login")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<LoginResponse>
}