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

public class Main_info_medio_superior extends AppCompatActivity {


    TextView text;
    //String IP = "http://orientapp.96.lt/";
    String IP = "http://educacionlzc.com/";
    String GETInfo = IP + "/obtener_medio_por_id.php";
    String GETCarrera = IP + "/getBachilleratoMS.php";
    String GETBeca = IP + "/obtener_becas_medio.php";
    String GETActividad = IP + "/obtener_actividades_medio.php";
    String GETContacto = IP + "/getContactoMS.php";
    String GETHorario = IP + "/getHorarioMS.php";


    obtenerWebService hiloconexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info_medio_superior);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        text = (TextView) findViewById(R.id.texto1);


        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lateral, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = getIntent();
        final String id = i.getStringExtra("ide");
        String cadenaaux;
        switch (item.getItemId()) {


            case R.id.optinfo:
                hiloconexion = new obtenerWebService();
                cadenaaux = GETInfo + "?id_medio_superior=" + id;
                hiloconexion.execute(cadenaaux, "1");
                return true;

            case R.id.optextra:
                hiloconexion = new obtenerWebService();
                cadenaaux = GETHorario + "?id_medio_superior=" + id;
                hiloconexion.execute(cadenaaux, "8");
                return true;

            case R.id.optficha:
                hiloconexion = new obtenerWebService();
                 cadenaaux = GETInfo + "?id_medio_superior=" + id;
                hiloconexion.execute(cadenaaux, "2");
                return true;

            case R.id.optservicios:
                hiloconexion = new obtenerWebService();
                cadenaaux = GETInfo + "?id_medio_superior=" + id;
                hiloconexion.execute(cadenaaux, "3");
                return true;

            case R.id.optcontacto:
                hiloconexion = new obtenerWebService();
                 cadenaaux = GETContacto + "?id_medio_superior=" + id;
                hiloconexion.execute(cadenaaux, "4");
                return true;
            case R.id.carrera:
                hiloconexion = new obtenerWebService();
            cadenaaux = GETCarrera + "?id_medio_superior=" + id;
            hiloconexion.execute(cadenaaux, "5");
            return true;

            case R.id.optbecas:
                hiloconexion = new obtenerWebService();
                 cadenaaux = GETBeca + "?id_medio_superior=" + id;
                hiloconexion.execute(cadenaaux, "6");
                return true;

            case R.id.optrecreativas:
                hiloconexion = new obtenerWebService();
                 cadenaaux = GETActividad + "?id_medio_superior=" + id;
                hiloconexion.execute(cadenaaux, "7");
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

                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                        if (resultJSON == "1") {
                            // JSONArray escuelasmsJSON = respuestaJSON.getJSONArray("ms");

                            devuelve = devuelve + "Misión:" +"\n"+ respuestaJSON.getJSONObject("ms").getString("mision") + "\n " +"\n"+
                                    "Visión:"+ "\n " + respuestaJSON.getJSONObject("ms").getString("objetivo")+"\n"+"\n"+
                                    "Objetivos:"+ "\n " + respuestaJSON.getJSONObject("ms").getString("descripcion");
    /*    URL urlimagen = new URL(IMAGENES + respuestaJSON.getJSONObject("ms").getString("logo"));
        HttpURLConnection conimagen =(HttpURLConnection)urlimagen.openConnection();
        conimagen.connect();
        bitmap = BitmapFactory.decodeStream(conimagen.getInputStream());
    */

                        } else if (resultJSON == "2") {
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

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                        if (resultJSON == "1") {
     devuelve = devuelve + "Fecha de entrega de ficha:" + "\n"+respuestaJSON.getJSONObject("ms").getString("Fecha_entrega_fichass") + "\n " + "\n"+
             "Costo:" +"\n"+ respuestaJSON.getJSONObject("ms").getString("costo_ficha") + " \n" + "\n"+
             "Requisitos:" + "\n"+respuestaJSON.getJSONObject("ms").getString("requisitos_ficha") +"\n"+ "\n"+
             "Fecha de examen de admision:"+"\n"+respuestaJSON.getJSONObject("ms").getString("fecha_examen_admision")+"\n"+ "\n"+
            // "Fecha de resultados:"+"\n"+ respuestaJSON.getJSONObject("ms").getString("fecha_resultados")+"\n"+
             "Fecha de curso propedeutico:"+"\n"+respuestaJSON.getJSONObject("ms").getString("Fecha_propedeutico")+ "\n";
             //"Fecha de inscripcion:"+"\n"+respuestaJSON.getJSONObject("ms").getString("fecha_inscripcion");

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

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                        if (resultJSON == "1") {
                            devuelve = devuelve + "Servicios:" + "\n"+respuestaJSON.getJSONObject("ms").getString("servicios") + "\n ";

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


                        String resultJSON = respuestaJSON.getString("estado");

                        if(resultJSON=="1"){

                            JSONArray escuelassJSON = respuestaJSON.getJSONArray("ms");
                            for(int i=0;i<escuelassJSON.length();i++){
                                devuelve = devuelve + "Dirección:" + "\n"+escuelassJSON.getJSONObject(i).getString("calle") + " "+
                                        escuelassJSON.getJSONObject(i).getString("colonia") + " "+
                                        escuelassJSON.getJSONObject(i).getString("num") + " "+
                                        "\n" +
                                        "Telefono:"  +  "\n"+escuelassJSON.getJSONObject(i).getString("tel") + "\n" +
                                        "Correo Electrónico:"  +  "\n"+escuelassJSON.getJSONObject(i).getString("email") + "\n" +
                                        "Pagina web:"  +  "\n"+escuelassJSON.getJSONObject(i).getString("pagina") + "\n" +
                                        "Facebook:"  + "\n"+ escuelassJSON.getJSONObject(i).getString("face") + "\n" ;

                            }

                        }
/*                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                        if (resultJSON == "1") {

                            devuelve = devuelve + "Direccion:" + respuestaJSON.getJSONObject("ms").getString("calle")+ " " +
                                    respuestaJSON.getJSONObject("ms").getString("colonia")+
                                    respuestaJSON.getJSONObject("ms").getString("num")+
                                    "\n"+ "Telefono:"+ respuestaJSON.getJSONObject("ms").getString("tel")+
                            "\n"+"Correo electronico:"+ respuestaJSON.getJSONObject("ms").getString("email")
                            + "/n" +"Pagina web:"+ respuestaJSON.getJSONObject("ms").getString("pagina") + "/n"+
                                    "Facebook:"+ respuestaJSON.getJSONObject("ms").getString("face") + "/n"+
                                    "Twitter:"+ respuestaJSON.getJSONObject("ms").getString("twit");

                        } else if (resultJSON == "2") {
                            devuelve = "No hay alumnos";
                        }
*/
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
                                devuelve = devuelve + escuelasmsJSON.getJSONObject(i).getString("nombre")+ "\n"+
                                        escuelasmsJSON.getJSONObject(i).getString("perfil")+ "\n"+"\n";
                        }  } else if (resultJSON == "2") {
                            devuelve = "No hay registro";
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
                            JSONArray escuelasmsJSON = respuestaJSON.getJSONArray("ms");
                            for(int i=0;i<escuelasmsJSON.length();i++){
                                devuelve = devuelve + escuelasmsJSON.getJSONObject(i).getString("nombre") + "\n "
                                        + "Descripción de Beca:" +"\n"+ escuelasmsJSON.getJSONObject(i).getString("descripcion")+"\n";
                            }  } else if (resultJSON == "2") {
                            devuelve = "No hay registro";
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
                            JSONArray escuelasmsJSON = respuestaJSON.getJSONArray("ms");
                            for(int i=0;i<escuelasmsJSON.length();i++){
                                devuelve = devuelve + escuelasmsJSON.getJSONObject(i).getString("nombre_actividad")+"\n";
                            }  }
                        else if (resultJSON == "2") {
                            devuelve = "No hay Actividades";
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
                            JSONArray escuelasmsJSON = respuestaJSON.getJSONArray("ms");
                            for(int i=0;i<escuelasmsJSON.length();i++){
                                devuelve = devuelve + escuelasmsJSON.getJSONObject(i).getString("turno")+ "\n" +
                                        escuelasmsJSON.getJSONObject(i).getString("horario")+ "\n";
                            }  } else if (resultJSON == "2") {
                            devuelve = "No hay registro";
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
