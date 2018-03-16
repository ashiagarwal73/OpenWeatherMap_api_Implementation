package com.agarwal.ashi.touchkinretrofit.pojo;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Ashi on 15-03-2018.
 */


public class WeatherMap {
    @SerializedName("coord")
    Coord coord;
    @SerializedName("weather")
    List<Weather> weather;
    @SerializedName("base")
    String base;
    @SerializedName("main")
    Main main;
    @SerializedName("visibility")
    int visibility;
    @SerializedName("wind")
    Wind wind;
    @SerializedName("clouds")
    Clouds clouds;
    @SerializedName("dt")
    long dt;
    @SerializedName("sys")
    Sys sys;
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("cod")
    int cod;
    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
