package com.example.hamroparse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface RequestInterface {

    //now we make a GET requset
    @GET("cars_list.json")

    Call<List<CarModelClass>> getCarJson();
}
