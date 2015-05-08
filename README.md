# XDroidBanner
An automatic scroll ViewPager 
一个Banner自动滚动组件（功能强大）和一个可自定义的indicator
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
