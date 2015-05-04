package com.xdroid.banner.library.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * The banner's base adapter
 * @author Robin
 * @since 2015-04-26 20:40:58
 * @Date_Last_Updated 2015-04-26 20:44:00
 */
public abstract class BannerAdapterBase extends PagerAdapter {

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(LayoutInflater.from(container.getContext()), position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    
    public  int getPositionForActual(int position){
    	return position;
    }
    
    public abstract int getRealTotal();

    public abstract View getView(LayoutInflater layoutInflater, int position);
}
