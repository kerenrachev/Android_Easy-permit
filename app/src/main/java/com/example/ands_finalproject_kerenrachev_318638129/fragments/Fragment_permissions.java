package com.example.ands_finalproject_kerenrachev_318638129.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ands_finalproject_kerenrachev_318638129.R;
import com.example.ands_finalproject_kerenrachev_318638129.adapters.ApplicationsAdapter;
import com.example.ands_finalproject_kerenrachev_318638129.adapters.PermissionsAdapter;
import com.example.ands_finalproject_kerenrachev_318638129.data.Application;
import com.example.ands_finalproject_kerenrachev_318638129.data.Permission;
import com.example.ands_finalproject_kerenrachev_318638129.utils.PermissionsHandler;

import java.util.ArrayList;
import java.util.Map;

public class Fragment_permissions extends Fragment {

    private RecyclerView permissions_LST_items;
    private TextView permissions_TXT_title;
    private ImageView permissions_IMG_icon;
    private LinearLayout Perms_BTN_all;
    private LinearLayout Perms_BTN_risky;
    private LinearLayout Perms_BTN_notrisky;
    private ImageView permissions_IMG_settings;
    private ArrayList<Permission> allPermissions;
    private ArrayList<Permission> riskyPermissions;
    private ArrayList<Permission> nonRiskyPermissions;
    private String title;
    private Drawable appIcon;
    private Boolean isSpecificAppPermissions;
    private ArrayList<LinearLayout> permission_type_buttons;
    private String applicationPackageName;

    // 0: All permissions, 1: Risky permissions, 2: Not risky permissions
    private int type = 0;

    // This constructor will be called when the user clicks on the "Permissions" option from the bottom navbar
    public Fragment_permissions() {
        allPermissions = new ArrayList<>();
        for(Map.Entry<String, Permission> currPerm: PermissionsHandler.getMe().getPermissions().entrySet()){
            allPermissions.add(currPerm.getValue());
        }
        initiatePermissionsByRisk();
        isSpecificAppPermissions = false;
    }

    // This constructor will be called when the user clicks on one of the applications from the applications list.
    public Fragment_permissions(String title, Drawable appIcon, ArrayList<Permission> permissions, int type, String applicationPackageName) {
        allPermissions= permissions;
        initiatePermissionsByRisk();
        this.title = title;
        this.appIcon = appIcon;
        this.type = type;
        this.applicationPackageName = applicationPackageName;
        isSpecificAppPermissions = true;
    }

    // Fill the riskyPermissions && nonRiskyPermissions arrays.
    private void initiatePermissionsByRisk() {
        riskyPermissions = new ArrayList<>();
        nonRiskyPermissions = new ArrayList<>();
        for(Permission currPerm: allPermissions){
            if(currPerm.getRisky()){
                riskyPermissions.add(currPerm);
            }
            else{
                nonRiskyPermissions.add(currPerm);
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_permissions, container, false);
        findViews(view);
        initButtonsArray(view);
        setTitle();
        setClickListeners();
        initAdapter();
        setSettingsButton();
        return view;
    }

    private void initButtonsArray(View view) {
        permission_type_buttons = new ArrayList<>();
        permission_type_buttons.add(Perms_BTN_all);
        permission_type_buttons.add(Perms_BTN_risky);
        permission_type_buttons.add(Perms_BTN_notrisky);

        for(int i = 0 ; i< permission_type_buttons.size();i++){
            LinearLayout current = permission_type_buttons.get(i);
            if(i == type)
                current.setBackgroundResource(R.color.white);
            else
                current.setBackgroundResource(R.color.light_orange);
        }
    }

    // Clicking on one of the options (All/Risky/Not Risky) will re-initiate this fragment with the specific selected type (0/1/2).
    private void setClickListeners() {
        Perms_BTN_all.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Fragment_permissions fragment_permissions = new Fragment_permissions( title,appIcon, allPermissions, 0, applicationPackageName);
                getParentFragmentManager().beginTransaction().replace(R.id.panel_FRAME_content, fragment_permissions).commit();

            }
        });
        Perms_BTN_risky.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Fragment_permissions fragment_permissions = new Fragment_permissions( title,appIcon, allPermissions, 1, applicationPackageName);
                getParentFragmentManager().beginTransaction().replace(R.id.panel_FRAME_content, fragment_permissions).commit();
            }
        });
        Perms_BTN_notrisky.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Fragment_permissions fragment_permissions = new Fragment_permissions( title,appIcon, allPermissions, 2, applicationPackageName);
                getParentFragmentManager().beginTransaction().replace(R.id.panel_FRAME_content, fragment_permissions).commit();
            }
        });
    }

    // If fragment displaying list of permissions for specific app, replace the title, to indicate which app's permissions are displayed, and display the app icon.
    private void setTitle() {
        if(isSpecificAppPermissions){
            permissions_TXT_title.setText(this.title + "'s Permissions");
            permissions_IMG_icon.setImageDrawable(this.appIcon);
        }
    }

    // If fragment displaying list of permissions for specific app, display the "Setting" orange button, which will open the current application settings screen in device.
    private void setSettingsButton(){
        if(isSpecificAppPermissions){
            permissions_IMG_settings.setVisibility(View.VISIBLE);
            permissions_IMG_settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", applicationPackageName, null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            });
        }
    }
    // Conditionally set the permissions list in the adapter, depending on user selection (All\Risky\Not risky)
    private void initAdapter() {

        switch (type){
            case 0:
                PermissionsAdapter.getMe().setPermissions(allPermissions);
                break;
            case 1:
                PermissionsAdapter.getMe().setPermissions(riskyPermissions);
                break;
            case 2:
                PermissionsAdapter.getMe().setPermissions(nonRiskyPermissions);
                break;
        }
        //PermissionsAdapter.getMe().setPermissions(allPermissions);
        permissions_LST_items.setLayoutManager(new LinearLayoutManager(getContext()));
        permissions_LST_items.setHasFixedSize(true);
        permissions_LST_items.setAdapter(PermissionsAdapter.getMe());
    }

    private void findViews(View view) {
        permissions_LST_items = view.findViewById(R.id.permissions_LST_items);
        permissions_TXT_title = view.findViewById(R.id.permissions_TXT_title);
        permissions_IMG_icon = view.findViewById(R.id.permissions_IMG_icon);
        Perms_BTN_all = view.findViewById(R.id.Perms_BTN_all);
        Perms_BTN_risky = view.findViewById(R.id.Perms_BTN_risky);
        Perms_BTN_notrisky = view.findViewById(R.id.Perms_BTN_notrisky);
        permissions_IMG_settings = view.findViewById(R.id.permissions_IMG_settings);
    }
}