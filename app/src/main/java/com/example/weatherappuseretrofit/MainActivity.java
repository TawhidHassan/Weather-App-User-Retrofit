package com.example.weatherappuseretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv);

      Retrofit retrofit= RetrofitClientInstance.getRetrofitInstance();
      DataService dataService=   retrofit.create(DataService.class);
      Call<DailyForecast> call= dataService.getDailyForcast("Dhaka","5e5798f84890cbe8acf1cfad1a4b07b8",7);

      call.enqueue(new Callback<DailyForecast>() {
        @SuppressLint("WrongConstant")
        @Override
        public void onResponse(Call<DailyForecast> call, Response<DailyForecast> response) {
            if(response.isSuccessful())
            {
                DailyForecast dailyForecast=response.body();
                List<Day> days=dailyForecast.getList();
//                Toast.makeText(getApplicationContext(),dailyForecast.getCity().getName(),Toast.LENGTH_LONG).show();
                WeatherAdapter weatherAdapter=new WeatherAdapter(getApplicationContext(),days);
                recyclerView.setAdapter(weatherAdapter);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);


            }
            else {
                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();

            }
        }

        @Override
        public void onFailure(Call<DailyForecast> call, Throwable t) {
            Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();

        }
    });
    }
}
