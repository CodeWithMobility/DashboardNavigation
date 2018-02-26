package com.mobiledev.dashboardnavigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by manu on 1/7/2018.
 */

public class FragmentSample extends Fragment implements CircleLayout.OnItemSelectedListener, CircleLayout.OnItemClickListener {
   // private PieCharView pieCharView;
   // private TextView tv;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sample, container, false);

        viewPager = view.findViewById(R.id.viewpager);
      //  pieCharView = view.findViewById(R.id.pv);
      //  tv = view.findViewById(R.id.tv);
        CircleLayout circleMenu = (CircleLayout)view.findViewById(R.id.main_circle_layout);

        List<PieCharBean> pieCharBeanList = new ArrayList<>();
        pieCharBeanList.add(new PieCharBean(30f, "piece 1",0));
        pieCharBeanList.add(new PieCharBean(17f, "piece 2",1));
        pieCharBeanList.add(new PieCharBean(15f, "piece 3",2));
        pieCharBeanList.add(new PieCharBean(8f, "piece 4",3));
        pieCharBeanList.add(new PieCharBean(20f, "piece 5",4));
        pieCharBeanList.add(new PieCharBean(10f, "piece 6",5));

       // pieCharView.setOnPieCharListener(this);
      //  pieCharView.setData(pieCharBeanList);

        MyPagerFragmentAdapter  adapterViewPager = new MyPagerFragmentAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //circleMenu.setSelected();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        circleMenu.setOnItemSelectedListener(this);
        circleMenu.setOnItemClickListener(this);



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       // pieCharView.release();
    }

    @Override
    public void onItemSelected(View view, int position, long id, String name) {
  viewPager.setCurrentItem(position,true);
    }

    @Override
    public void onItemClick(View view, int position, long id, String name) {
        viewPager.setCurrentItem(position,true);
         }




    public class MyPagerFragmentAdapter extends FragmentPagerAdapter {
        private int NUM_ITEMS = 8;

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
                case 4: // Fragment # 1 - This will show SecondFragment
                    return ItemFragment.newInstance(2, "Page # 5");
                case 5: // Fragment # 0 - This will show FirstFragment
                    return ItemFragment.newInstance(0, "Page # 1");
                case 6: // Fragment # 0 - This will show FirstFragment different title
                    return ItemFragment.newInstance(1, "Page # 2");
                case 7: // Fragment # 1 - This will show SecondFragment
                    return ItemFragment.newInstance(2, "Page # 3");

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

