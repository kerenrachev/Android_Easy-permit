package com.example.ands_finalproject_kerenrachev_318638129.pemissions;

import static android.content.pm.PackageInfo.REQUESTED_PERMISSION_GRANTED;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.net.ConnectException;
import java.util.List;

public class PermissionsHandler {

    private final Context context;

    public PermissionsHandler(Context context){
        this.context = context;
    }

    private void getPermissions() {
        PackageManager pm = context.getPackageManager();
        // Get all packages using package manager
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        // Iterate all the packages and collect only user-installed applications info
        for (ApplicationInfo applicationInfo : packages) {

            // Check if the application is system application
            boolean isSystemApp = ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0 || pm.getLaunchIntentForPackage(applicationInfo.packageName) == null);
            if(isSystemApp) continue;
            if(!applicationInfo.sourceDir.startsWith("/data/app/")) continue;

            StringBuffer appNameAndPermissions=new StringBuffer();

            appNameAndPermissions.append("**********************************************************************************\n");
            appNameAndPermissions.append("\napplicationInfo.packageName: "+applicationInfo.packageName+"\n");
            appNameAndPermissions.append("\naApplication name: " + applicationInfo.loadLabel(context.getPackageManager()).toString()+"\n");
            try {

                // Get package info by package name and collect all requested permissions
                PackageInfo packageInfo = pm.getPackageInfo(applicationInfo.packageName, PackageManager.GET_PERMISSIONS);
                //  Get Permissions from the current package
                String[] requestedPermissions = packageInfo.requestedPermissions;
                if(requestedPermissions != null) {

                    // Iterate permissions and collect only system permissions
                    for (int i = 0; i < requestedPermissions.length; i++) {

                        if(requestedPermissions[i].toString().startsWith("android.permission")
                                && (packageInfo.requestedPermissionsFlags[i] & REQUESTED_PERMISSION_GRANTED) != 0)
                            appNameAndPermissions.append(requestedPermissions[i]+"\n");
                    }
                    appNameAndPermissions.append("\n");



                }
                Log.d("pttt",appNameAndPermissions.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }}
    }
}
