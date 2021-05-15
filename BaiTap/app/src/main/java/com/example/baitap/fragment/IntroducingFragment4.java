package com.example.baitap.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.baitap.R;
import com.example.baitap.activity.MainActivity;

public class IntroducingFragment4 extends Fragment {

    private Button btnGetStart;
    private View view;
    public IntroducingFragment4() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       
        view = inflater.inflate(R.layout.fragment_introducing4, container, false);
        btnGetStart = view.findViewById(R.id.btn_get_start);
        btnGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
}