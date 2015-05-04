package com.xdroid.banner.library.interfaces;

/**
 * Automatic rolling callback
 * @author Robin
 * @since 2015-04-26 18:45:39
 * @Date_Last_Updated 2015-04-26 18:52:14
 */
public interface OnAutoScrollerListener {

	public int getTotal();
	public int getCurrent();
	public void onScrollTo(int position);
	public void onScrollToNext();
	public void onScrollToPrevious();
}
