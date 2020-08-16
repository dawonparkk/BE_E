package com.example.be_e;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final ArrayList<TripPathInfo> tripPaths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.linearLayoutTmap);
        TMapView tMapView = new TMapView(this);
        tMapView.setZoomLevel(11);

        String json = getJsonString();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray tripArray = jsonObject.getJSONArray("trippath");

            for(int i = 0; i < tripArray.length(); i++) {
                JSONObject tripObject = tripArray.getJSONObject(i);

                TripPathInfo tripPathInfo = new TripPathInfo();

                tripPathInfo.setName(tripObject.getString("CE"));
                tripPathInfo.setTheme(tripObject.getString("CATEGORY"));
                tripPathInfo.setLatitude(tripObject.getDouble("LATITUDE"));
                tripPathInfo.setLongtitude(tripObject.getDouble("LONGTITUDE"));
                tripPathInfo.setTrip1(tripObject.getString("1"));
                tripPathInfo.setLa1(tripObject.getDouble("2"));
                tripPathInfo.setLo1(tripObject.getDouble("3"));
                tripPathInfo.setTrip2(tripObject.getString("4"));
                tripPathInfo.setLa2(tripObject.getDouble("5"));
                tripPathInfo.setLo2(tripObject.getDouble("6"));
                tripPathInfo.setTrip3(tripObject.getString("7"));
                tripPathInfo.setLa3(tripObject.getDouble("8"));
                tripPathInfo.setLo3(tripObject.getDouble("9"));


//                ArrayList<String> names = new ArrayList<>();
//                ArrayList<Double> locations = new ArrayList<>();
//                for (int j = 1; j < tripObject.length() - 5; j += 3) {
//                    names.add(tripObject.getString(String.valueOf(i)));
//                    locations.add(tripObject.getDouble(String.valueOf(i + 1)));
//                    locations.add(tripObject.getDouble(String.valueOf(i + 2)));
//                }
//
//                tripPathInfo.setTrips(names);
//                tripPathInfo.setLocations(locations);

                tripPaths.add(i, tripPathInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String choice_se = getIntent().getStringExtra("choice_se");
        String choice_theme = getIntent().getStringExtra("choice_theme");

        for (int i = 0; i < tripPaths.size(); i++) {
            if ( (choice_se.equals(tripPaths.get(i).getName()) && (choice_theme.equals(tripPaths.get(i).getTheme())))) {
                TMapPoint tMapPoint1 = new TMapPoint(tripPaths.get(i).getLa1(), tripPaths.get(i).getLo1());
                TMapPoint tMapPoint2 = new TMapPoint(tripPaths.get(i).getLa2(), tripPaths.get(i).getLo2());
                TMapPoint tMapPoint3 = new TMapPoint(tripPaths.get(i).getLa3(), tripPaths.get(i).getLo3());

                TMapMarkerItem markerItem1 = new TMapMarkerItem();
                TMapMarkerItem markerItem2 = new TMapMarkerItem();
                TMapMarkerItem markerItem3 = new TMapMarkerItem();

                markerItem1.setTMapPoint(tMapPoint1);
                markerItem2.setTMapPoint(tMapPoint2);
                markerItem3.setTMapPoint(tMapPoint3);

                markerItem1.setName(tripPaths.get(i).getTrip1());
                markerItem2.setName(tripPaths.get(i).getTrip2());
                markerItem3.setName(tripPaths.get(i).getTrip3());

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lomaker);

                markerItem1.setIcon(bitmap);
                markerItem2.setIcon(bitmap);
                markerItem3.setIcon(bitmap);

                tMapView.addMarkerItem("markerItem1", markerItem1);
                tMapView.addMarkerItem("markerItem2", markerItem2);
                tMapView.addMarkerItem("markerItem3", markerItem3);

                ArrayList<TMapPoint> alTMapPoint = new ArrayList<TMapPoint>();
                alTMapPoint.add(tMapPoint1);
                alTMapPoint.add(tMapPoint2);
                alTMapPoint.add(tMapPoint3);

                TMapPolyLine tMapPolyLine = new TMapPolyLine();
                tMapPolyLine.setLineColor(Color.BLUE);
                tMapPolyLine.setLineWidth(2);
                for( int j=0; j<alTMapPoint.size(); j++ ) {
                    tMapPolyLine.addLinePoint( alTMapPoint.get(j) );
                }
                tMapView.addTMapPolyLine("Line1", tMapPolyLine);



                tMapView.setCenterPoint(tripPaths.get(i).getLongtitude(), tripPaths.get(i).getLatitude());

//                try {
//                    TMapPolyLine tMapPolyLine = new TMapData().findPathData(tMapPoint1, tMapPoint2);
//                    tMapPolyLine.setLineColor(Color.BLUE);
//                    tMapPolyLine.setLineWidth(2);
//                    tMapView.addTMapPolyLine("Line1", tMapPolyLine);
//                }catch(Exception e) {
//                    e.printStackTrace();
//                }

//                Toast.makeText(getApplicationContext(), tripPaths.get(i).getTrips().get(i), Toast.LENGTH_LONG).show();
            }
        }


//        TMapPoint tMapPoint1 = new TMapPoint(37.786875, 128.884989); // SKT타워
//
//        TMapMarkerItem markerItem1 = new TMapMarkerItem();
//
//        // 마커의 좌표,타이틀 지정
//        markerItem1.setTMapPoint(tMapPoint1);
//        markerItem1.setName("선교장");
//        markerItem1.setAutoCalloutVisible(true);
//        markerItem1.setCalloutTitle("선교장");
//
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lomaker);
//        markerItem1.setIcon(bitmap); // 마커 아이콘 지정
//        markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
//
//        tMapView.addMarkerItem("markerItem1", markerItem1); // 지도에 마커 추가
//
//        tMapView.setCenterPoint( 128.884989, 37.786875 );

        tMapView.setSKTMapApiKey( "l7xxaf5e2f6967094cf39c3ff1fdda6f3af6");
        linearLayoutTmap.addView( tMapView );
    }

    private String getJsonString()
    {
        String json = "";

        try {
            InputStream is = getAssets().open("tripath.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return json;
    }
}