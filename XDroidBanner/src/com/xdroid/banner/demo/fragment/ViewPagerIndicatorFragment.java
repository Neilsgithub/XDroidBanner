package com.xdroid.banner.demo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CirclePageIndicator;
import com.xdroid.banner.R;
import com.xdroid.banner.demo.BannerController;
import com.xdroid.banner.demo.base.BaseFragment;
import com.xdroid.banner.library.view.XDroidBanner;

/**
 *  A sample for  ViewPagerIndicator
 * @author Robin
 * @since 2015-04-29 23:30:16
 * @Date_Last_Updated 2015-04-30 18:55:11
 */
public class ViewPagerIndicatorFragment extends BaseFragment {
	private CirclePageIndicator indicator;
	private XDroidBanner banner;
	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_viewpagerindicator, null);
		indicator=(CirclePageIndicator) view.findViewById(R.id.indicator);
		banner=(XDroidBanner) view.findViewById(R.id.banner);
		
		initBanner();
		
		return view;
	}
	private void initBanner() {
		BannerController controller=new BannerController();
		controller.setData(banner, getData())
		.setTimeInterval(1000)
		.setInfinite(false);
		controller.beginAutoScroll();
		
		initIndicator(banner);
		
	}
	
	private void initIndicator(XDroidBanner banner) {
		indicator.setViewPager(banner.getViewPager());
		indicator.setStrokeColor(Color.parseColor("#88FFFFFF"));
		indicator.setFillColor(Color.WHITE);
	}
}
