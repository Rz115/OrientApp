package com.devector.raul.seeorienta;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;


public class adaptador extends RecyclerView.Adapter<adaptador.escuelaViewHolder>  {
    ArrayList<clase_escuelas> item;
    private View.OnClickListener listener;

    public adaptador(ArrayList itemm) {

        this.item = itemm;
    }


    public escuelaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_medio_superior, viewGroup, false);
        escuelaViewHolder escuela = new escuelaViewHolder(v);
        return escuela;
    }

    @Override
    public void onBindViewHolder(escuelaViewHolder escuelaViewHolder, int position) {
        escuelaViewHolder.nombre.setText(item.get(position).getNombre_escuela());
        escuelaViewHolder.imagen.setImageUrl(item.get(position).getImagen(), VolleySingleton.getInstance().getImageLoader());
        escuelaViewHolder.id.setText(item.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return item.size();
    }




    public static class escuelaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
Context context;
        TextView nombre;
        NetworkImageView imagen;
        TextView id;

        public escuelaViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
context=itemView.getContext();
            nombre = (TextView) itemView.findViewById(R.id.lblNombre);
            id = (TextView) itemView.findViewById(R.id.lblid);
            imagen = (NetworkImageView) itemView.findViewById(R.id.imgEscuela);


        }

        @Override
        public void onClick(View v) {

            String ide=id.getText().toString();
            v.getContext().startActivity(new Intent(v.getContext(),Main_info_medio_superior.class).putExtra("ide",ide));

        }
    }
}
