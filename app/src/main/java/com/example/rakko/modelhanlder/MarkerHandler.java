package com.example.rakko.modelhanlder;

import android.app.Activity;
import android.view.View;

import com.example.rakko.model.SpotInfo;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rakko on 2016/07/18.
 */
public class MarkerHandler {
    private static MarkerHandler instance = null;
    private static Map<Long, Marker> currentMarkerMap;
    private static Map<Integer, SpotInfo> currentSpotInfoMap;
    private MarkerHandler() {

    }

    private void clearMap() {
        if (currentMarkerMap != null) {
            if (currentMarkerMap.size() > 0) {
                Collection<Marker> mapValues = currentMarkerMap.values();
                for (Marker aMarker : mapValues)
                    aMarker.remove();
                currentMarkerMap.clear();
            }
        } else
            currentMarkerMap = new HashMap<Long, Marker>();
        currentSpotInfoMap = new HashMap<Integer, SpotInfo>();
    }

    public static MarkerHandler getInstance() {
        if (instance == null) {
            instance = new MarkerHandler();

        }
        instance.clearMap();
        return instance;
    }

    public void put(SpotInfo spotInfo, Marker marker) {
        this.currentMarkerMap.put(spotInfo.getSpotId(), marker);
        this.currentSpotInfoMap.put(marker.hashCode(), spotInfo);
    }

    public Marker getMarker(SpotInfo spotInfo) {
        return this.currentMarkerMap.get(spotInfo.getSpotId());
    }

    public SpotInfo getSpotInfo(Marker marker) {
        return this.currentSpotInfoMap.get(marker.hashCode());
    }

    public Marker addMarker(GoogleMap map, SpotInfo spotInfo) {
        return this.addMarker(map, spotInfo, false);
    }

    public Marker addMarker(GoogleMap map, SpotInfo spotInfo, boolean withInfo) {
        LatLng latLng = new LatLng(spotInfo.getLat(), spotInfo.getLng());
        if (withInfo) {

        }
        MarkerOptions markerOptions = new MarkerOptions();

        Marker marker = map.addMarker(new MarkerOptions().position(latLng));

        this.put(spotInfo, marker);



        return marker;

    }

    public void addAllSpotInfoMarker(GoogleMap map, Activity act) {
        List<SpotInfo> spotInfoList = SpotInfoHandler.getSpotInfoList(act);
        for (SpotInfo aSpotInfo : spotInfoList) {
            this.addMarker(map, aSpotInfo, true);
        }
    }
}
