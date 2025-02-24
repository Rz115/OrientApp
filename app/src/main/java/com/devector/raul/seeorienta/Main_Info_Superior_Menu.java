package com.devector.raul.seeorienta;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Main_Info_Superior_Menu extends AppCompatActivity {
    ListView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__info__superior__menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listado = (ListView) findViewById(R.id.listView1);
       obtenerDatos();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
  /*  Intent i = getIntent();
    String ide = i.getStringExtra("id");
int identificador = Integer.parseInt(ide);
*/
    public void obtenerDatos() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://185.28.20.91/orientapp.96.lt/conexion.php";
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
                texto = jsonArray.getJSONObject(i).getString("id") + "\r\n" +

                        jsonArray.getJSONObject(i).getString("nombre") + " \r\n"

                ;

                listado.add(texto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listado;
    }
}
