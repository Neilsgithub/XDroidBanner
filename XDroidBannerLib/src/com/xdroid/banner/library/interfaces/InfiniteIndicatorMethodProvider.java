package com.xdroid.banner.library.interfaces;

import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;

import com.xdroid.banner.library.indicator.InfiniteIndicator.ShapeType;
import com.xdroid.banner.library.indicator.InfiniteIndicatorDirection;

/**
 * provide some public method for InfiniteIndicator
 * @author Robin
 * @since 2015-04-29 12:05:51
 * @Date_Last_Updated 2015-04-29 12:47:46
 */
public interface InfiniteIndicatorMethodProvider<T> {

	public T setViewPager(ViewPager viewPager);
	
	public T setViewPager(ViewPager viewPager,int count);
	
	public T setUnSelectColor(int color);
	
	public T setSelectColor(int color);
	
	public T setUnSelectDrawable(Drawable unselectDrawable);
	
	public T setSelectDrawable(Drawable selectDrawable);
	
	public T setShapeType(ShapeType type);
	
	public T setAnimation(Boolean isAnim);
	
	public T setIndicatorWidth(int width);
	
	public T setIndicatorHeight(int height);
	
	public T setIndicatorMargin(int margin);
	
	public T setIndicatorDirection(InfiniteIndicatorDirection direction);
	
	public Drawable getUnSelectDrawable();
	
	public Drawable getSelectDrawable();
	
	public ShapeType getShapeType();
	
	public int getIndicatorWidth();
	
	public int getIndicatorHeight();
	
	public int getIndicatorMargin();
}
