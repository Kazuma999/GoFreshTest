package com.example.rakko.gofreshactivities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by rakko on 2016/07/18.
 */
public class SpotDetailActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private SpotInfo spotInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.spot_detail_page);
        this.spotInfo = (SpotInfo)getIntent().getSerializableExtra("spotInfo");
        setLayout();
        setUpMapIfNeeded();
    }

    private void setLayout() {
        TextView spotNameTextView = (TextView)findViewById(R.id.spot_name);
        spotNameTextView.setText(spotInfo.getName());

        ImageView spotTypeIconImageView = (ImageView)findViewById(R.id.spot_type_icon);
        int typeIconId = SpotInfoHandler.getSpotTypeIconId(spotInfo);
        if (typeIconId != -1)
            spotTypeIconImageView.setImageResource(typeIconId);

        TextView spotDistanceTextView = (TextView)findViewById(R.id.spot_distance);
        spotDistanceTextView.setText(spotInfo.getDistance() + "km");

        TextView spotLocateTextView = (TextView)findViewById(R.id.spot_locate);
        spotLocateTextView.setText(spotInfo.getLocate());

        if (spotInfo.isSmoke()) {
            ImageView smokeImageView = (ImageView)findViewById(R.id.spot_smoke_icon);
            smokeImageView.setImageResource(SpotInfoHandler.getSmokeIconId());
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeByteArray(spotInfo.getSpotImage(), 0, spotInfo.getSpotImage().length, options);

        ImageView spotImageView = (ImageView)findViewById(R.id.spot_image);
        spotImageView.setImageBitmap(bitmap);

        TextView telephoneTextView = (TextView)findViewById(R.id.telephone_number);
        telephoneTextView.setText(spotInfo.getTelNumber());

        TextView chairTextView = (TextView)findViewById(R.id.chair_number);
        chairTextView.setText(spotInfo.getRestChairNum() + " 席／" + spotInfo.getChairNum() + " 席");

        TextView cafeTimeTextView = (TextView)findViewById(R.id.cafe_time);
        cafeTimeTextView.setText("カフェタイム  " + spotInfo.getCafeStartTime() + "-" + spotInfo.getCafeEndTime());

    }

    @Override
    protected void onResume() {
        super.onResume();
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
        .target(new LatLng(spotInfo.getLat(), spotInfo.getLng())).zoom(15.0f)
        .bearing(0).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPos));

        MarkerHandler markerHandler = MarkerHandler.getInstance();
        markerHandler.addMarker(mMap, spotInfo);
    }

    private void setMarker(){

    }
}
