package com.example.weatherappuseretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {
    Context context;
    List<Day> days;
    WeatherAdapter(Context context, List<Day> days){
        this.context=context;
        this.days=days;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
       View view= inflater.inflate(R.layout.weather_single_row,parent, false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.date.setText(days.get(position).getDtTxt());
        holder.skyStats.setText(days.get(position).getWeather().get(0).getDescription());
        holder.wind.setText(days.get(position).getWind().getSpeed()+"");
        holder.presuuer.setText(days.get(position).getMain().getPressure()+"");
        DecimalFormat decimalFormat=new DecimalFormat("#.#");

        String     maxtemp = decimalFormat.format(273.15 - (days.get(position).getMain().getTempMax()));
        holder.maxtemp.setText(maxtemp);

        String     mintemp = decimalFormat.format(273.15 - (days.get(position).getMain().getTempMin()));
        holder.mintemp.setText(mintemp);


        String     selcias = decimalFormat.format(273.15 - (days.get(position).getMain().getTemp()));
        holder.temp.setText(selcias);
        holder.humadati.setText(days.get(position).getMain().getHumidity().toString());
    }

    @Override
    public int getItemCount() {
        return days.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date,skyStats,wind,presuuer,maxtemp,mintemp,temp,humadati;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.dateline);
            skyStats=itemView.findViewById(R.id.skystats);
            wind=itemView.findViewById(R.id.windstatas);
            presuuer=itemView.findViewById(R.id.pressurstatas);
            maxtemp=itemView.findViewById(R.id.maxmumcelceious);
            mintemp=itemView.findViewById(R.id.minmumcelceious);
            temp=itemView.findViewById(R.id.celceious);
            humadati=itemView.findViewById(R.id.humiditystatas);


        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
