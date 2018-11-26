package com.example.chaohan.onefamily;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){

        this.context = context;

    }

    public int[] slide_images = {
//            android.R.drawable.
    };

    public String[] slide_headings = {
            "Living in a Neighborhood\nShared with One Family",
            "Location-based Platform",
            "Post Tasks\nOrganize Events",
            "Attend Events\nAccept Tasks",
            "Are You Ready?"
    };

    public String[] slide_descs = {
            "Real experience living with enthusiastic neighbors "+
                    "and having a better feeling of getting involved",
            "People you communicate are people you living with",
            "Invite neighbors to attend your party or post a " +
                    "help-seeking task just like living in a big family",
            "Knowing your neighbors and making friends through neighbor-posted events and tasks",
            "Starting using \"One Family\" \n" + "with Sign up or Log in"
    };

    public int[] slide_background = {
        R.drawable.firstscreen,
            R.drawable.background,
            R.drawable.background,
            R.drawable.background,
            R.drawable.background
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout)o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        TextView slideHeading = (TextView) view.findViewById(R.id.SlideHeading);
        TextView slideDesc = (TextView) view.findViewById(R.id.SlideDesc);
        ImageView slideBack = (ImageView) view.findViewById(R.id.Slidebackground);

        slideHeading.setText(slide_headings[position]);
        slideDesc.setText(slide_descs[position]);
        slideBack.setBackgroundResource(slide_background[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((RelativeLayout)object);

    }


}
