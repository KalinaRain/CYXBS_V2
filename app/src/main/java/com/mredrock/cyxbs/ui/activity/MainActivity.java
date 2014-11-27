package com.mredrock.cyxbs.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mredrock.cyxbs.R;
import com.mredrock.cyxbs.ui.adapter.NavListOptionsAdapter;
import com.mredrock.cyxbs.ui.adapter.NavListSettingsAdapter;
import com.mredrock.cyxbs.ui.fragment.EduInquiryFragment;
import com.mredrock.cyxbs.ui.fragment.MyExploreFragment;
import com.mredrock.cyxbs.ui.fragment.PersonalCourseFragment;
import com.mredrock.cyxbs.ui.fragment.SchoolNewsFragment;
import com.mredrock.cyxbs.ui.impl.AppBarImpl;
import com.mredrock.cyxbs.ui.widget.swipebacklayout.app.SwipeBackActivity;
import com.mredrock.cyxbs.util.LogUtils;

import static com.mredrock.cyxbs.util.LogUtils.LOGD;

public class MainActivity extends BaseActivity implements AppBarImpl{
    private static final String TAG = LogUtils.makeLogTag(MainActivity.class);

    // Navigation drawer:
    private static DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private static int currentItem;

    // options for navdrawer items (indices must correspond to the above)
    private static final int[] NAVDRAWER_OPTIONS_RES_ID = new int[]{
            R.string.nav_item_class_schedule,
            R.string.nav_item_edu_inquiry,
            R.string.nav_item_news,
            R.string.nav_item_explore,
    };
    //setting items for navdrawer items (indices must correspond to the above)
    private static final int[] NAVDRAWER_SETTINGS_RES_ID = new int[]{
            R.string.nav_item_setting,
            R.string.nav_item_about,
            R.string.nav_item_change_account,
            R.string.nav_item_exit
    };

    // icons for navdrawer items (indices must correspond to above array)
    private static final int[] NAVDRAWER_ICON_RES_ID = new int[] {
            R.drawable.navi_aboutus,
            R.drawable.navi_aboutus,
            R.drawable.navi_aboutus,
            R.drawable.navi_aboutus
    };

    protected static final int NAVDRAWER_ITEM_COURSE_SCHEDULE = 0;
    protected static final int NAVDRAWER_ITEM_EXAM_SCHEDULE = 1;
    protected static final int NAVDRAWER_ITEM_SCHOOL_NEWS = 2;
    protected static final int NAVDRAWER_ITEM_MY_EXPLORE = 3;

    protected static int CURRENT_NAV_ITEM = -1;

    protected static final int NAVDRAWER_ITEM_SETTING = 0;
    protected static final int NAVDRAWER_ITEM_ABOUT = 1;
    protected static final int NAVDRAWER_ITEM_CHANGE_ACCOUNT = 2;
    protected static final int NAVDRAWER_ITEM_EXIT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureToolbar();
        configureDrawer();
    }

    public void configureToolbar() {
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle(getString(R.string.tittle));
        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                    mDrawerLayout.closeDrawer(Gravity.START);
                } else {
                    mDrawerLayout.openDrawer(Gravity.START);
                }
            }
        });
    }

    @Override
    public void configureToolbar(int title) {

    }

    private void configureDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,
                R.string.nav_drawer_open,
                R.string.nav_drawer_closed){

            @Override
            public void onDrawerClosed(View view){
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Inner Class NavigationFragment
     */
    public static class NavigationFragment extends Fragment{

        private ListView listOptions = null;
        private ListView listSetting = null;
        private TextView tvGreet = null;

        public NavigationFragment() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if (savedInstanceState == null) {
                goToNavDrawerItem(new PersonalCourseFragment());
            }
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            final View view = inflater.inflate(R.layout.fragment_navigation, container, false);
            initView(view);
            createNavItems();
            return view;
        }

        private void initView(View view) {
            tvGreet = (TextView) view.findViewById(R.id.tv_greet);
            listOptions = (ListView) view.findViewById(R.id.lv_options);
            listSetting = (ListView) view.findViewById( R.id.lv_setting);
        }

        private void createNavItems() {

            final NavListOptionsAdapter optionsAdapter = new NavListOptionsAdapter(getActivity(),NAVDRAWER_OPTIONS_RES_ID,NAVDRAWER_ICON_RES_ID);
            final NavListSettingsAdapter settingsAdapter = new NavListSettingsAdapter(getActivity(),NAVDRAWER_SETTINGS_RES_ID);

            listOptions.setAdapter(optionsAdapter);
            listSetting.setAdapter(settingsAdapter);

            adapterList(listOptions);
            adapterList(listSetting);
            //make the first one highlight defaultly;
            optionsAdapter.setSelectItem(0);

            listOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //make the place which be selected highlight
                    /*if(parent.getTag() != null){
                        ((View)(parent).getTag()).setBackgroundDrawable(null);
                    }
                    (parent).setTag(view);
                    view.setBackgroundColor(Color.LTGRAY);*/
                    optionsAdapter.setSelectItem(position);
                    selectOptionsItem(position);
                }
            });

            listSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    selectSettingItems(position);
                }
            });
        }

        private void selectOptionsItem(int position) {
            if(position != CURRENT_NAV_ITEM){
                CURRENT_NAV_ITEM = position;
                switch (position){
                    case NAVDRAWER_ITEM_COURSE_SCHEDULE:
                        goToNavDrawerItem(new PersonalCourseFragment());
                        break;
                    case NAVDRAWER_ITEM_EXAM_SCHEDULE:
                        goToNavDrawerItem(new EduInquiryFragment());
                        break;
                    case NAVDRAWER_ITEM_SCHOOL_NEWS:
                        goToNavDrawerItem(new SchoolNewsFragment());
                        break;
                    case NAVDRAWER_ITEM_MY_EXPLORE:
                        goToNavDrawerItem(new MyExploreFragment());
                        break;
                    default:
                        break;
                }
            }
            try{
                mDrawerLayout.closeDrawer(Gravity.START);
            }catch (NullPointerException npe){
                LOGD("TAG","这里居然会空指针！！！");
            }
        }

        private void goToNavDrawerItem(Fragment fragment){
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.tab_fragment, fragment);
            transaction.commit();
        }

        private void selectSettingItems(int position) {
            switch (position){
                case NAVDRAWER_ITEM_SETTING:
                    gotoIntent(SettingActivity.class);
                    break;
                case NAVDRAWER_ITEM_ABOUT:
                    gotoIntent(AboutUsActivity.class);
                    break;
                case NAVDRAWER_ITEM_CHANGE_ACCOUNT:
                    gotoIntent(LoginActivity.class);
                    this.getActivity().finish();
                    break;
                case NAVDRAWER_ITEM_EXIT:
                    this.getActivity().finish();
//                    this.getActivity().overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                    break;
                default:
                    break;
            }
        }

        protected void gotoIntent(Class clazz) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), clazz);
            getActivity().startActivity(intent);

        }

        public static void adapterList(ListView listView) {
            if(listView == null) return;

            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                return;
            }

            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
        }
    }
}
