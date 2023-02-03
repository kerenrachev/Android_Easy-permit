package com.example.ands_finalproject_kerenrachev_318638129.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ands_finalproject_kerenrachev_318638129.R;
import com.example.ands_finalproject_kerenrachev_318638129.adapters.ApplicationsAdapter;
import com.example.ands_finalproject_kerenrachev_318638129.data.Application;
import com.example.ands_finalproject_kerenrachev_318638129.utils.PermissionsHandler;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Fragment_apps extends Fragment {


    private RecyclerView apps_LST_items;
    private TextView title_TXT_apps;
    private TextView title_TXT_description;
    private Boolean isGrid = false;
    private String permissionName;
    private ArrayList<Application> allApplications;

    // This constructor is called when the user clicks on "Apps" in the Navbar (will display all apps in device)
    public Fragment_apps() {
        ApplicationsAdapter.getMe().setGrid(false);
        allApplications = PermissionsHandler.getMe().getApplications();
    }

    // This constructor is called when the user clicks on one of the permissions in the permissions fragment.
    // @param: applications: all the applications that use the specific permission
    // @param: permissionName: The permission name.
    public Fragment_apps(boolean isGrid, ArrayList<Application> applications, String permissionName) {
        ApplicationsAdapter.getMe().setGrid(isGrid);
        this.isGrid = isGrid;
        this.allApplications = applications;
        this.permissionName = permissionName;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_apps, container, false);
        findViews(view);
        initAdapter();
        return view;
    }

    private void initAdapter() {
        // Set the applications
        ApplicationsAdapter.getMe().setApplications(allApplications);
        // if isGrid = false, the user wants to display all the applications in the device -> Linear view
        if(!isGrid)
            apps_LST_items.setLayoutManager(new LinearLayoutManager(getContext()));
        else {
            apps_LST_items.setLayoutManager(new GridLayoutManager(getContext(), 2));
            title_TXT_apps.setText(permissionName);
            title_TXT_description.setText("Applications that use this permission");
        }
        apps_LST_items.setHasFixedSize(true);
        apps_LST_items.setAdapter(ApplicationsAdapter.getMe());
    }

    private void findViews(View view) {

        apps_LST_items = view.findViewById(R.id.apps_LST_items);
        title_TXT_apps = view.findViewById(R.id.title_TXT_apps);
        title_TXT_description = view.findViewById(R.id.title_TXT_description);
    }


}