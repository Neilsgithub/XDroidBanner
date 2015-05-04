package com.xdroid.banner.library.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.xdroid.banner.library.AutoScrollMode;
import com.xdroid.banner.library.AutoScroller;
import com.xdroid.banner.library.SpeedScroller;
import com.xdroid.banner.library.adapter.BannerAdapterBase;
import com.xdroid.banner.library.interfaces.OnAutoScrollerListener;
import com.xdroid.banner.library.interfaces.XDroidBannerMethodProvider;

/**
 * ViewPager Container
 * @author Robin
 * @since 2015-04-26 21:00:51
 * @Date_Last_Updated 2015-04-28 09:25:27
 */
public class XDroidBanner extends RelativeLayout implements XDroidBannerMethodProvider{

    protected int mTimeInterval = 2000;
    private ViewPager mViewPager;
    private BannerAdapterBase mBannerAdapterBase;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private AutoScroller mAutoScroller;
    private OnTouchListener mViewPagerOnTouchListener;
    private OnAutoScrollerListener onAutoScrollerListener;
    private Boolean mInfinite=false;

    public XDroidBanner(Context context) {
        this(context, null);
        init(context);
    }

	public XDroidBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
	
    private void init(Context context) {
      
        onAutoScrollerListener=new OnAutoScrollerListener() {
    		
    		@Override
    		public void onScrollToPrevious() {
    		     mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
    		}
    		
    		@Override
    		public void onScrollToNext() {
    			 mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
    		}
    		
    		@Override
    		public void onScrollTo(int position) {
    			mViewPager.setCurrentItem(position, true);
    		}
    		
    		@Override
    		public int getTotal() {
    		     return mBannerAdapterBase.getCount();
    		}
    		
    		@Override
    		public int getCurrent() {
    			  return mViewPager.getCurrentItem();
    		}
    	};
		
	}

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mAutoScroller != null) {
                    mAutoScroller.pause();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mAutoScroller != null) {
                    mAutoScroller.resume();
                }
                break;
            default:
                break;
        }
        if (mViewPagerOnTouchListener != null) {
            mViewPagerOnTouchListener.onTouch(this, ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onFinishInflate() {
    	for (int i = 0; i < getChildCount(); i++) {
			View childView=getChildAt(i);
			if (childView instanceof ViewPager) {
				mViewPager=(ViewPager) childView;
			}
		}
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position,float positionOffset, int positionOffsetPixels) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                mAutoScroller.skipNext();

                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageScrollStateChanged(state);
                }
            }
        });

        mAutoScroller = new AutoScroller(onAutoScrollerListener);
        mAutoScroller.setTimeInterval(mTimeInterval);
    }

	/*
	 * ===================================================== 
	 * Override XDroidBannerMethodProvider
	 * =====================================================
	 */
    @Override
    public void setViewPagerOnTouchListener(OnTouchListener onTouchListener) {
        mViewPagerOnTouchListener = onTouchListener;
    }

    @Override
    public void setAdapter(BannerAdapterBase adapter) {
        mBannerAdapterBase = adapter;
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mOnPageChangeListener = listener;
    }
    
    @Override
    public ViewPager getViewPager(){
    	return mViewPager;
    }
    
	@Override
	public XDroidBanner setScrollDuration(int duration) {
		SpeedScroller scroller = new SpeedScroller(getContext()); 
		scroller.setScrollDuration(duration); 
		scroller.initViewPagerScroll(mViewPager);
		return this;
	}

	@Override
	public XDroidBanner scroll() {
		mAutoScroller.scroll();
		return this;
	}

	@Override
	public XDroidBanner scroll(int start) {
		mAutoScroller.scroll(start);
		return this;
	}

	@Override
	public XDroidBanner scroll(int start, AutoScrollMode mAutoScrollMode) {
		mAutoScroller.scroll(start, mAutoScrollMode);
		return this;
	}

	@Override
	public XDroidBanner pause() {
		mAutoScroller.pause();
		return this;
	}

	@Override
	public XDroidBanner resume() {
		mAutoScroller.resume();
		return this;
	}

	@Override
	public XDroidBanner stop() {
		mAutoScroller.stop();
		return this;
	}

	@Override
	public XDroidBanner skipNext() {
		mAutoScroller.skipNext();
		return this;
	}

	@Override
	public XDroidBanner setTimeInterval(int timeInterval) {
		mAutoScroller.setTimeInterval(timeInterval);
		return this;
	}

	@Override
	public XDroidBanner setAutoScrollMode(AutoScrollMode autoScrollMode) {
		mAutoScroller.setAutoScrollMode(autoScrollMode);
		return this;
	}

	@Override
	public AutoScrollMode getetAutoScrollMode() {
		return mAutoScroller.getetAutoScrollMode();
	}

	@Override
	public XDroidBanner setRealTotal(int size) {
		mAutoScroller.setRealTotal(size);
		return this;
	}

	@Override
	public int getRealTotal() {
		return mAutoScroller.getRealTotal();
	}

	@Override
	public XDroidBanner setInfinite(Boolean infinite) {
		mInfinite=infinite;
		mViewPager.getAdapter().notifyDataSetChanged();
		mViewPager.setCurrentItem((mViewPager.getAdapter().getCount()/2)+1);
		return this;
	}

	@Override
	public Boolean getInfinite() {
		return mInfinite;
	}

}
