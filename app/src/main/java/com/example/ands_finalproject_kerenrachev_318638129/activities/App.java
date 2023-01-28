package com.example.ands_finalproject_kerenrachev_318638129.activities;

import android.app.Application;

import com.example.ands_finalproject_kerenrachev_318638129.adapters.ApplicationsAdapter;
import com.example.ands_finalproject_kerenrachev_318638129.adapters.PermissionsAdapter;
import com.example.ands_finalproject_kerenrachev_318638129.data.BlackPermissionsList;
import com.example.ands_finalproject_kerenrachev_318638129.utils.PermissionsHandler;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BlackPermissionsList.initHelper(this);
        PermissionsHandler.initHelper(this);
        ApplicationsAdapter.initHelper(this);
        PermissionsAdapter.initHelper(this);
    }
}
