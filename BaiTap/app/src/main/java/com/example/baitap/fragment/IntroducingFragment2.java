package com.example.baitap.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.baitap.R;

public class IntroducingFragment2 extends Fragment {

    public IntroducingFragment2() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       
        return inflater.inflate(R.layout.fragment_introducing2, container, false);
    }
}