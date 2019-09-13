package com.example.themagicalbrush;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.themagicalbrush.fragment.PaintingFragment;
import com.example.themagicalbrush.fragment.UserFragment;
import com.example.themagicalbrush.fragment.ChatFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    //ViewPager
    private ViewPager mViewPager;
    //Title
    private List<String> mTitle;
    //Fragment
    private List<Fragment> mFragment;
    //悬浮窗

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //去掉阴影
        //getSupportActionBar().setElevation(0);//设置一个0的阴影

        initData();
        initView();
    }

    //初始化数据
    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add(getResources().getString(R.string.PaintingFragment));
        mTitle.add(getResources().getString(R.string.ChatFragment));
        mTitle.add(getResources().getString(R.string.UserFragment));


        mFragment = new ArrayList<>();
        mFragment.add(new PaintingFragment());
        mFragment.add(new ChatFragment());
        mFragment.add(new UserFragment());

    }

    //初始化View
    @SuppressLint("RestrictedApi")
    private void initView() {

        mTabLayout = findViewById(R.id.mTabLayout);
        mViewPager = findViewById(R.id.main_mViewPager);

        //预加载(提前进行加载)
        mViewPager.setOffscreenPageLimit(mFragment.size());


        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            //返回item的个数
            @Override
            public int getCount() {
                return mFragment.size();
            }

            //设置标题
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        //绑定
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.tab1));
        mTabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.tab2));
        mTabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.tab3));
    }


}
