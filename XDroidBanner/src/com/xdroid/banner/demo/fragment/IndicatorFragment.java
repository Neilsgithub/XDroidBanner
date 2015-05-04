package com.xdroid.banner.demo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.xdroid.banner.R;
import com.xdroid.banner.demo.BannerController;
import com.xdroid.banner.demo.base.BaseFragment;
import com.xdroid.banner.demo.view.ColorPickerDialog;
import com.xdroid.banner.demo.view.ColorPickerDialog.OnColorChangedListener;
import com.xdroid.banner.library.indicator.InfiniteIndicator;
import com.xdroid.banner.library.indicator.InfiniteIndicator.ShapeType;
import com.xdroid.banner.library.indicator.InfiniteIndicatorDirection;
import com.xdroid.banner.library.view.XDroidBanner;

/**
 * A sample for InfiniteIndicator usage
 * @author Robin
 * @since 2015-04-29 23:29:59
 * @Date_Last_Updated 2015-04-30 17:29:33
 */
public class IndicatorFragment extends BaseFragment implements OnSeekBarChangeListener, OnClickListener {
	private InfiniteIndicator indicator;
	private XDroidBanner banner;
	
	private InfiniteIndicator indicator_drawable;
	private XDroidBanner banner_drawable;
	@Override
	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_indicator, null);
		indicator=(InfiniteIndicator) view.findViewById(R.id.indicator);
		banner=(XDroidBanner) view.findViewById(R.id.banner);
		
		indicator_drawable=(InfiniteIndicator) view.findViewById(R.id.indicator_drawable);
		banner_drawable=(XDroidBanner) view.findViewById(R.id.banner_drawable);
		
		initView(view);
		initBanner();
		initBannerDrawable();
		
		return view;
	}
	
	/**
	 * init
	 * @param view
	 */
	private void initView(View view) {
		RadioGroup mShape=(RadioGroup) view.findViewById(R.id.rg_shape);
		RadioGroup mDirection=(RadioGroup) view.findViewById(R.id.rg_direction);
		SeekBar mWidth=(SeekBar) view.findViewById(R.id.sb_width);
		SeekBar mHeight=(SeekBar) view.findViewById(R.id.sb_height);
		SeekBar mMargin=(SeekBar) view.findViewById(R.id.sb_margin);
		CheckBox mState=(CheckBox) view.findViewById(R.id.cb_anim);
		Button mSelectColorButton=(Button) view.findViewById(R.id.btn_choose_select_color);
		Button mUnSelectColorButton=(Button) view.findViewById(R.id.btn_choose_unselect_color);
		
		mShape.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_circle:
					indicator.setShapeType(ShapeType.CIRCLE);
					break;

				case R.id.rb_rect:
					indicator.setShapeType(ShapeType.RECT);
					break;
				}
			}
		});
		
		mDirection.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_horizontal:
					indicator.setIndicatorDirection(InfiniteIndicatorDirection.HORIZONTAL);
					break;

				case R.id.rb_vertical:
					indicator.setIndicatorDirection(InfiniteIndicatorDirection.VERTICAL);
					break;
				}
			}
		});
		
		mWidth.setOnSeekBarChangeListener(this);
		mHeight.setOnSeekBarChangeListener(this);
		mMargin.setOnSeekBarChangeListener(this);
		
		mState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					indicator.setAnimation(true);
				}else {
					indicator.setAnimation(false);
				}
			}
		});
		
		mSelectColorButton.setOnClickListener(this);
		mUnSelectColorButton.setOnClickListener(this);
		
	}
	
	private void initBanner() {
		BannerController controller=new BannerController();
		controller.setData(banner, getData());
		controller.beginAutoScroll();
		
		initIndicator(banner,indicator);
		
	}
	
	private void initIndicator(XDroidBanner banner,InfiniteIndicator indicator){
		indicator.setViewPager(banner.getViewPager(),banner.getRealTotal())
		.setShapeType(ShapeType.CIRCLE)
		.setAnimation(false)
		.setSelectColor(Color.argb(180,0,0,255))
		.setUnSelectColor(Color.argb(100,255,255,255))
		.setIndicatorDirection(InfiniteIndicatorDirection.HORIZONTAL)
		;
	}
	
	/**
	 * initialize banner for drawable indicator
	 */
	private void initBannerDrawable() {
		BannerController controller=new BannerController();
		controller.setData(banner_drawable, getData());
		controller.beginAutoScroll();
		
		initIndicatorDrawable(banner_drawable,indicator_drawable);
		
	}
	
	/**
	 * initialize drawable indicator
	 */
	private void initIndicatorDrawable(XDroidBanner banner,InfiniteIndicator indicator){
		indicator.setViewPager(banner.getViewPager(),banner.getRealTotal())
		.setIndicatorWidth(30)
		.setIndicatorHeight(30)
		.setShapeType(ShapeType.CIRCLE)
		.setAnimation(false)
		.setSelectDrawable(getResources().getDrawable(R.drawable.sun))
		.setUnSelectDrawable(getResources().getDrawable(R.drawable.moon))
		.setIndicatorDirection(InfiniteIndicatorDirection.HORIZONTAL)
		;
	}
	

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		switch (seekBar.getId()) {
		case R.id.sb_width:
			indicator.setIndicatorWidth(progress);
			break;

		case R.id.sb_height:
			indicator.setIndicatorHeight(progress);
			break;
		case R.id.sb_margin:
			indicator.setIndicatorMargin(progress);
			break;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_choose_select_color:
	        new ColorPickerDialog(getCotext(), new OnColorChangedListener() {
				
				@Override
				public void colorChanged(int color) {
					indicator.setSelectColor(color);
				}
			}, Color.BLUE).show();
			break;

       case R.id.btn_choose_unselect_color:
			new ColorPickerDialog(getCotext(), new OnColorChangedListener() {
				
				@Override
				public void colorChanged(int color) {
					indicator.setUnSelectColor(color);
				}
			}, Color.WHITE).show();
			break;
		}
	}
}
