package com.devector.raul.seeorienta.Publicidad;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.devector.raul.seeorienta.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoPublicidad2 extends Fragment {


    public FragmentoPublicidad2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_publicidad2, container, false);
    }

}

/*

public class FragmentoPublicidad2 extends Fragment {
    View v;



    private RecyclerView myrecyclerview;

    private List<ImagenPatrocinio> listaimagen;

//Constructor
    public FragmentoPublicidad2() {
        // Required empty public constructor
    }
//Metodo para crear declarar el recicler de la calse recyclerviewadapterrrrrr
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragmento_lista,container,false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.recyclerimagen);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),listaimagen);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        /*ImageView imageView = (ImageView) v.findViewById(R.id.imagenpubli);
        imageView.setOnTouchListener(new ImageMatrixTouchHandler(v.getContext()));
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        listaimagen = new ArrayList<>();
        listaimagen.add(new ImagenPatrocinio((R.drawable.logoicep)));
        listaimagen.add(new ImagenPatrocinio((R.drawable.casserole)));
        listaimagen.add(new ImagenPatrocinio((R.drawable.logoicep)));


    }

    }*/

