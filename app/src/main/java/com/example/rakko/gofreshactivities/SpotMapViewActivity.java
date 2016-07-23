package com.example.rakko.gofreshactivities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rakko.model.SpotInfo;
import com.example.rakko.modelhanlder.MarkerHandler;
import com.example.rakko.modelhanlder.SpotInfoHandler;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class SpotMapViewActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MarkerHandler markerHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.spot_map_view_page);
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.spot_detail_map);
            mapFragment.getMapAsync(this);
            // Check if we were successful in obtaining the map.
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        CameraPosition cameraPos = new CameraPosition.Builder()
                .target(getCurrentLatLng()).zoom(15.0f)
                .bearing(0).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPos));

        markerHandler = MarkerHandler.getInstance();
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoContents(Marker marker) {
                // TODO Auto-generated method stub


                // タイトル設定
                return null;
            }

            @Override
            public View getInfoWindow(Marker marker) {
                // TODO Auto-generated method stub
                View view = getLayoutInflater().inflate(R.layout.spot_info_marker_window, null);
                ImageView spotTypeIconImageView = (ImageView)view.findViewById(R.id.spot_type_icon);
                SpotInfo info = markerHandler.getSpotInfo(marker);
                int typeIconId = SpotInfoHandler.getSpotTypeIconId(info);
                if (typeIconId != -1)
                    spotTypeIconImageView.setImageResource(typeIconId);

                TextView spotNameTextView = (TextView)view.findViewById(R.id.spot_name);
                spotNameTextView.setText(info.getName());
                return view;
            }
        });
        markerHandler.addAllSpotInfoMarker(googleMap, this);
    }

    private LatLng getCurrentLatLng() {
        return new LatLng(35.640042, 139.751901);
    }

}
