package com.devector.raul.seeorienta.Publicidad;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devector.raul.seeorienta.Publicidad.ImagenPatrocinio;
import com.devector.raul.seeorienta.Publicidad.RecyclerViewAdapter;
import com.devector.raul.seeorienta.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoPublicidad extends Fragment {
        View v;
private RecyclerView myrecyclerview;
private List<ImagenPatrocinio> listaimagen;



    public FragmentoPublicidad() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragmento_lista,container,false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.recyclerimagen);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),listaimagen);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        listaimagen = new ArrayList<>();
        listaimagen.add(new ImagenPatrocinio((R.drawable.logoicep)));
        listaimagen.add(new ImagenPatrocinio((R.drawable.logoicep)));
        listaimagen.add(new ImagenPatrocinio((R.drawable.iceprepa)));


    }
}
