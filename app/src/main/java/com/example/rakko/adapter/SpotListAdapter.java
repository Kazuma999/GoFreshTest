package com.example.rakko.adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rakko.gofreshactivities.R;
import com.example.rakko.model.SpotInfo;
import com.example.rakko.modelhanlder.SpotInfoHandler;

/**
 * Created by rakko on 2016/07/18.
 */
public class SpotListAdapter extends ArrayAdapter<SpotInfo> {
    LayoutInflater mInflater;

    public SpotListAdapter(Context context) {
        super(context, 0);
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_list_item_spot, parent, false);
        }
        SpotInfo info = getItem(position);

        TextView spotNameTextView = (TextView)convertView.findViewById(R.id.spot_name);
        spotNameTextView.setText(info.getName());

        ImageView spotTypeIconImageView = (ImageView)convertView.findViewById(R.id.spot_type_icon);
        int typeIconId = SpotInfoHandler.getSpotTypeIconId(info);
        if (typeIconId != -1)
            spotTypeIconImageView.setImageResource(typeIconId);

        TextView spotDistanceTextView = (TextView) convertView.findViewById(R.id.spot_distance);
        spotDistanceTextView.setText(info.getDistance() + "km");

        TextView spotLocateTextView = (TextView) convertView.findViewById(R.id.spot_locate);
        spotLocateTextView.setText(info.getLocate());

        if (info.isSmoke()) {
            ImageView smokeImageView = (ImageView) convertView.findViewById(R.id.spot_smoke_icon);
            smokeImageView.setImageResource(SpotInfoHandler.getSmokeIconId());
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeByteArray(info.getSpotImage(), 0, info.getSpotImage().length, options);

        ImageView spotImageView = (ImageView) convertView.findViewById(R.id.spot_image);
        spotImageView.setImageBitmap(bitmap);

        return convertView;
    }
}
