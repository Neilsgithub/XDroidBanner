package com.xdroid.banner.demo.activity;

import com.xdroid.banner.R;
import com.xdroid.banner.demo.base.BaseFragmentActivity;
import com.xdroid.banner.demo.fragment.HomeFragment;

import android.os.Bundle;

public class HomeFragmentActivity extends BaseFragmentActivity {
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_home);
		
		 pushFragmentToBackStack(HomeFragment.class);
	}

	@Override
	protected String getCloseWarning() {
		return null;
	}

	@Override
	protected int getFragmentContainerId() {
		return R.id.fragment_container;
	}

}
