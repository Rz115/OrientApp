package com.devector.raul.seeorienta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class Lista_informacion_superior extends MainActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_superior);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent i = getIntent();
        new ConsultarDatos().execute("http://10.0.2.2/Orienta/consultams.php?id="+1);

        }

}
