package com.xdroid.banner.library;

import android.os.Handler;
import android.os.Looper;

import com.xdroid.banner.library.interfaces.AutoScrollerMethodProvider;
import com.xdroid.banner.library.interfaces.OnAutoScrollerListener;

/**
 * Automatic rolling player
 * @author Robin
 * @since 2015-04-26 18:59:40
 * @Date_Last_Updated 2015-04-27 14:12:34
 */
public class AutoScroller implements AutoScrollerMethodProvider {

	/*
	 * enum
	 */
	private AutoScrollMode mAutoScrollMode = AutoScrollMode.LEFT_TO_RIGHT;
	
	private AutoScrollDirection mAutoScrollDirection=AutoScrollDirection.DIRECTION_LEFT_TO_RIGHT;

	/*
	 * interfaces
	 */
	private OnAutoScrollerListener onAutoScrollerListener;
	
	private Runnable mAutoScrollTask;

	/*
	 * property
	 */
	private int mTimeInterval = 5000;
	/**
	 * To ensure that the time interval when the ViewPager completed transition
	 */
	private boolean mSkipNext = false;
	private int mTotal;
	private int mRealTotal;
	private boolean mScrolling = false;
	private boolean mPaused = false;

	public AutoScroller(OnAutoScrollerListener onAutoScrollerListener) {
		this.onAutoScrollerListener = onAutoScrollerListener;
	}
	
	private void scrollToNext() {
		   if (mSkipNext) {
	            mSkipNext = false;
	            return;
	        }
	        int current = onAutoScrollerListener.getCurrent();
	        if (mAutoScrollMode==AutoScrollMode.LEFT_TO_RIGHT) {
	            if (current == mTotal-1) { //reach the last item
	                onAutoScrollerListener.onScrollTo(0);
	            } else {
	                onAutoScrollerListener.onScrollToNext();
	            }
				
			}else if (mAutoScrollMode==AutoScrollMode.RIGHT_TO_LEFT) {
				  if (current == 0) { //reach the first item
		                onAutoScrollerListener.onScrollTo(mTotal);
		            } else {
		                onAutoScrollerListener.onScrollToPrevious();
		            }
				
			}else if (mAutoScrollMode==AutoScrollMode.BACK_AND_FORTH) {
				if (current%mRealTotal == 0) { //reach the first item
					mAutoScrollDirection=AutoScrollDirection.DIRECTION_LEFT_TO_RIGHT;
				}else if (current%mRealTotal == mRealTotal-1) { //reach the last item
					mAutoScrollDirection=AutoScrollDirection.DIRECTION_RIGHT_TO_LEFT;
				}
				if (mAutoScrollDirection==AutoScrollDirection.DIRECTION_LEFT_TO_RIGHT) {
					  onAutoScrollerListener.onScrollToNext();
				}else if (mAutoScrollDirection==AutoScrollDirection.DIRECTION_RIGHT_TO_LEFT) {
					 onAutoScrollerListener.onScrollToPrevious();
				}
			}
	
	}

	/*
	 * ===================================================== 
	 * Override AutoScrollerMethodProvider
	 * =====================================================
	 */

	@Override
	public void scroll() {
		scroll(0, mAutoScrollMode);
	}

	@Override
	public void scroll(int start) {
		scroll(start, mAutoScrollMode);
	}

	@Override
	public void scroll(int start, AutoScrollMode mAutoScrollMode) {
		if (mScrolling) {
			return;
		}
		mTotal = onAutoScrollerListener.getTotal();
		if (mTotal <= 1) {
			return;
		}
		mScrolling = true;
		onAutoScrollerListener.onScrollTo(start);

		final Handler handler=new Handler(Looper.myLooper());
		mAutoScrollTask=new Runnable() {
			
			@Override
			public void run() {
		          if (!mPaused) {
	                    scrollToNext();
	                }
		          if (mScrolling) {
	                    handler.postDelayed(mAutoScrollTask, mTimeInterval);
	                }
			}

		};
		handler.postDelayed(mAutoScrollTask, mTimeInterval);
		
	}

	@Override
	public void pause() {
        mPaused = true;
	}

	@Override
	public void resume() {
        mPaused = false;
	}

	@Override
	public void stop() {
        if (!mScrolling) {
            return;
        }
        mScrolling = false;
	}

	@Override
	public void skipNext() {
		mSkipNext=true;
	}

	@Override
	public AutoScroller setTimeInterval(int timeInterval) {
		 mTimeInterval = timeInterval;
		return this;
	}

	@Override
	public AutoScroller setAutoScrollMode(AutoScrollMode autoScrollMode) {
		mAutoScrollMode=autoScrollMode;
		return this;
	}

	@Override
	public AutoScrollMode getetAutoScrollMode() {
		return mAutoScrollMode;
	}

	@Override
	public void setRealTotal(int size) {
		mRealTotal=size;
	}

	@Override
	public int getRealTotal() {
		return mRealTotal;
	}

}
