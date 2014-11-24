package com.mredrock.cyxbs.ui.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mredrock.cyxbs.R;
import com.mredrock.cyxbs.ui.widget.slidingTabs.SlidingTabLayout;
import com.mredrock.cyxbs.util.LogUtils;

public class PersonalCourseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = LogUtils.makeLogTag(PersonalCourseFragment.class);

    private SwipeRefreshLayout mSwipeLayout;
    private SlidingTabLayout mSlidingTabLayout;

    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_schedule, container, false);
        initView(view);
        return view;
    }
    private void initView(View view){
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container_schedule);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(
                R.color.theme_default_primary,
                android.R.color.holo_blue_bright,
                android.R.color.holo_purple,
                android.R.color.holo_red_light);
    }

    // BEGIN_INCLUDE (fragment_onviewcreated)

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_schedule);
        mViewPager.setAdapter(new SchedulePagerAdapter());
        // END_INCLUDE (setup_viewpager)

        // BEGIN_INCLUDE (setup_slidingtablayout)
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs_schedule);
        mSlidingTabLayout.setViewPager(mViewPager);
        // END_INCLUDE (setup_slidingtablayout)
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(false);
            }
        }, 5000);
    }
    // END_INCLUDE (fragment_onviewcreated)

    class SchedulePagerAdapter extends PagerAdapter {
        final String [] TITLES = {"第 一 周", "第 二 周", "第 三 周", "第 四 周", "第 五 周",
                "第 六 周", "第 七 周", "第 八 周", "第 九 周", "第 十 周",
                "第 十 一 周", "第 十 二 周", "第 十 三 周", "第 十 四 周", "第 十 五 周",
                "第 十 六 周", "第 十 七 周", "第 十 八 周", "学 期"};

        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
            return TITLES.length;
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
         * same object as the {@link View} added to the {@link ViewPager}.
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        // BEGIN_INCLUDE (pageradapter_getpagetitle)
        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p>
         * Here we construct one using the position value, but for real application the title should
         * refer to the item's contents.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
        // END_INCLUDE (pageradapter_getpagetitle)

        /**
         * Instantiate the {@link View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // Inflate a new layout from our resources
            View view = getActivity().getLayoutInflater().inflate(R.layout.item_sliding_pager_schedule,
                    container, false);
            // Add the newly created View to the ViewPager

            container.addView(view);

            // Return the View
            return view;
        }

        /**
         * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
         * {@link View}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
//            Log.i(TAG, "destroyItem() [position: " + position + "]");
        }

    }



}
