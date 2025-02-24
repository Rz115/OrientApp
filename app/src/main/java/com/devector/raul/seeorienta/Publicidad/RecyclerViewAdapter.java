package com.devector.raul.seeorienta.Publicidad;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devector.raul.seeorienta.R;

import java.util.List;

/**
 * Created by raul_ on 15/02/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyviewHolder> {


    Context mContext;
    List<ImagenPatrocinio> mData;

    //Constructor
    public RecyclerViewAdapter(Context mContext, List<ImagenPatrocinio> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.adaptador_publicidad,parent,false);
        MyviewHolder vHolder = new MyviewHolder(v);
        return vHolder;

    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {

       // holder.tv_name.setText(mData.get(position).getName());
        holder.img.setImageResource(mData.get(position).getPhoto());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class  MyviewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name;
        private ImageView img;


        public MyviewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.imagenpubli);

        }
    }
}
