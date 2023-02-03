package com.example.ands_finalproject_kerenrachev_318638129.utils;

import static android.content.pm.PackageInfo.REQUESTED_PERMISSION_GRANTED;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.ands_finalproject_kerenrachev_318638129.data.Application;
import com.example.ands_finalproject_kerenrachev_318638129.data.BlackPermissionsList;
import com.example.ands_finalproject_kerenrachev_318638129.data.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PermissionsHandler {

    private static PermissionsHandler me;

    private Context context;

    // Holds all the applications in user's device.
    private ArrayList<Application> applications;

    // HashMap that holds the permission name as key, and permission object as value.
    private HashMap<String,Permission> permissions;

    private PermissionsHandler(Context context) {
        this.context = context;
    }
    public static PermissionsHandler getMe() {
        return me;
    }

    public static PermissionsHandler initHelper(Context context) {
        if (me == null) {
            me = new PermissionsHandler(context);
            PermissionsHandler.getMe().generatePermissions();
        }
        return me;
    }

    public ArrayList<Application> getApplications() {
        return applications;
    }
    public HashMap<String, Permission> getPermissions() {
        return permissions;
    }


    public void generatePermissions() {
        applications = new ArrayList<>();
        permissions = new HashMap<>();

        PackageManager pm = context.getPackageManager();
        // Get all packages using package manager
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        // Iterate all the packages and collect only user-installed applications info
        for (ApplicationInfo applicationInfo : packages) {

            // Check if the application is system application
            boolean isSystemApp = ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0 || pm.getLaunchIntentForPackage(applicationInfo.packageName) == null);
            if(isSystemApp) continue;
            if(!applicationInfo.sourceDir.startsWith("/data/app/")) continue;


            Application app = new Application();
            app.setPermissions(new ArrayList<>());
            app.setApplicationPackage(applicationInfo.packageName);
            app.setApplicationName(applicationInfo.loadLabel(context.getPackageManager()).toString());
            app.setApplicationIcon(pm.getApplicationIcon(applicationInfo));

            try {

                // Get package info by package name and collect all requested permissions
                PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
                //  Get Permissions from the current package
                String[] requestedPermissions = packageInfo.requestedPermissions;
                if(requestedPermissions != null) {

                    // Iterate permissions and collect only system permissions
                    for (int i = 0; i < requestedPermissions.length; i++) {

                        if(requestedPermissions[i].toString().startsWith("android.permission")
                                && (packageInfo.requestedPermissionsFlags[i] & REQUESTED_PERMISSION_GRANTED) != 0){
                            //appNameAndPermissions.append(requestedPermissions[i]+"\n");


                            String permissionName = requestedPermissions[i];
                            // If permission is already defined in map -> just add the application object to the ArrayList
                            if(permissions.containsKey(permissionName)){
                                permissions.get(permissionName).getApplications().add(app);
                            }
                            else{
                                // Create new Permission object to add to the app object.
                                Permission currPermission = new Permission();
                                currPermission.setPermissionName(permissionName);
                                if(checkIfPermissionRisky(permissionName)){
                                    currPermission.setRisky(true);
                                }
                                else currPermission.setRisky(false);
                                currPermission.setApplications(new ArrayList<>());
                                currPermission.getApplications().add(app);
                                permissions.put(permissionName, currPermission);

                            }
                            app.getPermissions().add(permissions.get(permissionName));
                        }
                    }
                }
                applications.add(app);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // Given the permission name, check if it is listed in the "BlackPermissionsList" (hard-coded risky permissions in android)
    private boolean checkIfPermissionRisky(String permissionName) {
        for(String permissionInBlackList: BlackPermissionsList.getMe().getBlackList()){
            if(permissionName.contains(permissionInBlackList)) return true;
        }
        return false;
    }
}
