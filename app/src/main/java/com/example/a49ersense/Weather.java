package com.example.a49ersense;

public class Weather
{
    private String date;
    private String minTemp;
    private String maxTemp;
    private String backgroundDay;
    private String backgroundNight;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getBackgroundDay() {
        return backgroundDay;
    }

    public void setBackgroundDay(String background) {
        this.backgroundDay = background;
    }

    public String getBackgroundNight() {
        return backgroundNight;
    }

    public void setBackgroundNight(String backgroundNight) {
        this.backgroundNight = backgroundNight;
    }
}
