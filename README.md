# XDroidBanner
An automatic scroll ViewPager 
一个Banner自动滚动组件（功能强大）和一个可自定义的indicator

业余时间完成的一个强大的Banner组件，可自定义滚动方向，从左到又。从右到左，来回滚动，手指触摸暂停滚动，可自定义滚动速度，滚动时间间隔，无限滚动等

指示器部分可以自定义颜色，形状，图片，垂直或水平，大小间隔等，都可自定义，具体可下载运行看下

效果可以先运行demo看下，相关使用说明后面更新
#Usage
in xml:

              <com.xdroid.banner.library.view.XDroidBanner
                    xmlns:app="http://schemas.android.com/apk/res-auto"
                    android:id="@+id/banner"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:layout_alignParentTop="true" >

                    <android.support.v4.view.ViewPager
                        android:id="@+id/viewpager"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent" />
              </com.xdroid.banner.library.view.XDroidBanner>

in code:

    BannerController controller=new BannerController();
		controller.setData(banner, getData());
		controller.beginAutoScroll();
