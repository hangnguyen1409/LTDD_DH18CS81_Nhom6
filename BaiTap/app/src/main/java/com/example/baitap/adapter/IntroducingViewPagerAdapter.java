package com.example.baitap.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.baitap.fragment.IntroducingFragment;
import com.example.baitap.fragment.IntroducingFragment2;
import com.example.baitap.fragment.IntroducingFragment3;
import com.example.baitap.fragment.IntroducingFragment4;

public class IntroducingViewPagerAdapter extends FragmentStatePagerAdapter {

    public IntroducingViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new IntroducingFragment();
            case 1:
                return new IntroducingFragment2();
            case 2:
                return new IntroducingFragment3();
            case 3:
                return new IntroducingFragment4();
            default:
                return new IntroducingFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
