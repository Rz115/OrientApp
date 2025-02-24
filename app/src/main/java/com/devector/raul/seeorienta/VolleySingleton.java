package com.devector.raul.seeorienta;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton instance = null;
    private RequestQueue mrequestQueue;
    private ImageLoader imageLoader;
    private VolleySingleton(){
mrequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        imageLoader = new ImageLoader(this.mrequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String,Bitmap> mCache = new LruCache<String, Bitmap>(100);
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url,bitmap);
            }
        });
    }

    public static VolleySingleton getInstance(){
        if(instance==null){
            instance = new VolleySingleton();
        }
        return instance;
    }
    public ImageLoader getImageLoader()
    {return this.imageLoader;}
    public RequestQueue getRequestQueue(){
        return mrequestQueue;
    }
}
