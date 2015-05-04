package com.xdroid.banner.library;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;

import com.xdroid.banner.library.adapter.XDroidBannerAdapter;
import com.xdroid.banner.library.interfaces.BannerAdapterCallback;
import com.xdroid.banner.library.view.XDroidBanner;
/**
 * The banner's base controller
 * @author Robin
 * @since 2015-04-26 21:50:52
 * @Date_Last_Updated 2015-05-01 23:06:32
 */
public abstract class XDroidBannerControllerBase<EntityType> implements BannerAdapterCallback<EntityType> {

	private XDroidBanner mXDroidBanner;
    private XDroidBannerAdapter<EntityType> mBannerAdapter;
    
    public XDroidBanner setData(XDroidBanner mXDroidBanner,List<EntityType> mItemDataList) {
    	this.mXDroidBanner=mXDroidBanner;
    	mBannerAdapter=new XDroidBannerAdapter<EntityType>(mXDroidBanner,this);
        mBannerAdapter.setItemDataList(mItemDataList);
        mXDroidBanner.setAdapter(mBannerAdapter);
        mXDroidBanner.setRealTotal(mBannerAdapter.getRealTotal());
        
        return mXDroidBanner;
    }
    
    public XDroidBanner beginAutoScroll(){
    	  mXDroidBanner.scroll(mXDroidBanner.getInfinite()?(mBannerAdapter.getRealTotal()*180):0);
    	  return mXDroidBanner;
    }
    
    @Override
    public View createView(LayoutInflater layoutInflater, int position,
    		EntityType itemData) {
    	return null;
    }
    
   /* private class XDroidBannerAdapter extends BannerAdapterBase{
        private List<EntityType> mItemDataList;

        private void setItemDataList(List<EntityType> mItemDataList) {
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
        	 View convertView=createView(layoutInflater, getPositionForActual(position), itemData);
        	 return convertView;
        }

        *//**
         * Compute the real position 
         * @param position
         * @return
         *//*
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
    
    }*/
    
//    public abstract View createView(LayoutInflater layoutInflater, final int position,EntityType itemData);
    
}
