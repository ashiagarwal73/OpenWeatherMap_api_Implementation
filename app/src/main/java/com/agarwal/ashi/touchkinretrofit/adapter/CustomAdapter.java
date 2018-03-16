package com.agarwal.ashi.touchkinretrofit.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agarwal.ashi.touchkinretrofit.R;
import com.agarwal.ashi.touchkinretrofit.pojo.WeatherMap;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ashi on 16-03-2018.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<WeatherMap> weatherMapArrayList;
    public CustomAdapter(Context context, ArrayList<WeatherMap> weatherMapArrayList)
    {
        this.context=context;
        this.weatherMapArrayList=weatherMapArrayList;
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return weatherMapArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=layoutInflater.inflate(R.layout.customadapter,null);
        TextView city=convertView.findViewById(R.id.city);
        city.setText(weatherMapArrayList.get(position).getName());
        TextView temp=convertView.findViewById(R.id.temp);
        temp.setText(""+(int)weatherMapArrayList.get(position).getMain().getTemp());
        TextView humidity=convertView.findViewById(R.id.humidity);
        humidity.setText(weatherMapArrayList.get(position).getMain().getHumidity()+"%");
        TextView country=convertView.findViewById(R.id.country);
        country.setText(weatherMapArrayList.get(position).getSys().getCountry());
        Date d = new Date();
        CharSequence s  = DateFormat.format("hh:mm", d.getTime());
        TextView time=convertView.findViewById(R.id.time);
        time.setText(s);
        CardView cardView=convertView.findViewById(R.id.card);
        cardView.setCardElevation(10);
        switch (position)
        {
            case 1:
            case 5:
            case 9:
            case 13:cardView.setCardBackgroundColor(convertView.getResources().getColor(R.color.blue));
            break;
            case 2:
            case 6:
            case 10:
            case 14:cardView.setCardBackgroundColor(convertView.getResources().getColor(R.color.gray));
            break;
            case 3:
            case 7:
            case 11:
            case 15:cardView.setCardBackgroundColor(convertView.getResources().getColor(R.color.bb));
            break;
            default:cardView.setCardBackgroundColor(convertView.getResources().getColor(R.color.bb));
        }

        if(weatherMapArrayList.get(position).getWeather().get(0).getMain().equals("Rain"))
        {
            ImageView imageView=convertView.findViewById(R.id.cloud);
            imageView.setImageResource(R.drawable.ic_rainycloud_24dp);

        }
        else if(weatherMapArrayList.get(position).getWeather().get(0).getMain().equals("Snow"))
        {
            ImageView imageView=convertView.findViewById(R.id.cloud);
            imageView.setImageResource(R.drawable.snow);
        }
        else {
            ImageView imageView=convertView.findViewById(R.id.cloud);
            imageView.setImageResource(R.drawable.ic_icons8_onedrive);
        }
        return convertView;
    }
}
