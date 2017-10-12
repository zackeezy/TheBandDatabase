package edu.harding.weatherapipractice;

public class Weather {
    private int mCurrentTemp;
    private int mMinTemp;
    private int mMaxTemp;
    private String mCity;


    public int getCurrentTemp() {
        return mCurrentTemp;
    }

    public void setCurrentTemp(int mCurrentTemp) {
        this.mCurrentTemp = mCurrentTemp;
    }

    public int getMinTemp() {
        return mMinTemp;
    }

    public void setMinTemp(int minTemp) {
        this.mMinTemp = minTemp;
    }

    public int getMaxTemp() {
        return mMaxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.mMaxTemp = maxTemp;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        this.mCity = city;
    }
}
