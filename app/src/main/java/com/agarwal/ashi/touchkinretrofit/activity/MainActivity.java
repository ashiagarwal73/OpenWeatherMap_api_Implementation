package com.agarwal.ashi.touchkinretrofit.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.agarwal.ashi.touchkinretrofit.R;
import com.agarwal.ashi.touchkinretrofit.adapter.CustomAdapter;
import com.agarwal.ashi.touchkinretrofit.pojo.Main;
import com.agarwal.ashi.touchkinretrofit.pojo.Weather;
import com.agarwal.ashi.touchkinretrofit.pojo.WeatherMap;
import com.agarwal.ashi.touchkinretrofit.rest.ApiClient;
import com.agarwal.ashi.touchkinretrofit.rest.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String appid = "63a156c3a8c9e753308714ef3a6b8ebd";
    ListView listView;
    String newcity;
    ArrayList<String> arrayList;
    ArrayList<WeatherMap> weatherMapArrayList;
    Set<String> set;
    SharedPreferences prefs;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList=new ArrayList<String>();
        weatherMapArrayList=new ArrayList<WeatherMap>();
        arrayList.add("Washington");
        arrayList.add("Sydney");
        arrayList.add("oslo");
        listView=findViewById(R.id.lisview);
        prefs=this.getSharedPreferences("yourPrefsKey",Context.MODE_PRIVATE);
        if (appid.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY ", Toast.LENGTH_LONG).show();
            return;
        }
        set = prefs.getStringSet("yourKey", null);
        if(set!=null)
        {
        arrayList=new ArrayList<String>(set);}
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        for( String string:arrayList)
        {
            Call<WeatherMap> call = apiService.getWeatherReport(string,appid,"metric");
            call.enqueue(new Callback<WeatherMap>() {
                @Override
                public void onResponse(Call<WeatherMap>call, Response<WeatherMap> response) {
                    weatherMapArrayList.add(response.body());
                    CustomAdapter customAdapter=new CustomAdapter(getBaseContext(),weatherMapArrayList);
                    listView.setAdapter(customAdapter);
                }

                @Override
                public void onFailure(Call<WeatherMap>call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
        }

        FloatingActionButton floatingActionButton=findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Search City");
                final EditText input = new EditText(MainActivity.this);
                input.setHint("Enter City Name");
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setIcon(R.drawable.ic_search_black_24dp);
                alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(input.getText().toString().equals(""))
                        {
                            Toast.makeText(MainActivity.this, "Enter city Name", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            newcity=input.getText().toString();
                            ApiInterface apiService =
                                    ApiClient.getClient().create(ApiInterface.class);

                            Call<WeatherMap> call = apiService.getWeatherReport(newcity,appid,"metric");
                            call.enqueue(new Callback<WeatherMap>() {
                                @Override
                                public void onResponse(Call<WeatherMap>call, Response<WeatherMap> response) {
                                    if (response.body() != null) {
                                        if (response.body().getCod() == 200) {
                                            arrayList.add(newcity);
                                            SharedPreferences.Editor edit = prefs.edit();
                                            set = new HashSet<String>();
                                            set.addAll(arrayList);
                                            edit.putStringSet("yourKey", set);
                                            edit.commit();
                                            weatherMapArrayList.add(response.body());

                                            CustomAdapter customAdapter = new CustomAdapter(getBaseContext(), weatherMapArrayList);
                                            listView.setAdapter(customAdapter);
                                        }
                                        if (response.body().getCod() == 404) {

                                            Toast.makeText(MainActivity.this, "No such city exist Please check spelling and try again", Toast.LENGTH_LONG).show();
                                        }
                                        // Log.e(TAG, "Number of weather reports received: " +response.body().getName()+"latitude="+response.body().getCoord().getLat());
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this, "No such city exist Please check spelling and try again", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<WeatherMap>call, Throwable t) {
                                    // Log error here since request failed
                                    Toast.makeText(MainActivity.this, "No such city exist Please check spelling and try again", Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    }
                });
                alertDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

        SharedPreferences.Editor edit=prefs.edit();
        set = new HashSet<String>();
        set.addAll(arrayList);
        edit.putStringSet("yourKey", set);
        edit.commit();

    }
    @Override
    protected void onStop() {
        super.onStop();
        weatherMapArrayList.clear();
        arrayList.clear();
    }
}
