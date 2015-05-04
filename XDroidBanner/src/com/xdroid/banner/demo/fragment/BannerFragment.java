package com.xdroid.banner.demo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.xdroid.banner.R;
import com.xdroid.banner.demo.BannerController;
import com.xdroid.banner.demo.base.BaseFragment;
import com.xdroid.banner.library.AutoScrollMode;
import com.xdroid.banner.library.indicator.InfiniteIndicator;
import com.xdroid.banner.library.indicator.InfiniteIndicator.ShapeType;
import com.xdroid.banner.library.indicator.InfiniteIndicatorDirection;
import com.xdroid.banner.library.view.XDroidBanner;

/**
 * A sample for XDroidBanner usage
 * @author Robin
 * @since 2015-04-29 23:13:34
 * @Date_Last_Updated 2015-04-30 17:02:48
 */
public class BannerFragment extends BaseFragment {
	
	private InfiniteIndicator indicator;
	private InfiniteIndicator infiniteIndicator;
	private XDroidBanner banner;
	private XDroidBanner infiniteBanner;

	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_banner, null);
		indicator=(InfiniteIndicator) view.findViewById(R.id.indicator);
		infiniteIndicator=(InfiniteIndicator) view.findViewById(R.id.indicator_infinite);
		banner=(XDroidBanner) view.findViewById(R.id.banner);
		infiniteBanner=(XDroidBanner) view.findViewById(R.id.banner_infinite);
		initView(view);
		initBanner();
		
		initInfiniteBanner();
		
		return view;
	}

	private void initView(View view) {
		RadioGroup mScrollMode=(RadioGroup) view.findViewById(R.id.rg_scrollmode);
		SeekBar mTimeInterval=(SeekBar) view.findViewById(R.id.sb_timeinterval);
		SeekBar mSpeed=(SeekBar) view.findViewById(R.id.sb_speed);
		CheckBox mState=(CheckBox) view.findViewById(R.id.cb_state);
		
		mScrollMode.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (group.getId()) {
				case R.id.rg_scrollmode:
					switch (checkedId) {
					case R.id.rb_L2R:
						banner.setAutoScrollMode(AutoScrollMode.LEFT_TO_RIGHT);
						break;

			        case R.id.rb_R2L:
			        	banner.setAutoScrollMode(AutoScrollMode.RIGHT_TO_LEFT);
						break;
			       case R.id.rb_BAF:
			    	 	banner.setAutoScrollMode(AutoScrollMode.BACK_AND_FORTH);
						break;
					}
					break;

				}
				
			}
		});
		
		mTimeInterval.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				banner.setTimeInterval(progress);
			}
		});
		
		mSpeed.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				banner.setScrollDuration(progress);
			}
		});
		
		mState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					banner.pause();
				}else {
					banner.resume();
				}
			}
		});
		
	}
	
	private void initBanner() {
		BannerController controller=new BannerController();
		controller.setData(banner, getData());
		controller.beginAutoScroll();
		
		initIndicator(banner,indicator);
		
	}
	
	private void initInfiniteBanner() {
		BannerController controller=new BannerController();
		controller.setData(infiniteBanner, getData())
		.setTimeInterval(4000)
		.setInfinite(true);
		controller.beginAutoScroll();
		
		initIndicator(infiniteBanner,infiniteIndicator);
	}
	
	private void initIndicator(XDroidBanner banner,InfiniteIndicator indicator){
		indicator.setViewPager(banner.getViewPager(),banner.getRealTotal())
		.setShapeType(ShapeType.CIRCLE)
		.setAnimation(true)
		.setSelectColor(Color.RED)
		.setUnSelectColor(Color.WHITE)
		.setIndicatorDirection(InfiniteIndicatorDirection.HORIZONTAL);
	}


}
