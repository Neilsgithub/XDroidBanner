package com.xdroid.banner.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.xdroid.banner.R;
import com.xdroid.banner.library.XDroidBannerControllerBase;

/**
 * The banner's  controller for demo
 * @author Robin
 * @since 2015-04-26 22:22:06
 * @Date_Last_Updated 2015-04-26 23:20:08
 */
public class BannerController extends
		XDroidBannerControllerBase<Entity> {

	@Override
	public View createView(final LayoutInflater layoutInflater, int position,
			final Entity itemData) {
	    View convertView = layoutInflater.inflate(R.layout.view_banner_item, null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.img_banner_item);
        imageView.setAdjustViewBounds(false);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        imageView.setBackgroundResource(itemData.getImageUrl());

        convertView.setTag(itemData);
        convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(layoutInflater.getContext(), "ID:"+itemData.getId(), Toast.LENGTH_SHORT).show();
			}
		});
		return convertView;
	}


}
