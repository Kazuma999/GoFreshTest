package com.example.rakko.model;

import android.location.Address;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * Created by rakko on 2016/07/18.
 */
public class SpotInfo implements Serializable {
    private long spotId;
    private String imageId;
    private String type;
    private String name;
    private double distance;
    private String locate;

    public long getSpotId() {
        return spotId;
    }

    private boolean isSmoke;
    private String telNumber;

    public String getLocate() {
        return locate;
    }

    public double getLat() {
        return lat;
    }

    public void setSpotId(long spotId) {
        this.spotId = spotId;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLng() {

        return lng;
    }

    private String cafeStartTime;
    private String cafeEndTime;
    private List<DrinkMenuInfo> drinkMenus;
    private double lat;
    private double lng;
    private int chairNum;
    private int restChairNum;


    public int getChairNum() {
        return chairNum;
    }

    public int getRestChairNum() {
        return restChairNum;
    }

    public void setChairNum(int chairNum) {
        this.chairNum = chairNum;
    }

    public void setRestChairNum(int restChairNum) {
        this.restChairNum = restChairNum;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getCafeStartTime() {
        return cafeStartTime;
    }

    public String getCafeEndTime() {
        return cafeEndTime;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setCafeStartTime(String cafeStartTime) {
        this.cafeStartTime = cafeStartTime;
    }

    public void setCafeEndTime(String cafeEndTime) {
        this.cafeEndTime = cafeEndTime;
    }

    public void setDrinkMenus(List<DrinkMenuInfo> drinkMenus) {
        this.drinkMenus = drinkMenus;
    }

    public List<DrinkMenuInfo> getDrinkMenus() {
        return drinkMenus;
    }

    public void setSpotImage(byte[] spotImage) {
        this.spotImage = spotImage;
    }

    private byte[] spotImage;

    public byte[] getSpotImage() {
        return spotImage;
    }

    public String getImageId() {
        return imageId;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public boolean isSmoke() {
        return isSmoke;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setSmoke(boolean smoke) {
        isSmoke = smoke;
    }
}
