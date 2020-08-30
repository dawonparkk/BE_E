package com.example.be_e;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skt.Tmap.TMapCircle;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapTapi;
import com.skt.Tmap.TMapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    final ArrayList<TripPathInfo> tripPaths = new ArrayList<>();
    final ArrayList<EVListInfo> evListInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 사용자 선택한 도시와 테마 가져오기
        String choice_se = getIntent().getStringExtra("choice_se");
        String choice_theme = getIntent().getStringExtra("choice_theme");
        final TextView textView = (TextView)findViewById(R.id.textinfo);
        textView.setText(choice_se + "의 " + choice_theme);

        // TMapView 띄우기
        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.linearLayoutTmap);
        TMapView tMapView = new TMapView(this);
        tMapView.setZoomLevel(11);

        // 전기차 충전소 띄우기
        // 전기차 충전소 Json 파일 읽기
        String jsonEV = getJsonString("evlist.json");

        // 전기차 충전소 Json 파싱
        try {
            JSONObject jsonEVObject = new JSONObject(jsonEV);
            JSONArray EVArray = jsonEVObject.getJSONArray("evs");

            for(int i = 0; i < EVArray.length(); i++) {
                JSONObject EVObject = EVArray.getJSONObject(i);

                EVListInfo evListInfo = new EVListInfo();

                evListInfo.setName(EVObject.getString("충전소"));
                evListInfo.setLoce(EVObject.getString("이름"));
                evListInfo.setLati(EVObject.getDouble("Y"));
                evListInfo.setLongti(EVObject.getDouble("X"));

                evListInfos.add(i, evListInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 주변 충전소들 마커 띄우기
        for (int i = 0; i < evListInfos.size(); i++) {
            if ( choice_se.equals(evListInfos.get(i).getLoce() )) {
                TMapPoint evPoint = new TMapPoint(evListInfos.get(i).getLati(), evListInfos.get(i).getLongti());

                TMapMarkerItem markerItem1 = new TMapMarkerItem();
                markerItem1.setTMapPoint(evPoint);
                markerItem1.setName(evListInfos.get(i).getName());

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mar);
                markerItem1.setIcon(bitmap);

                markerItem1.setCanShowCallout(true);
                markerItem1.setCalloutTitle(evListInfos.get(i).getName());


                tMapView.addMarkerItem("marker" + i, markerItem1);

//                TMapCircle tMapCircle = new TMapCircle();
//                tMapCircle.setCenterPoint(evPoint);
//                tMapCircle.setRadius(200);
//                tMapCircle.setCircleWidth(5);
//                tMapCircle.setLineColor(Color.BLUE);
//                tMapCircle.setAreaColor(Color.GRAY);
//                tMapCircle.setAreaAlpha(100);
//                tMapView.addTMapCircle("circle" + i, tMapCircle);
            }
        }

        // 관광 경로 띄우기
        // 관광 경로 Json 파일 읽기
        String json = getJsonString("tripath.json");

        // 관광 경로 Json 파싱
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

                ArrayList<String> trips = new ArrayList<>();
                ArrayList<Double> locations = new ArrayList<>();
                for (int j = 1; j < tripObject.length() - 5; j += 3) {
                    trips.add(tripObject.getString(String.valueOf(j)));
                    locations.add(tripObject.getDouble(String.valueOf(j + 1)));
                    locations.add(tripObject.getDouble(String.valueOf(j + 2)));
                }

                tripPathInfo.setTrips(trips);
                tripPathInfo.setLocations(locations);

                tripPaths.add(i, tripPathInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 관광 경로 마커 띄우기
        for (int i = 0; i < tripPaths.size(); i++) {
            if ((choice_se.equals(tripPaths.get(i).getName()) && (choice_theme.equals(tripPaths.get(i).getTheme())))) {
                tMapView.setCenterPoint(tripPaths.get(i).getLongtitude(), tripPaths.get(i).getLatitude());

                ArrayList<TMapPoint> alTMapPoint = new ArrayList<TMapPoint>();
                for (int j = 0; j < tripPaths.get(i).getTrips().size(); j++) {
                    TMapPoint tmapPoint1 = new TMapPoint(tripPaths.get(i).getLocations().get(j * 2), tripPaths.get(i).getLocations().get(j * 2 + 1));
                    alTMapPoint.add(tmapPoint1);
                    TMapMarkerItem markerItem1 = new TMapMarkerItem();
                    markerItem1.setTMapPoint(tmapPoint1);
                    markerItem1.setName(tripPaths.get(i).getTrips().get(j));

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lomaker);
                    markerItem1.setIcon(bitmap);

                    markerItem1.setCanShowCallout(true);
                    markerItem1.setCalloutTitle(j + ":" + tripPaths.get(i).getTrips().get(j));
                    markerItem1.setAutoCalloutVisible(true);
                    markerItem1.setEnableClustering(false);

                    tMapView.addMarkerItem("markeritem" + j, markerItem1);
                }

                TMapPolyLine tMapPolyLine = new TMapPolyLine();
                tMapPolyLine.setLineColor(Color.BLUE);
                tMapPolyLine.setLineWidth(2);
                for (int k = 0; k < alTMapPoint.size(); k++) {
                    tMapPolyLine.addLinePoint(alTMapPoint.get(k));
                }
                tMapView.addTMapPolyLine("Line" + i, tMapPolyLine);
            }
        }

        Button buttonmap = (Button)findViewById(R.id.buttonMap);
        buttonmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TMapTapi tMapTapi = new TMapTapi(getApplication());

                HashMap pathInfo = new HashMap();
                pathInfo.put("rGoName", "정동진레일");
                pathInfo.put("rGoX", "129.033067");
                pathInfo.put("rGoY", "37.691351");

                pathInfo.put("rStName", "경포대");
                pathInfo.put("rStX", "128.896585");
                pathInfo.put("rStY", "37.79519");

                pathInfo.put("rV1Name", "초당순두부");
                pathInfo.put("rV1X", "128.913453");
                pathInfo.put("rV1Y", "37.78837");
                tMapTapi.invokeRoute(pathInfo);
            }
        });






//                try {
//                    TMapPolyLine tMapPolyLine = new TMapData().findPathData(tMapPoint1, tMapPoint2);
//                    tMapPolyLine.setLineColor(Color.BLUE);
//                    tMapPolyLine.setLineWidth(2);
//                    tMapView.addTMapPolyLine("Line1", tMapPolyLine);
//                }catch(Exception e) {
//                    e.printStackTrace();
//                }

//                Toast.makeText(getApplicationContext(), tripPaths.get(i).getTrips().get(i), Toast.LENGTH_LONG).show();
//            }
//        }


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

    private String getJsonString(String jsonName)
    {
        String json = "";

        try {
            InputStream is = getAssets().open(jsonName);
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