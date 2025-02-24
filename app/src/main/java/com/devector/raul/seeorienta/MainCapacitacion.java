package com.devector.raul.seeorienta;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainCapacitacion extends AppCompatActivity {
    ListView lista;


    JsonObjectRequest array;
    RequestQueue mrequestQueue;
    //private final String url = "http://orientapp.96.lt/obtener_medio.php";
    private final String url = "http://educacionlzc.com/obtener_centros.php";
    private final String TAG = "Prueba";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_capacitacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        final RecyclerView recicladora = (RecyclerView) findViewById(R.id.reciclador1);
        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recicladora.setLayoutManager(llm);
        ArrayList<clase_escuelas> datos = new ArrayList<clase_escuelas>();
        mrequestQueue = VolleySingleton.getInstance().getRequestQueue();
        array = new JsonObjectRequest(Request.Method.GET, url, "", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                adaptador_centro adapter1 = new adaptador_centro((ArrayList) getCentro(response));
                recicladora.setAdapter(adapter1);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,error.toString());
            }
        });
        mrequestQueue.add(array);


}
    private List<clase_escuelas> getCentro(JSONObject jsonObject){

        ArrayList<clase_escuelas> lista = new ArrayList<>();
        try {
            JSONArray array = jsonObject.getJSONArray("ms");
            for(int i=0;i<array.length();i++){

                JSONObject objeto = array.getJSONObject(i);
                clase_escuelas clase = new clase_escuelas();

                clase.setNombre_escuela(objeto.getString("nombre"));
                clase.setId(objeto.getString("id_centro"));

                clase.setImagen(objeto.getString("logo"));
                if(i<array.length()){
                    lista.add(clase);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
