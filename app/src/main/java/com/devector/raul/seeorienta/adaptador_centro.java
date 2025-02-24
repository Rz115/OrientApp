package com.devector.raul.seeorienta;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;


public class adaptador_centro extends RecyclerView.Adapter<adaptador_centro.escuelaViewHolder> {
    ArrayList<clase_escuelas> items;

    public adaptador_centro(ArrayList itemm) {
        this.items =  itemm;

    }
    public escuelaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_capacitacion,viewGroup,false);
        escuelaViewHolder escuela = new escuelaViewHolder(v);
        return escuela;
    }

    @Override
    public void onBindViewHolder(escuelaViewHolder escuelaViewHolder, int position) {
        escuelaViewHolder.nombre.setText(items.get(position).getNombre_escuela());
        escuelaViewHolder.imagen.setImageUrl(items.get(position).getImagen(),VolleySingleton.getInstance().getImageLoader());
        escuelaViewHolder.id.setText(items.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class escuelaViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        NetworkImageView imagen;
        TextView id;
        public escuelaViewHolder(View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.lblNombresd);
            id = (TextView) itemView.findViewById(R.id.lblidsd);

            imagen = (NetworkImageView) itemView.findViewById(R.id.imgEscuelasd);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ide=id.getText().toString();
                    v.getContext().startActivity(new Intent(v.getContext(),Main_info_capacitacion.class).putExtra("ide",ide));
                }
            });


        }
    }

}
