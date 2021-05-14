package com.example.baitap.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.baitap.R;
import com.example.baitap.adapter.IntroducingViewPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

public class IntroducingActivity extends AppCompatActivity {
    private TextView tv_skip;
    private ViewPager view_pager;
    private RelativeLayout layout_bottom;
    private CircleIndicator CircleIndicator;
    private LinearLayout layout_next;

    private IntroducingViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducing);
        init();

        viewPagerAdapter = new IntroducingViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        view_pager.setAdapter(viewPagerAdapter);
        CircleIndicator.setViewPager(view_pager);
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 3){
                    tv_skip.setVisibility(View.GONE);
                    layout_bottom.setVisibility(View.GONE);
                }
                else
                {
                    tv_skip.setVisibility(View.VISIBLE);
                    layout_bottom.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void init() {
        tv_skip = findViewById(R.id.tv_skip);
        view_pager = findViewById(R.id.view_pager);
        layout_bottom = findViewById(R.id.layout_bottom);
        CircleIndicator = findViewById(R.id.CircleIndicator);
        layout_next = findViewById(R.id.layout_next);

        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_pager.setCurrentItem(3);
            }
        });
        layout_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view_pager.getCurrentItem() < 3)
                    view_pager.setCurrentItem(view_pager.getCurrentItem() + 1);
            }
        });
    }
}