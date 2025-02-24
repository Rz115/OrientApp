package com.devector.raul.seeorienta.Publicidad;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.devector.raul.seeorienta.Publicidad.FragmentoPublicidad1;
import com.devector.raul.seeorienta.Publicidad.FragmentoPublicidad10;
import com.devector.raul.seeorienta.Publicidad.FragmentoPublicidad2;
import com.devector.raul.seeorienta.Publicidad.FragmentoPublicidad3;
import com.devector.raul.seeorienta.Publicidad.FragmentoPublicidad4;
import com.devector.raul.seeorienta.Publicidad.FragmentoPublicidad5;
import com.devector.raul.seeorienta.Publicidad.FragmentoPublicidad6;
import com.devector.raul.seeorienta.Publicidad.FragmentoPublicidad7;
import com.devector.raul.seeorienta.Publicidad.FragmentoPublicidad8;
import com.devector.raul.seeorienta.Publicidad.FragmentoPublicidad9;


public class CustomAdapter extends FragmentStatePagerAdapter
{
    public static int LOOPS_COUNT = 1000;



    String[] tabarray = new String[]{"Patrocinio1","Patrocinio2","Patrocinio3","Patrocinio4","Patrocinio5","Patrocinio6","Patrocinio7","Patrocinio8","Patrocinio9"};
    Integer tabnumber = 8;
    public CustomAdapter(FragmentManager fm) {
        super(fm);
    }

  /*  @Override
    public CharSequence getPageTitle(int position) {
        return tabarray[position];
    } */

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                FragmentoPublicidad1 uno = new FragmentoPublicidad1();
                return uno;
            case 1:
                FragmentoPublicidad2 dos = new FragmentoPublicidad2();
                return dos;
            case 2:
                FragmentoPublicidad3 tres = new FragmentoPublicidad3();
                return tres;
            case 3:
                FragmentoPublicidad4 cuatro = new FragmentoPublicidad4();
                return cuatro;
            case 4:
                FragmentoPublicidad5 cinco = new FragmentoPublicidad5();
                return cinco;
            case 5:
                FragmentoPublicidad6 seis = new FragmentoPublicidad6();
                return seis;
            case 6:
                FragmentoPublicidad7 siete = new FragmentoPublicidad7();
                return siete;
            case 7:
                FragmentoPublicidad8 ocho = new FragmentoPublicidad8();
                return ocho;
          /*  case 8:
                FragmentoPublicidad9 nueve = new FragmentoPublicidad9();
                return nueve;
            case 9:
                FragmentoPublicidad10 diez = new FragmentoPublicidad10();
                return diez; */

        }

        return null;
    }

    @Override
    public int getCount() {
        return tabnumber;
    }
}
