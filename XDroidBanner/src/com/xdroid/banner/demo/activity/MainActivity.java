package com.xdroid.banner.demo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.xdroid.banner.R;
import com.xdroid.banner.demo.BannerController;
import com.xdroid.banner.demo.Entity;
import com.xdroid.banner.library.AutoScrollMode;
import com.xdroid.banner.library.indicator.InfiniteIndicator;
import com.xdroid.banner.library.indicator.InfiniteIndicator.ShapeType;
import com.xdroid.banner.library.indicator.InfiniteIndicatorDirection;
import com.xdroid.banner.library.view.XDroidBanner;

/**
 * MainActivity for demo. You can see the usage 
 * @author Robin
 * @since 2015-04-26 23:23:16
 * @Date_Last_Updated 2015-04-27 09:31:56
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_viewpagerindicator);
		
		BannerController controller=new BannerController();
		XDroidBanner banner=controller.setData((XDroidBanner) findViewById(R.id.banner), getData())
//		.setScrollDuration(1000)
		.setTimeInterval(4000)
		.setAutoScrollMode(AutoScrollMode.LEFT_TO_RIGHT)
		.setInfinite(true);
		
//		initIndicator(banner);
//		initInfiniteIndicator(banner);
		initInfiniteIndicator(banner);
		controller.beginAutoScroll();
	}

//	private void initIndicator(XDroidBanner banner) {
//		CirclePageIndicator indicator=(CirclePageIndicator) findViewById(R.id.indicator);
//		indicator.setViewPager(banner.getViewPager());
//        indicator.setStrokeColor(Color.parseColor("#88FFFFFF")); 
//        indicator.setFillColor(Color.WHITE);
//	}
	
	private void initInfiniteIndicator(XDroidBanner banner){
		InfiniteIndicator indicator=(InfiniteIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(banner.getViewPager(),banner.getRealTotal())
		.setShapeType(ShapeType.CIRCLE)
		.setAnimation(true)
//		.setIndicatorWidth(30)
//		.setIndicatorHeight(30)
//		.setIndicatorMargin(20)
		.setSelectColor(Color.RED)
		.setUnSelectColor(Color.argb(120, 255, 0, 0))
		.setIndicatorDirection(InfiniteIndicatorDirection.HORIZONTAL)
//		.setSelectDrawable(getResources().getDrawable(R.drawable.ic_launcher))
//		.setUnSelectDrawable(getResources().getDrawable(R.drawable.icon_1));
		;
	}
	
	

	private List<Entity> getData() {
		List<Entity> mItemDataList=new ArrayList<Entity>();
		mItemDataList.add(new Entity("0", R.drawable.icon_1));
		mItemDataList.add(new Entity("1", R.drawable.icon_2));
		mItemDataList.add(new Entity("2", R.drawable.icon_3));
		mItemDataList.add(new Entity("3", R.drawable.icon_4));
		return mItemDataList;
	}
}
