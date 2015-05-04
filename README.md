# XDroidBanner
An automatic scroll ViewPager 

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
