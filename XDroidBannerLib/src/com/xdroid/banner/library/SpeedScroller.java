package com.xdroid.banner.library;

import java.lang.reflect.Field;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Control the speed of ViewPager
 * @author Robin
 * @since 2015-04-27 09:15:40
 * @Date_Last_Updated 2015-04-27 09:21:14
 */
public class SpeedScroller extends Scroller {

	private int mScrollDuration = 2000; 

	public void setScrollDuration(int duration) {
		this.mScrollDuration = duration;
	}

	public SpeedScroller(Context context) {
		super(context);
	}

	public SpeedScroller(Context context, Interpolator interpolator) {
		super(context, interpolator);
	}

	public SpeedScroller(Context context, Interpolator interpolator,
			boolean flywheel) {
		super(context, interpolator, flywheel);
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy, int duration) {
		super.startScroll(startX, startY, dx, dy, mScrollDuration);
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy) {
		super.startScroll(startX, startY, dx, dy, mScrollDuration);
	}

	public void initViewPagerScroll(ViewPager viewPager) {
		try {
			Field mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			mScroller.set(viewPager, this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}