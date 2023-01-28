package com.example.ands_finalproject_kerenrachev_318638129.data;

import java.util.ArrayList;

public class Permission {

    private String permissionName;
    private Boolean isRisky;
    private ArrayList<Application> applications;

    public Permission(){}

    public String getPermissionName() {
        return permissionName;
    }

    public Permission setPermissionName(String permissionName) {
        this.permissionName = permissionName;
        return this;
    }

    public Boolean getRisky() {
        return isRisky;
    }

    public Permission setRisky(Boolean risky) {
        isRisky = risky;
        return this;
    }

    public ArrayList<Application> getApplications() {
        return applications;
    }

    public Permission setApplications(ArrayList<Application> applications) {
        this.applications = applications;
        return this;
    }
}
