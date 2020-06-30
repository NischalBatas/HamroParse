package com.example.hamroparse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CarModelClass>carModelClasses =new ArrayList<>();
    private RecyclerView rv;
    private CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //google.com/project/api/v1/  apicall_name(register)
        //basic api                      call api
        rv =findViewById(R.id.cars_recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));

        getCarRespone();

    }

    private void getCarRespone() {
        //aba retrofit
        Retrofit hamroRetrofit =new Retrofit.Builder().baseUrl("https://navneet7k.github.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //pojo java classes bhanauna khojcha


        //aba interface banauna, to request jasto data
        RequestInterface requestInterface =hamroRetrofit.create(RequestInterface.class);
        Call<List<CarModelClass>> mycall =requestInterface.getCarJson();

        mycall.enqueue(new Callback<List<CarModelClass>>() {
            @Override
            public void onResponse(Call<List<CarModelClass>> call, Response<List<CarModelClass>> response) {
                //json file sanga connection bhayo bhane call hunxa
                carModelClasses = new ArrayList<>(response.body());
                //aba set Adapter garne
                carAdapter =new CarAdapter(carModelClasses,MainActivity.this);
                rv.setAdapter(carAdapter);
                Toast.makeText(MainActivity.this, "Connection Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<CarModelClass>> call, Throwable t) {
                //json file sanga connection
                Toast.makeText(MainActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
