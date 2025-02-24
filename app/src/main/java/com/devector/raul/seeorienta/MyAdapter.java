package com.devector.raul.seeorienta;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raul_ on 17/02/2018.
 */


public class MyAdapter extends PagerAdapter {
    List<Integer> lstImages;
    Context context;
    LayoutInflater layoutInflater;

    public MyAdapter(List<Integer> lstImages, Context context) {
        this.lstImages = lstImages;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);


    }

    @Override
    public int getCount() {
        return lstImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.card_item, container, false);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        imageView.setImageResource(lstImages.get(position));
        container.addView(view);
        return view;
    }
}


