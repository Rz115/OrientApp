package com.devector.raul.seeorienta;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main_Superior_Instalaciones extends AppCompatActivity {
    ListView lista;


    JsonObjectRequest array;
    RequestQueue mrequestQueue;
    //private final String url = "http://orientapp.96.lt/obtener_medio.php";
    private final String url = "http://educacionlzc.com/obtener_superior.php";
    private final String TAG = "Prueba";

    List<Integer> lstImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // initData();

        HorizontalInfiniteCycleViewPager pager = (HorizontalInfiniteCycleViewPager) findViewById(R.id.horizontal_cycle);
        MyAdapter adapter = new MyAdapter(lstImages, getBaseContext());
        pager.setAdapter(adapter);


    }
/*
    private void initData() {
        lstImages.add(R.drawable.img1);
        lstImages.add(R.drawable.img2);
        lstImages.add(R.drawable.img3);
    } */
}