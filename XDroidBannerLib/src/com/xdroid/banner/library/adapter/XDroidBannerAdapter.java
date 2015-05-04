package com.xdroid.banner.library.adapter;

import java.util.List;

import com.xdroid.banner.library.interfaces.BannerAdapterCallback;
import com.xdroid.banner.library.view.XDroidBanner;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

/**
 * The banner's adapter
 * @author Robin
 * @since 2015-04-28 14:35:46
 * @Date_Last_Updated 2015-04-28 14:37:50
 */
public class XDroidBannerAdapter<EntityType> extends BannerAdapterBase{
    private List<EntityType> mItemDataList;
    
    private XDroidBanner mXDroidBanner;
    
    private BannerAdapterCallback<EntityType> bannerAdapterCallback;
    
    public XDroidBannerAdapter(XDroidBanner mXDroidBanner,BannerAdapterCallback<EntityType> bannerAdapterCallback){
    	this.mXDroidBanner=mXDroidBanner;
    	this.bannerAdapterCallback=bannerAdapterCallback;
    }

    public void setItemDataList(List<EntityType> mItemDataList) {
    	this.mItemDataList = mItemDataList;
    }

    @Override
    public int getCount() {
        if (mItemDataList == null) {
            return 0;
        }
        return mXDroidBanner.getInfinite()?Integer.MAX_VALUE:getRealTotal();
    }
    
    public EntityType getItem(int position) {
        if (mItemDataList == null)
            return null;
        return mItemDataList.get(getPositionForActual(position));
    }

    @SuppressLint("InflateParams")
	@Override
    public View getView(LayoutInflater layoutInflater, final int position) {
    	 final EntityType itemData = getItem(position);
    	 View convertView=bannerAdapterCallback.createView(layoutInflater, getPositionForActual(position), itemData);
    	 return convertView;
    }

    /**
     * Compute the real position 
     * @param position
     * @return
     */
    public int getPositionForActual(int position) {
        if (null == mItemDataList || mItemDataList.size() == 0) {
            return 0;
        }
        return position % mItemDataList.size();
    }

	@Override
	public int getRealTotal() {
		return mItemDataList.size();
	}

}