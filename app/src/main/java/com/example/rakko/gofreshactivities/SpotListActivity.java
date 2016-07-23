package com.example.rakko.gofreshactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rakko.adapter.SpotListAdapter;
import com.example.rakko.model.SpotInfo;
import com.example.rakko.modelhanlder.SpotInfoHandler;

import java.util.List;

public class SpotListActivity extends Activity implements AdapterView.OnItemClickListener, View.OnTouchListener {
    ListView spotListView;
    SpotListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spot_list_page);
        adapter = new SpotListAdapter(this.getApplicationContext());
        List<SpotInfo> infoList = SpotInfoHandler.getSpotInfoList(this);

        for(SpotInfo info : infoList) {
            adapter.add(info);
        }

        spotListView = (ListView) findViewById(R.id.spot_list);
        spotListView.setScrollBarStyle(ListView.SCROLLBARS_OUTSIDE_OVERLAY);
        spotListView.setDivider(null);
        spotListView.setAdapter(adapter);
        spotListView.setOnItemClickListener(this);
        View mapButtonView = (View)findViewById(R.id.map_icon);
        mapButtonView.setOnTouchListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        SpotInfo spotInfo = adapter.getItem(position);
        Intent intent = new Intent(this.getApplicationContext(), SpotDetailActivity.class);
        intent.putExtra("spotInfo", spotInfo);
        startActivity(intent);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Intent intent = new Intent(this.getApplicationContext(), SpotMapViewActivity.class);
        startActivity(intent);
        return false;
    }
}
