package com.r2apps.triangleboard.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 7/29/2016.
 */
public class AppPermissions {
    public static final int PERMISSIONS_REQUEST_READ_WRITE_STORAGE = 1;

    public static boolean checkPermissionsForStorage(Activity activity) {
        boolean isGranted = true;
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;
            List<String> listPermissionsNeeded = new ArrayList<>();

            int permissionPhoneState = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionPhoneState != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSIONS_REQUEST_READ_WRITE_STORAGE);
            }
            return false;
        } else {
            return isGranted;
        }
    }

    public static boolean isAllPermissionsAllowed(Activity activity) {
        boolean flag = true;
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            flag = false;

        }
        return flag;
    }

    public static boolean askForRationalStorage(Activity activity) {
        boolean flag = false;
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            flag = true;
        }

        return flag;
    }
}
