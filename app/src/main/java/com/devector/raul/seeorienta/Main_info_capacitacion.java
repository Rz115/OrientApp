package com.devector.raul.seeorienta;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main_info_capacitacion extends AppCompatActivity {
    TextView text;
    //String IP = "http://orientapp.96.lt/";
    String IP = "http://educacionlzc.com/";
    String GETInfo = IP + "/obtenerTodoCentro.php";
    String GETCurso = IP + "/obtenerCursosCentro.php";
    String GETContacto = IP + "/obtenerContactoCentro.php";
    obtenerWebServiceCC hiloconexion;
    private CardView cardView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info_capacitacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        text = (TextView) findViewById(R.id.textoc);



    }




    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cap, menu);
        return true;
}
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = getIntent();
        final String id = i.getStringExtra("ide");
        String cadenaauxs;
        switch (item.getItemId()) {

            case R.id.optprincipios:
                hiloconexion = new obtenerWebServiceCC();
                cadenaauxs = GETInfo + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "1");
                return true;

            case R.id.optcursos:
                hiloconexion = new obtenerWebServiceCC();
                cadenaauxs = GETCurso + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "2");
                return true;

            case R.id.optcontacto:
                hiloconexion = new obtenerWebServiceCC();
                cadenaauxs = GETContacto + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "3");
                return true;

            case R.id.optservicios:
                hiloconexion = new obtenerWebServiceCC();
                cadenaauxs = GETInfo + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "4");
                return true;

            /*case R.id.optinstalaciones:
                hiloconexion = new obtenerWebServiceCC();
                cadenaauxs = GETInfo + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "5");
                return true;*/
            case R.id.optrequisitos:
                hiloconexion = new obtenerWebServiceCC();
                cadenaauxs = GETInfo + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "5");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}

    public class obtenerWebServiceCC extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            text.setText(s);
            //super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected String doInBackground(String... params) {
            String cadena = params[0];
            URL url = null;
            String devuelve = "";

            if (params[1] == "1") {

                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                    //connection.setHeader("content-type", "application/json");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                        // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                        // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                        // StringBuilder.

                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena


                        String resultJSON = respuestaJSON.getString("estado");

                        if(resultJSON=="1"){

                            JSONArray escuelassJSON = respuestaJSON.getJSONArray("ms");
                            for(int i=0;i<escuelassJSON.length();i++){
                                devuelve = devuelve + "Mision:"+"\n" + escuelassJSON.getJSONObject(i).getString("mision") + "\n" +
                                        "Vision:" +"\n" +  escuelassJSON.getJSONObject(i).getString("vision");

                            }

                        }
                        else if (resultJSON == "2") {
                            devuelve = "No hay información";
                        }

                    }
                    // hay alumnos a mostrar
                    //JSONArray alumnosJSON = respuestaJSON.getJSONArray("alumnos");

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelve;
            }

            else if (params[1] == "2") {
                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader


                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena


                        String resultJSON = respuestaJSON.getString("estado");

                        if(resultJSON=="1"){

                            JSONArray escuelassJSON = respuestaJSON.getJSONArray("ms");
                            for(int i=0;i<escuelassJSON.length();i++){
                                devuelve = devuelve  + escuelassJSON.getJSONObject(i).getString("nombre") + "\n"
                                        ;

                            }

                        }

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelve;
            }
            else if (params[1] == "3") {
                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader


                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena


                        String resultJSON = respuestaJSON.getString("estado");

                        if(resultJSON=="1"){

                            JSONArray escuelassJSON = respuestaJSON.getJSONArray("ms");
                            for(int i=0;i<escuelassJSON.length();i++){
                                devuelve = devuelve + "Direccion:" +"\n"+ escuelassJSON.getJSONObject(i).getString("calle")+ " " +escuelassJSON.getJSONObject(i).getString("colonia") +
                                       " " + escuelassJSON.getJSONObject(i).getString("num") + "\n" +
                                        "Telefono:"  +  "\n"+escuelassJSON.getJSONObject(i).getString("tel") + "\n"+
                                        "Correo Electronico:"  + "\n"+ escuelassJSON.getJSONObject(i).getString("email") + "\n"+
                                        "Facebook:"  +  "\n"+escuelassJSON.getJSONObject(i).getString("face") + "\n"+
                                        "Página Web:"  +  "\n"+escuelassJSON.getJSONObject(i).getString("pagina");

                            }

                        }
                        else if (resultJSON == "2") {
                            devuelve = "No hay información";
                        }
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelve;
            }
            else if (params[1] == "4") {
                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader


                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                        if (resultJSON == "1") {
                            JSONArray escuelasmsJSON = respuestaJSON.getJSONArray("ms");
                            for(int i=0;i<escuelasmsJSON.length();i++){
                                devuelve = devuelve + escuelasmsJSON.getJSONObject(i).getString("ofrecimientos") + "\n ";
                            }  } else if (resultJSON == "2") {
                            devuelve = "No hay información";
                        }

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelve;
            }
            else if (params[1] == "5") {
                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader


                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                        if (resultJSON == "1") {
                            JSONArray escuelasmsJSON = respuestaJSON.getJSONArray("ms");
                            for(int i=0;i<escuelasmsJSON.length();i++){
                                devuelve = devuelve + escuelasmsJSON.getJSONObject(i).getString("nombre") + "\n ";
                            }  } else if (resultJSON == "2") {
                            devuelve = "No hay información";
                        }

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelve;
            }
           /* else if (params[1] == "5") {
                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader


                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                        if (resultJSON == "1") {
                            JSONArray escuelasmsJSON = respuestaJSON.getJSONArray("ms");
                            for(int i=0;i<escuelasmsJSON.length();i++){
                                devuelve = devuelve + escuelasmsJSON.getJSONObject(i).getString("requisitos") + "\n ";
                            }  } else if (resultJSON == "2") {
                            devuelve = "No hay carreras";
                        }

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelve;
            } */
            else if (params[1] == "5") {
                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader


                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                        if (resultJSON == "1") {
                            JSONArray escuelasmsJSON = respuestaJSON.getJSONArray("ms");
                            for(int i=0;i<escuelasmsJSON.length();i++){
                                devuelve = devuelve + escuelasmsJSON.getJSONObject(i).getString("requisitos") + "\n ";
                            }  } else if (resultJSON == "2") {
                            devuelve = "No hay carreras";
                        }

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devuelve;
            }

            return null;
        }
    }



}
