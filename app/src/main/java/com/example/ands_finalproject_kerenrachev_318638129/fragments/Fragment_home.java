package com.example.ands_finalproject_kerenrachev_318638129.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ands_finalproject_kerenrachev_318638129.R;
import com.google.android.material.button.MaterialButton;

public class Fragment_home extends Fragment {

    private MaterialButton home_BTN_apps;
    private MaterialButton home_BTN_permissions;
    public Fragment_home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        findViewd(view);
        setClickListeners();
        return view;
    }

    private void setClickListeners() {
        home_BTN_apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_apps fragment_apps = new Fragment_apps();
                getParentFragmentManager().beginTransaction().replace(R.id.panel_FRAME_content, fragment_apps).commit();
            }
        });
        home_BTN_permissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_permissions fragment_permissions = new Fragment_permissions();
                getParentFragmentManager().beginTransaction().replace(R.id.panel_FRAME_content, fragment_permissions).commit();
            }
        });
    }


    private void findViewd(View view) {
        home_BTN_apps = view.findViewById(R.id.home_BTN_apps);
        home_BTN_permissions = view.findViewById(R.id.home_BTN_permissions);
    }
}