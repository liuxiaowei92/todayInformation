package com.study.todayinformation.main.hangzhou.adapter;

import com.study.todayinformation.main.hangzhou.view.JiKeFragment;
import com.study.todayinformation.main.hangzhou.view.ZhiHuFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @authour lxw
 * @function
 * @date 2019/10/11
 */
public class HangzhouViewPageAdapter extends FragmentPagerAdapter {

    private ArrayList<String> titleList=new ArrayList<>();

    public HangzhouViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
        titleList.add("知乎");
        titleList.add("即刻");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ZhiHuFragment();
            case 1:
                return new JiKeFragment();
                default:
        }
        return null;
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
