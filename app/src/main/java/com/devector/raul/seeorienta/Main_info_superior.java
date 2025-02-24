package com.devector.raul.seeorienta;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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


public class Main_info_superior extends AppCompatActivity {


    TextView text;

    String IP = "http://educacionlzc.com/";
    String GETInfo = IP + "/obtenerDatosSuperior.php";
    String GETBeca = IP + "/obtenerBecasSuperior.php";
    String GETCarrera = IP + "/obtenerCarrerasSuperior.php";
    String GETContacto = IP + "/obtenerContactoSuperior.php";
    String GETDiplo = IP + "/obtenerDiplomadosSuperior.php";
    String GETRecre = IP + "/obtenerRecreativasSuperior.php";
    String GETHora = IP + "/obtenerHorarioSuperior.php";
    obtenerWebService hiloconexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info_superior);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        text = (TextView) findViewById(R.id.texto);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_super, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = getIntent();
        final String id = i.getStringExtra("ide");
        String cadenaauxs;
        switch (item.getItemId()) {

            case R.id.optprincipios:
                hiloconexion = new obtenerWebService();
                cadenaauxs = GETInfo + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "1");
                return true;

            case R.id.optbecas:
                hiloconexion = new obtenerWebService();
                cadenaauxs = GETBeca + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "2");
                return true;

            case R.id.optcarreras:
                hiloconexion = new obtenerWebService();
                cadenaauxs = GETCarrera + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "3");
                return true;

           /*case R.id.optinstalaciones:
                hiloconexion = new obtenerWebService();
                cadenaauxs = GETInfo + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "4");
                return true;*/

            case R.id.optfichas:
                hiloconexion = new obtenerWebService();
                cadenaauxs = GETInfo + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "4");
                return true;

            case R.id.optservicios:
                hiloconexion = new obtenerWebService();
                cadenaauxs = GETInfo + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "5");
                return true;

            case R.id.optcontacto:
                hiloconexion = new obtenerWebService();
                cadenaauxs = GETContacto + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "6");
                return true;

           /* case R.id.optdiplomado:
                hiloconexion = new obtenerWebService();
                cadenaauxs = GETDiplo + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "8");
                return true; */

            case R.id.optrecreativas:
                hiloconexion = new obtenerWebService();
                cadenaauxs = GETRecre + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "7");
                return true;

            case R.id.opthorario:
                hiloconexion = new obtenerWebService();
                cadenaauxs = GETHora + "?id=" + id;
                hiloconexion.execute(cadenaauxs, "8");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

public class obtenerWebService extends AsyncTask<String, Void, String> {
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


                int respuesta = connection.getResponseCode();
                StringBuilder result = new StringBuilder();

                if (respuesta == HttpURLConnection.HTTP_OK){
                    InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));


                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);        // Paso toda la entrada al StringBuilder
                    }

                    JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena


                    String resultJSON = respuestaJSON.getString("estado");

                    if(resultJSON=="1"){

                        JSONArray escuelassJSON = respuestaJSON.getJSONArray("s");
                        for(int i=0;i<escuelassJSON.length();i++){
                            devuelve = devuelve + "Mision:" + "\n"+escuelassJSON.getJSONObject(i).getString("mision") + "\n" +"\n"+
                                    "Vision:"  +  "\n"+escuelassJSON.getJSONObject(i).getString("vision")+ "\n";

                        }

                    }
                    else if (resultJSON == "2") {
                        devuelve = "No hay alumnos";
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

                        JSONArray escuelassJSON = respuestaJSON.getJSONArray("s");
                        for(int i=0;i<escuelassJSON.length();i++){
                            devuelve = devuelve + "Beca:" + "\n"+escuelassJSON.getJSONObject(i).getString("nombre_beca") + "\n" +
                                    "Descripcion:"  + "\n"+ escuelassJSON.getJSONObject(i).getString("descripcion") + "\n"+"\n";

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

                        JSONArray escuelassJSON = respuestaJSON.getJSONArray("s");
                        for(int i=0;i<escuelassJSON.length();i++){
                            devuelve = devuelve +  escuelassJSON.getJSONObject(i).getString("nombre") +
                                     "\n"+ escuelassJSON.getJSONObject(i).getString("perfil_carrera") +"\n"+"\n";

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
       /* else if (params[1] == "4") {
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
                        devuelve = devuelve + "Direccion:" +  "\n"+respuestaJSON.getJSONObject("ms").getString("imagen");

                    } else if (resultJSON == "2") {
                        devuelve = "No hay alumnos";
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
        }*/
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


                    String resultJSON = respuestaJSON.getString("estado");

                    if(resultJSON=="1"){

                        JSONArray escuelassJSON = respuestaJSON.getJSONArray("s");
                        for(int i=0;i<escuelassJSON.length();i++){
                            devuelve = devuelve + "Requisitos:" +  "\n"+escuelassJSON.getJSONObject(i).getString("requisitos_ficha") + "\n" +
                                    "Costo de ficha:"  + "\n"+ escuelassJSON.getJSONObject(i).getString("costo_ficha") + "\n"
                            +"Entrega de Fichas:"  + "\n"+ escuelassJSON.getJSONObject(i).getString("fecha_entrega_fichass") + "\n" +
                                    "Fecha de examen de admision:"  +  "\n"+escuelassJSON.getJSONObject(i).getString("fecha_examen_admision") + "\n"+
                                    "Fecha de Inscripción:"  + "\n"+ escuelassJSON.getJSONObject(i).getString("fecha_inscripcion");


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
                        JSONArray escuelassJSON = respuestaJSON.getJSONArray("s");
                        for(int i=0;i<escuelassJSON.length();i++){
                            devuelve = devuelve + "Servicios:" + "\n" + escuelassJSON.getJSONObject(i).getString("servicios") + "\n" ;
                        }  } else if (resultJSON == "2") {
                        devuelve = "No tiene servicios registrados";
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
        else if (params[1] == "6") {
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
                        JSONArray escuelassJSON = respuestaJSON.getJSONArray("s");
                        for(int i=0;i<escuelassJSON.length();i++){
                            devuelve = devuelve + "Telefonos:" + "\n"+escuelassJSON.getJSONObject(i).getString("numtel") + "\n" +
                                    "Email:" + "\n"+ escuelassJSON.getJSONObject(i).getString("email") + "\n" +
                                    "Pagina Web:" + "\n"+escuelassJSON.getJSONObject(i).getString("pagina") + "\n" +
                            "Facebook:" + "\n"+escuelassJSON.getJSONObject(i).getString("face") + "\n" +
                                    "Direccion:" + "\n"+escuelassJSON.getJSONObject(i).getString("calle") + " " +
                                    escuelassJSON.getJSONObject(i).getString("colonia") + " "+
                                    escuelassJSON.getJSONObject(i).getString("numlocal")+ "\n" ;
                        }  } else if (resultJSON == "2") {
                        devuelve = "Sin direccion";
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
      /*  else if (params[1] == "8") {
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
                        JSONArray escuelassJSON = respuestaJSON.getJSONArray("s|");
                        for(int i=0;i<escuelassJSON.length();i++){
                           // devuelve = devuelve + "Diplomado:" + "\n" + escuelassJSON.getJSONObject(i).getString("nombre") + "\n" ;
                        }  }
                    else if (resultJSON == "2") {
                        devuelve = "No tiene diplomados registrados";
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
        }*/
        else if (params[1] == "7") {
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
                        JSONArray escuelassJSON = respuestaJSON.getJSONArray("s");
                        for(int i=0;i<escuelassJSON.length();i++){
                            devuelve = devuelve + escuelassJSON.getJSONObject(i).getString("nombre") + "\n"
                            +"Descripcion:" + "\n" + escuelassJSON.getJSONObject(i).getString("descripcion") + "\n";
                        }  }
                    else if (resultJSON == "2") {
                        devuelve = "No tiene diplomados registrados";
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
        else if (params[1] == "8") {
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
                        JSONArray escuelassJSON = respuestaJSON.getJSONArray("s");
                        for(int i=0;i<escuelassJSON.length();i++){
                            devuelve = devuelve +"Turno:" + " " +  escuelassJSON.getJSONObject(i).getString("turno") + " " +
                                    escuelassJSON.getJSONObject(i).getString("horario") + "\n";
                        }  }
                    else if (resultJSON == "2") {
                        devuelve = "No tiene horarios registrados";
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





