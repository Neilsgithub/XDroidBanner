package com.xdroid.banner.library.interfaces;

import android.support.v4.view.ViewPager;
import android.view.View.OnTouchListener;

import com.xdroid.banner.library.AutoScrollMode;
import com.xdroid.banner.library.adapter.BannerAdapterBase;
import com.xdroid.banner.library.view.XDroidBanner;

/**
 * provide some public method for XDroidBanner
 * @author Robin
 * @since 2015-04-27 08:58:41
 * @Date_Last_Updated 2015-04-27 14:11:14
 */
public interface XDroidBannerMethodProvider {

    public void setViewPagerOnTouchListener(OnTouchListener onTouchListener);

    public void setAdapter(BannerAdapterBase adapter);

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener);
    
    public ViewPager getViewPager();
    
    public XDroidBanner setScrollDuration(int duration);
    
    public XDroidBanner scroll();
	   
    public XDroidBanner scroll(int start);
    
    public XDroidBanner scroll(int start, AutoScrollMode mAutoScrollMode);
    
    public XDroidBanner pause();

    public XDroidBanner resume();
    
    public XDroidBanner stop();
    
    public XDroidBanner skipNext();

    public XDroidBanner setTimeInterval(int timeInterval);

    public XDroidBanner setAutoScrollMode(AutoScrollMode autoScrollMode);
    
    public AutoScrollMode getetAutoScrollMode();
    
    public XDroidBanner setRealTotal(int size);
    
    public int getRealTotal();
    
    public XDroidBanner setInfinite(Boolean infinite);
    
    public Boolean getInfinite();

}
