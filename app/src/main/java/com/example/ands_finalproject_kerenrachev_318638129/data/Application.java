package com.example.ands_finalproject_kerenrachev_318638129.data;


import android.graphics.drawable.Drawable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Application {

    private String applicationName;
    private String applicationPackage;
    private Drawable applicationIcon;
    // Permissions that this application holds.
    private ArrayList<Permission> permissions;

    public Application(){

    }

    public String getApplicationName() {
        return applicationName;
    }

    public Application setApplicationName(String applicationName) {
        this.applicationName = applicationName;
        return this;
    }

    public String getApplicationPackage() {
        return applicationPackage;
    }

    public Application setApplicationPackage(String applicationPackage) {
        this.applicationPackage = applicationPackage;
        return this;
    }

    public Drawable getApplicationIcon() {
        return applicationIcon;
    }

    public Application setApplicationIcon(Drawable applicationIcon) {
        this.applicationIcon = applicationIcon;
        return this;
    }

    public ArrayList<Permission> getPermissions() {
        return permissions;
    }

    public Application setPermissions(ArrayList<Permission> permissions) {
        this.permissions = permissions;
        return this;
    }
}
