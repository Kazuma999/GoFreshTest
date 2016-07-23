package com.example.rakko.modelhanlder;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.rakko.gofreshactivities.R;
import com.example.rakko.model.DrinkMenuInfo;
import com.example.rakko.model.SpotInfo;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rakko on 2016/07/18.
 */
public class SpotInfoHandler {
    static List<SpotInfo> spotInfoList = null;
    public static int getSpotTypeIconId(SpotInfo info) {
        if (info == null) return -1;

        String type = info.getType();
        if (type == null) return -1;

        if (type.equals("cafe")) {
            return R.drawable.icon_cafe;
        }
        else if (type.equals("restaurant")) {
            return R.drawable.icon_restaurant;
        }
        else return -1;
    }

    public static int getSmokeIconId() {
        return R.drawable.icon_smoke;
    }

    public static List<SpotInfo> getSpotInfoList(Activity act) {
        if (spotInfoList != null) return spotInfoList;
        List<SpotInfo> spotInfoList = new ArrayList<SpotInfo>();
        String[] types = {"cafe", "restaurant"};
        String[] namesR = {"レストラン ０１01", "Restaurant αγ"};
        String[] namesC = {"Cafe 朝night", "カフェ・ω・"};
        String[] locates = {"東京", "長野", "鹿児島", "北海道"};
        Random rnd = new Random();
        Random rnd2 = new Random();
        Random rnd3 = new Random();
        Random rnd5 = new Random();
        double lat = 35.640042;
        double lng = 139.751901;
        long spotId = 1;
        for (int i = 0; i < 30 ; i++) {
            int ran = rnd.nextInt(2);
            int ran2 = rnd.nextInt(2);
            int ran3 = rnd.nextInt(2);

            SpotInfo info = new SpotInfo();
            boolean isCafe = true;
            if(ran2 == 1) isCafe = false;
            info.setType(types[ran2]);
            Bitmap bm;
            if (isCafe) {
                info.setName(namesC[ran]);
                if (ran == 0)
                    bm = BitmapFactory.decodeResource(act.getResources(), R.drawable.cafe01);
                else
                    bm = BitmapFactory.decodeResource(act.getResources(), R.drawable.cafe02);
            } else {
                info.setName(namesR[ran]);
                if (ran == 0)
                    bm = BitmapFactory.decodeResource(act.getResources(), R.drawable.restaurant01);
                else
                    bm = BitmapFactory.decodeResource(act.getResources(), R.drawable.restaurant02);

            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            info.setSpotImage(bos.toByteArray());

            Random rnd4 = new Random();
            info.setDistance(((double)rnd4.nextInt(1000)) / 100.00);
            info.setSmoke((ran3 == 0) ? true : false);

            info.setLocate(locates[rnd4.nextInt(1000) % 4]);
            List<DrinkMenuInfo> drinkMenus = new ArrayList<DrinkMenuInfo>();
            drinkMenus.add(new DrinkMenuInfo("アイスコーヒー", 400));
            drinkMenus.add(new DrinkMenuInfo("ホットコーヒー", 400));
            drinkMenus.add(new DrinkMenuInfo("カフェラテ", 450));
            info.setDrinkMenus(drinkMenus);
            info.setCafeStartTime("13:00");
            info.setCafeEndTime("17:00");
            info.setTelNumber("03 1234 5678");
            info.setChairNum(120);
            info.setRestChairNum(33);

            info.setLat(lat + (((double)rnd5.nextInt(100)) / 10000.0) - 0.005);
            info.setLng(lng + (((double)rnd5.nextInt(100)) / 10000.0) - 0.005);
            info.setSpotId(spotId++);

            spotInfoList.add(info);

        }

        return spotInfoList;
    }


}
