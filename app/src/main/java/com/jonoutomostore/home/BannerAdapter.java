package com.jonoutomostore.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.jonoutomostore.R;

public class BannerAdapter extends PagerAdapter {
    Context c;
    String[] data;

    public BannerAdapter(Context c, String[] data) {
        this.c = c;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
        View v= LayoutInflater.from(c).inflate(R.layout.item_banner, container, false);
        ImageView img=v.findViewById(R.id.imgBanner);

        Glide.with(c).load(data[position]).into(img);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
