package com.devector.raul.seeorienta;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by lenovo on 06/06/2016.
 */
public class MainSuperiorBecas extends Activity{
    ListView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_superior);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listado = (ListView) findViewById(R.id.listafinal);
        obtenerDatos();
    }
    public void obtenerDatos() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://192.168.1.90:82/SeeOrienta/getBecaS.php";
        RequestParams parametros = new RequestParams();
//parametros.put("id",identificador);
        client.post(url, parametros, new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    CargaLista(obtDatosJSON(new String(responseBody)));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
    public void CargaLista(ArrayList<String> datoz) {
        ArrayAdapter<String> adaptador1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datoz);
        listado.setAdapter(adaptador1);
    }




    public ArrayList<String> obtDatosJSON(String response) {
        ArrayList<String> listado = new ArrayList<String>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            String texto = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                texto = jsonArray.getJSONObject(i).getString("nombre_beca") + " \r\n" +
                        jsonArray.getJSONObject(i).getString("descripcion") + "\r\n ";
                listado.add(texto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listado;
    }
}
