package com.sked.android.workmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

public class AttMgmtCheckPermission {
    private final String TAG = "AttMgmtCheckPermission";

    private final String[] permissionList = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    private String disabledPermission;

    public AttMgmtCheckPermission() {
        disabledPermission = null;
    }

    public boolean isComponentEnabled(Context context) {
        boolean isComponentEnabled = true;

        for (int i = 0; permissionList != null && i < permissionList.length; i++) {
            int permissionCheck = ContextCompat.checkSelfPermission(context, permissionList[i]);

            if (PackageManager.PERMISSION_DENIED == permissionCheck) {
                disabledPermission = permissionList[i];
                isComponentEnabled = false;
                break;
            }
        }

        return (isComponentEnabled);
    }

    public String getDisabledPermission() {
        return (disabledPermission);
    }

    public String[] getPermissionList() {
        return (permissionList);
    }
}