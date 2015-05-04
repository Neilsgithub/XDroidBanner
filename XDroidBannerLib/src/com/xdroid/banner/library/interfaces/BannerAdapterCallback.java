package com.xdroid.banner.library.interfaces;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Open interface for create the banner's view and other related methods
 * @author Robin
 * @since 2015-04-28 14:35:46
 * @Date_Last_Updated 2015-05-04 13:56:09
 */
public interface BannerAdapterCallback<EntityType> {

	public View createView(LayoutInflater layoutInflater, final int position,EntityType itemData);
}
