package com.xdroid.banner.library.indicator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.Color;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.xdroid.banner.library.interfaces.InfiniteIndicatorMethodProvider;

/**
 * A Indicator for ViewPager
 * @author Robin
 * @since 2015-04-29 11:48:29
 * @Date_Last_Updated 2015-05-01 23:52:12
 */
public class InfiniteIndicator extends LinearLayout implements OnPageChangeListener ,InfiniteIndicatorMethodProvider<InfiniteIndicator>{

	/*
	 * property
	 */
	private Drawable mIndicatorSelectedBackground;
	private Drawable mIndicatorUnselectedBackground;
	private int mIndicatorSelectedColor;
	private int mIndicatorUnSelectedColor;
	private int mIndicatorMargin;
	private int mIndicatorWidth;
	private int mIndicatorHeight;
	private Boolean mDoAnim=false;
	
    private int mCurrentPosition = 0;
    private Animator mAnimationOut;
    private Animator mAnimationIn;
    
    private int realTotal;
    
    /*
     * enum
     */
	private ShapeType mShapeType=ShapeType.CIRCLE;
	
	/*
	 * view
	 */
    private ViewPager mViewpager;
    
    /*
     * interfaces
     */
    private OnPageChangeListener mViewPagerOnPageChangeListener;
    
    /*
     * constants
     */
    private final static int DEFAULT_INDICATOR_WIDTH = 5;
    private final static int DEFAULT_INDICATOR_HEIGHT = 5;
    private final static int DEFAULT_INDICATOR_MARGIN = 5;
    
    public static enum ShapeType{
    	CIRCLE,RECT;
    }
    
    public InfiniteIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public InfiniteIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
        init();
    }

    private void init() {
    	initProperty();
    	initColor();
    	initDrawable();
    	initAnimator();
	}

	private void initProperty() {
		mIndicatorWidth =dip2px(DEFAULT_INDICATOR_WIDTH);
    	mIndicatorHeight = dip2px(DEFAULT_INDICATOR_HEIGHT) ;
    	mIndicatorMargin = dip2px(DEFAULT_INDICATOR_MARGIN) ;
	}

	private void initColor() {
		mIndicatorSelectedColor=Color.argb(255, 255, 255, 255);
		mIndicatorUnSelectedColor=Color.argb(120, 255, 255, 255);
	}

	private void initDrawable() {
    	mIndicatorSelectedBackground=createDrawable(mIndicatorSelectedColor);
    	mIndicatorUnselectedBackground=createDrawable(mIndicatorUnSelectedColor);
	}

	private void initAnimator() {
    	AnimatorSet setOut = createAnimator(new float[] { 0.7f , 1.0f } , new float[] { 1.0f , 1.8f });
    	mAnimationOut=setOut;
    	
    	AnimatorSet setIn=createAnimator(new float[] { 1.0f , 0.7f } , new float[] { 1.8f , 1.0f });
    	mAnimationIn =setIn;
	}

	private AnimatorSet createAnimator(float[] alpha,float[] scale) {
		ObjectAnimator alphaAnimator=ObjectAnimator.ofFloat(null, "alpha", alpha[0],alpha[1]);
    	ObjectAnimator scaleXAnimator=ObjectAnimator.ofFloat(null, "scaleX", scale[0],scale[1]);
    	ObjectAnimator scaleYAnimator=ObjectAnimator.ofFloat(null, "scaleY", scale[0],scale[1]);
    	AnimatorSet set=new AnimatorSet();
    	set.play(alphaAnimator).with(scaleXAnimator).with(scaleYAnimator);
    	set.setDuration(500);
		return set;
	}
	
	/*public Drawable createDrawable(int[] color){
		int WIDTH=40; 
		int HEIGHT=40;
		//create bitmap's background
	    int[] colors=new int[WIDTH*HEIGHT];  
        for (int y = 0; y < HEIGHT; y++) {
             for (int x = 0; x < WIDTH; x++) {  
            	   int r = color[1];  
                   int g = color[2];  
                   int b = color[3];  
                   int a = color[0];  
                 colors[y*WIDTH+x]=(a<<24)|(r<<16)|(g<<8)|(b);//the shift operation generates the color ARGB  
             }  
         }
        
        Drawable resaultDrawable=null;
		Bitmap bitmap=Bitmap.createBitmap(colors,WIDTH, HEIGHT, Config.ARGB_8888);  //create bitmap
		if (mShapeType==ShapeType.CIRCLE) {
			Bitmap roundBitmap=getRoundedCornerBitmap(bitmap, 20);  //create rounded bitmap
			resaultDrawable=bitmapToDrawable(roundBitmap);
		}else if (mShapeType==ShapeType.RECT) {
			resaultDrawable=bitmapToDrawable(bitmap);
		}
		
		return resaultDrawable;
	}*/
	
	public Drawable createDrawable(int color){
		int WIDTH=40; 
		int HEIGHT=40;
		
		Paint paint=new Paint();
		paint.setColor(color);
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL_AND_STROKE);
		
		Bitmap bitmap=Bitmap.createBitmap(WIDTH, HEIGHT, Config.ARGB_8888);
		Canvas canvas=new Canvas(bitmap);
		canvas.drawPaint(paint);
		
		   Drawable resaultDrawable=null;
			if (mShapeType==ShapeType.CIRCLE) {
				Bitmap roundBitmap=getRoundedCornerBitmap(bitmap, 20);  //create rounded bitmap
				resaultDrawable=bitmapToDrawable(roundBitmap);
			}else if (mShapeType==ShapeType.RECT) {
				resaultDrawable=bitmapToDrawable(bitmap);
			}
			
			return resaultDrawable;
	}

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (mViewpager == null) {
            throw new NullPointerException("can not find Viewpager , setViewPager first");
        }
        mViewPagerOnPageChangeListener = onPageChangeListener;
        mViewpager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mViewPagerOnPageChangeListener != null) {
            mViewPagerOnPageChangeListener.onPageScrolled(position, positionOffset,
                    positionOffsetPixels);
        }
    }

    @Override 
    public void onPageSelected(int position) {
        if (mViewPagerOnPageChangeListener != null) {
            mViewPagerOnPageChangeListener.onPageSelected(position%realTotal);
        }

        if (mAnimationIn.isRunning()) mAnimationIn.end();
        if (mAnimationOut.isRunning()) mAnimationOut.end();

        View currentIndicator = getChildAt(mCurrentPosition);
        currentIndicator.setBackground(mIndicatorUnselectedBackground);
        if (mDoAnim) {
            mAnimationIn.setTarget(currentIndicator);
            mAnimationIn.start();
		}


        View selectedIndicator = getChildAt(position%realTotal);
        selectedIndicator.setBackground(mIndicatorSelectedBackground);
        if (mDoAnim) {
            mAnimationOut.setTarget(selectedIndicator);
            mAnimationOut.start();
		}

        mCurrentPosition = position%realTotal;
    }

    @Override 
    public void onPageScrollStateChanged(int state) {
        if (mViewPagerOnPageChangeListener != null) {
            mViewPagerOnPageChangeListener.onPageScrollStateChanged(state);
        }
    }

    private void createIndicators(ViewPager viewPager) {
        removeAllViews();
        //int count = viewPager.getAdapter().getCount();
        /*BannerAdapterBase adapter=(BannerAdapterBase) viewPager.getAdapter();
        int count =adapter.getRealTotal();*/
        int count=realTotal;
        if (count <= 0) {
            return;
        }
        
        int realCurrentCount=viewPager.getCurrentItem()%realTotal;
        for (int i = 0; i < count; i++) {
			if (i==realCurrentCount) {
				  addIndicator(mIndicatorSelectedBackground, mAnimationOut);
			}else {
				  addIndicator(mIndicatorUnselectedBackground, mAnimationIn);
			}
		}
        
    }

    private void addIndicator( Drawable background, Animator animator) {
        if (animator.isRunning()) animator.end();

        View Indicator = new View(getContext());
        Indicator.setBackground(background);
        
        addView(Indicator, mIndicatorWidth, mIndicatorHeight);
        LayoutParams lp = (LayoutParams) Indicator.getLayoutParams();
        lp.leftMargin = mIndicatorMargin;
        lp.rightMargin = mIndicatorMargin;
        lp.topMargin=mIndicatorMargin;
        lp.bottomMargin=mIndicatorMargin;
        Indicator.setLayoutParams(lp);

        if (mDoAnim) {
            animator.setTarget(Indicator);
            animator.start();
		}

    }

    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    
    public static Drawable bitmapToDrawable(Bitmap bitmap) {
		@SuppressWarnings("deprecation")
		Drawable drawable = new BitmapDrawable(bitmap);
		return drawable;
	}
    
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}
    
    /*
	 * ===================================================== 
	 * Override InfiniteIndicatorMethodProvider
	 * =====================================================
	 */
    
    /**
     * just used for common ViewPager
     */
    @Override
    public InfiniteIndicator setViewPager(ViewPager viewPager) {
       
        return setViewPager(viewPager, viewPager.getAdapter().getCount());
    }
    
    /**
     * used for infinite ViewPager
     */
	@Override
	public InfiniteIndicator setViewPager(ViewPager viewPager, int count) {
		    mViewpager = viewPager;
	       // BannerAdapterBase adapter=(BannerAdapterBase) mViewpager.getAdapter();
	       // realTotal=adapter.getRealTotal();
		    realTotal=count;
	        mCurrentPosition = mViewpager.getCurrentItem()%realTotal;
	        createIndicators(viewPager);
	        mViewpager.setOnPageChangeListener(this);
	        onPageSelected(mCurrentPosition);
		return this;
	}

	@Override
	public InfiniteIndicator setUnSelectColor(int color) {
		mIndicatorUnSelectedColor=color;
		initDrawable();
		if (mViewpager!=null) {
			createIndicators(mViewpager);
		}
		return this;
	}

	@Override
	public InfiniteIndicator setSelectColor(int color) {
		mIndicatorSelectedColor=color;
		initDrawable();
		if (mViewpager!=null) {
			createIndicators(mViewpager);
		}
		return this;
	}

	@Override
	public InfiniteIndicator setUnSelectDrawable(Drawable unselectDrawable) {
		mIndicatorUnselectedBackground=unselectDrawable;
		if (mViewpager!=null) {
			createIndicators(mViewpager);
		}
		return this;
	}

	@Override
	public InfiniteIndicator setSelectDrawable(Drawable selectDrawable) {
		mIndicatorSelectedBackground=selectDrawable;
		if (mViewpager!=null) {
			createIndicators(mViewpager);
		}
		return this;
	}

	@Override
	public InfiniteIndicator setShapeType(ShapeType type) {
		mShapeType=type;
		initDrawable();
		if (mViewpager!=null) {
			createIndicators(mViewpager);
		}
		return this;
	}

	@Override
	public InfiniteIndicator setAnimation(Boolean isAnim) {
		mDoAnim=isAnim;
		if (mViewpager!=null) {
			createIndicators(mViewpager);
		}
		return this;
	}
	
	@Override
	public InfiniteIndicator setIndicatorWidth(int width) {
		mIndicatorWidth=width;
		if (mViewpager!=null) {
			createIndicators(mViewpager);
		}
		return this;
	}

	@Override
	public InfiniteIndicator setIndicatorHeight(int height) {
		mIndicatorHeight=height;
		if (mViewpager!=null) {
			createIndicators(mViewpager);
		}
		return this;
	}

	@Override
	public InfiniteIndicator setIndicatorMargin(int margin) {
		mIndicatorMargin=margin;
		if (mViewpager!=null) {
			createIndicators(mViewpager);
		}
		return this;
	}
	
	@Override
	public InfiniteIndicator setIndicatorDirection(InfiniteIndicatorDirection direction){
		if (direction==InfiniteIndicatorDirection.HORIZONTAL) {
			setOrientation(LinearLayout.HORIZONTAL);
		}else if (direction==InfiniteIndicatorDirection.VERTICAL) {
			setOrientation(LinearLayout.VERTICAL);
		}
		setGravity(Gravity.CENTER);
		if (mViewpager!=null) {
			createIndicators(mViewpager);
		}
        return this;
	}

	@Override
	public Drawable getUnSelectDrawable() {
		return mIndicatorUnselectedBackground;
	}

	@Override
	public Drawable getSelectDrawable() {
		return mIndicatorSelectedBackground;
	}

	@Override
	public ShapeType getShapeType() {
		return mShapeType;
	}


	@Override
	public int getIndicatorWidth() {
		return mIndicatorWidth;
	}

	@Override
	public int getIndicatorHeight() {
		return mIndicatorHeight;
	}

	@Override
	public int getIndicatorMargin() {
		return mIndicatorMargin;
	}

    
}
