package com.devector.raul.seeorienta;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.devector.raul.seeorienta.Publicidad.CustomAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

     ViewPager mViewPager;
     TabLayout tabLayout;
    public static final String TAG = "MainActivity";
    public static final String FRAGTAG = "BasicImmersiveModeFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       //Cuida que no gire la pantalla en  el activity
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Crear las tablas en el activity_main
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        ViewPager Pager =(ViewPager)findViewById(R.id.containertl);
        CustomAdapter Tabpageradapter = new CustomAdapter(getSupportFragmentManager());
        Pager.setAdapter(Tabpageradapter);
        tabLayout.setupWithViewPager(Pager);

        //Cajon de navegacion, declarandolo en el on create
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//para colocar los iconos de cada patrocinio
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_school_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_school_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_local_offer_black_24dp);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_local_offer_black_24dp);
        tabLayout.getTabAt(5).setIcon(R.drawable.ic_local_offer_black_24dp);
        tabLayout.getTabAt(6).setIcon(R.drawable.ic_local_offer_black_24dp);
        tabLayout.getTabAt(7).setIcon(R.drawable.ic_local_offer_black_24dp);
      /*  tabLayout.getTabAt(8).setIcon(R.drawable.ic_local_offer_black_24dp);
        tabLayout.getTabAt(9).setIcon(R.drawable.ic_local_offer_black_24dp); */

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);
        } else {


                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("")
                        .setMessage("¿Seguro que deseas salir?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public class ConsultarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

        }
    }

    public String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        myurl = myurl.replace("","%20");
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(20000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("Respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.ayuda) {


            Intent intent6 = new Intent(this, Ayuda.class);

            startActivity(intent6);
            return true;
        }

       /* if (id == R.id.action_sync) {


            showSnackBar("Sincronizando...");
            return true;
        } */

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.medio_superior) {
            // Handle the camera action
            Intent intent = new Intent(this, MainMSuperior.class);
            startActivity(intent);

        } else if (id == R.id.superior) {
            Intent intent1 = new Intent(this, MainSuperior.class);
            startActivity(intent1);

        } else if (id == R.id.capacitacion) {

            Intent intent2 = new Intent(this, MainCapacitacion.class);

            startActivity(intent2);

        } else if (id == R.id.contacto) {
            Intent intent3 = new Intent(this, MainContacto.class);

            startActivity(intent3);


        } else if (id == R.id.about) {

            Intent intent4 = new Intent(this, MainAcercaSeeOrienta.class);

            startActivity(intent4);

        }
        else if (id == R.id.about_us) {

            Intent intent5 = new Intent(this, MainProyectoSO.class);

            startActivity(intent5);

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}




