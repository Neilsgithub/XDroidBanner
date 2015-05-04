package com.xdroid.banner.demo.base;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xdroid.banner.R;
import com.xdroid.banner.demo.Entity;

/**
 * Base Fragment 
 * @author Robin
 * @since 2015-04-29 23:52:32
 * @Date_Last_Updated 2015-04-30 11:12:32
 */
public abstract class BaseFragment extends Fragment{
	
	protected abstract View createView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState);
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		  View contentView = createView(inflater, container, savedInstanceState);

		return contentView;
	}

	public List<Entity> getData() {
		List<Entity> mItemDataList = new ArrayList<Entity>();
		mItemDataList.add(new Entity("0", R.drawable.icon_1));
		mItemDataList.add(new Entity("1", R.drawable.icon_2));
		mItemDataList.add(new Entity("2", R.drawable.icon_3));
		mItemDataList.add(new Entity("3", R.drawable.icon_4));
		mItemDataList.add(new Entity("4", R.drawable.icon_5));
		return mItemDataList;
	}

	public BaseFragmentActivity getCotext() {
		return (BaseFragmentActivity) getActivity();
	}

	public boolean processBackPressed() {
		return false;
	}
}
