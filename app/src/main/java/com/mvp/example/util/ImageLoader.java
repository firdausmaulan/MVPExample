package com.mvp.example.util;

import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class ImageLoader {

    public static void load(String url, ImageView view) {
        //Picasso.get().load(url).into(view);
        Glide.with(view).load(url).into(view);
    }

}
