package com.xdroid.banner.library.interfaces;

import com.xdroid.banner.library.AutoScrollMode;
import com.xdroid.banner.library.AutoScroller;

/**
 * provide some public method for AutoScroller
 * @author Robin
 * @since 2015-04-26 19:18:44
 * @Date_Last_Updated 2015-04-27 14:11:56
 */
public interface AutoScrollerMethodProvider {

	    public void scroll();
	   
	    public void scroll(int start);
	    
	    public void scroll(int start, AutoScrollMode mAutoScrollMode);
	    
	    public void pause();

	    public void resume();
	    
	    public void stop();
	    
	    public void skipNext();

	    public AutoScroller setTimeInterval(int timeInterval);

	    public AutoScroller setAutoScrollMode(AutoScrollMode autoScrollMode);
	    
	    public AutoScrollMode getetAutoScrollMode();
	    
	    public void setRealTotal(int size);
	    
	    public int getRealTotal(); 
	    
}
