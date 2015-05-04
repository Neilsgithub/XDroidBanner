package com.xdroid.banner.demo.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.Toast;


/**
 * 
 * @author Robin
 * @since 2015-04-29 21:49:20
 * @Date_Last_Updated 2015-04-30 09:32:12
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
	
	  protected BaseFragment mCurrentFragment;

	  private boolean mCloseWarned;

	  protected abstract String getCloseWarning();
	  protected abstract int getFragmentContainerId();

    public void pushFragmentToBackStack(Class<?> cls) {
        goToThisFragment(cls);
    }

    protected String getFragmentTag(Class<?> cls) {
        StringBuilder sb = new StringBuilder(cls.toString());
        return sb.toString();
    }

    private void goToThisFragment(Class<?> cls) {
        int containerId = getFragmentContainerId();
        if (cls == null) {
            return;
        }
        try {
            String fragmentTag = getFragmentTag(cls);
            FragmentManager fm = getSupportFragmentManager();
            BaseFragment fragment = (BaseFragment) fm.findFragmentByTag(fragmentTag);
            if (fragment == null) {
                fragment = (BaseFragment) cls.newInstance();
            }
            FragmentTransaction ft = fm.beginTransaction();
            if (fragment.isAdded()) {
                ft.show(fragment);
            } else {
                ft.add(containerId, fragment, fragmentTag);
            }
            mCurrentFragment = fragment;
            
            ft.addToBackStack(fragmentTag);
            ft.commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mCloseWarned = false;
    }
    
    public void popTopFragment(Object data) {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStackImmediate();
        if (tryToUpdateCurrentAfterPop() && mCurrentFragment != null) {
        }
    }

    public void popToRoot(Object data) {
        FragmentManager fm = getSupportFragmentManager();
        while (fm.getBackStackEntryCount() > 1) {
            fm.popBackStackImmediate();
        }
        popTopFragment(data);
    }
    
    /**
     * process the return back logic
     * return true if back pressed event has been processed and should stay in current view
     *
     * @return
     */
    protected boolean processBackPressed() {
        return false;
    }

    /**
     * process back pressed
     */
    @Override
    public void onBackPressed() {

        // process back for fragment
        if (processBackPressed()) {
            return;
        }

        // process back for fragment
        boolean enableBackPressed = true;
        if (mCurrentFragment != null) {
            enableBackPressed = !mCurrentFragment.processBackPressed();
        }
        if (enableBackPressed) {
            int cnt = getSupportFragmentManager().getBackStackEntryCount();
            if (cnt <= 1 && isTaskRoot()) {
                String closeWarningHint = getCloseWarning();
                if (!mCloseWarned && !TextUtils.isEmpty(closeWarningHint)) {
                    Toast toast = Toast.makeText(this, closeWarningHint, Toast.LENGTH_SHORT);
                    toast.show();
                    mCloseWarned = true;
                } else {
                    doReturnBack();
                }
            } else {
                mCloseWarned = false;
                doReturnBack();
            }
        }
    }

    /**
     * update current fragment in stack top
     * @return
     */
    private boolean tryToUpdateCurrentAfterPop() {
        FragmentManager fm = getSupportFragmentManager();
        int cnt = fm.getBackStackEntryCount();
        if (cnt > 0) {
            String name = fm.getBackStackEntryAt(cnt - 1).getName();
            Fragment fragment = fm.findFragmentByTag(name);
            if (fragment != null && fragment instanceof BaseFragment) {
                mCurrentFragment = (BaseFragment) fragment;
            }
            return true;
        }
        return false;
    }

    protected void doReturnBack() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count <= 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStackImmediate();
            /*if (tryToUpdateCurrentAfterPop() && mCurrentFragment != null) {
            }*/
            tryToUpdateCurrentAfterPop();
        }
    }
}
