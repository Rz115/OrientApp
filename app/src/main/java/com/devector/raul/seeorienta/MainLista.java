package com.devector.raul.seeorienta;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.github.snowdream.android.widget.SmartImageView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;



public class MainLista extends AppCompatActivity {
     private ListView lista;
    ArrayList imagen = new ArrayList();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        lista=(ListView)findViewById(R.id.lista);
descargarImagen();
    }

    private void descargarImagen() {
        imagen.clear();

        final ProgressDialog progressDialog = new ProgressDialog(MainLista.this);
        progressDialog.setMessage("Cargando datos");
        progressDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://educacionlzc.com/getImagenes.php", new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    progressDialog.dismiss();
                    try {
                        JSONArray jsonArray = new JSONArray(new String(responseBody));
                    for(int i=0;i<jsonArray.length();i++){
                        imagen.add(jsonArray.getJSONObject(i).getString("imagen"));
                    }
lista.setAdapter(new ImagenAdapter(getApplicationContext()));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    private class ImagenAdapter extends BaseAdapter {
        Context ctx;
        LayoutInflater layoutInflater;
        SmartImageView smartImageView;

        public ImagenAdapter(Context applicationContext) {
this.ctx=applicationContext;
            layoutInflater=(LayoutInflater)ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return imagen.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewGroup viewGroup =(ViewGroup)layoutInflater.inflate(R.layout.activity_main_item,null);

            smartImageView =(SmartImageView)viewGroup.findViewById(R.id.imagen);
            String urlfinal="http://educacionlzc.com/images"+imagen.get(position).toString();

            Rect rect = new Rect(smartImageView.getLeft(),smartImageView.getTop(),smartImageView.getRight(),smartImageView.getBottom());
            smartImageView.setImageUrl(urlfinal,rect);
            return viewGroup;
        }
    }
}
