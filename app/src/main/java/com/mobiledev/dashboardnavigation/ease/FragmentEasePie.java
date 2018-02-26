package com.mobiledev.dashboardnavigation.ease;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mobiledev.dashboardnavigation.FragmentSample;
import com.mobiledev.dashboardnavigation.ItemFragment;
import com.mobiledev.dashboardnavigation.R;

/**
 * Created by manu on 1/7/2018.
 */

public class FragmentEasePie extends ChartFragment {

      ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);
        mPieChart = view.findViewById(R.id.piechart);
        viewPager = view.findViewById(R.id.viewPager);
        loadData();

        MyPagerFragmentAdapter adapterViewPager = new MyPagerFragmentAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mPieChart.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPieChart.startAnimation();
    }

    @Override
    public void restartAnimation() {
        mPieChart.startAnimation();
    }

    @Override
    public void onReset() {

    }

    private void loadData() {
        mPieChart.addPieSlice(new PieModel("Freetime", 35, Color.parseColor("#FE6DA8")));
        mPieChart.addPieSlice(new PieModel("Sleep", 25, Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel("Work", 15, Color.parseColor("#CDA67F")));
        mPieChart.addPieSlice(new PieModel("Eating", 9, Color.parseColor("#FED70E")));

        mPieChart.setOnItemFocusChangedListener(new IOnItemFocusChangedListener() {
            @Override
            public void onItemFocusChanged(int _Position) {
//                Log.d("PieChart", "Position: " + _Position);
                //viewPager.setCurrentItem(_Position,true);
            }
        });
    }

    private PieChart mPieChart;

    public class MyPagerFragmentAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = 4;

        public MyPagerFragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return ItemFragment.newInstance(0, "Page # 1");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return ItemFragment.newInstance(1, "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return ItemFragment.newInstance(2, "Page # 3");
                case 3: // Fragment # 0 - This will show FirstFragment different title
                    return ItemFragment.newInstance(1, "Page # 4");


                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }
    }
}
