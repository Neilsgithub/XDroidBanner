package com.xdroid.banner.demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.xdroid.banner.R;
import com.xdroid.banner.demo.base.BaseFragment;

/**
 * 
 * @author Robin
 * @since 2015-04-29 23:09:33
 * @Date_Last_Updated 2015-04-27 09:31:56
 */
public class HomeFragment extends BaseFragment implements OnClickListener {
	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, null);
		view.findViewById(R.id.btn_banner).setOnClickListener(this);
		view.findViewById(R.id.btn_indicator).setOnClickListener(this);
		view.findViewById(R.id.btn_viewpagerindicator).setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_banner:
			getCotext().pushFragmentToBackStack(BannerFragment.class);
			break;

		case R.id.btn_indicator:
			getCotext().pushFragmentToBackStack(IndicatorFragment.class);
			break;
		case R.id.btn_viewpagerindicator:
			getCotext().pushFragmentToBackStack(ViewPagerIndicatorFragment.class);
			break;
		}
	}

}
