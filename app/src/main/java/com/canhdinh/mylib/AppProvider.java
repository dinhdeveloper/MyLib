package com.canhdinh.mylib;

import com.canhdinh.api.ApiManagement;
import com.canhdinh.helper.AppCleanerHelper;
import com.canhdinh.helper.AuthHelper;
import com.canhdinh.helper.ConnectivityHelper;
import com.canhdinh.helper.FileHelper;
import com.canhdinh.helper.ImageHelper;
import com.canhdinh.helper.InstallationHelper;
import com.canhdinh.helper.LanguageHelper;

public class AppProvider  {
    private static ObjectProviderInterface instance;

    public static void init(ObjectProviderInterface objectProviderInterface) {
        instance = objectProviderInterface;
    }

//    public static DatabaseHelper getDatabaseHelper() {
//        return instance.getDatabaseHelper();
//    }

    public static ImageHelper getImageHelper() {
        return instance.getImageHelper();
    }

    public static SharePrefs getPreferences() {
        return instance.getPreferences();
    }

    public static InstallationHelper getInstallationHelper() {
        return instance.getInstallationHelper();
    }

    public static AppCleanerHelper getAppCleanerHelper() {
        return instance.getAppCleanerHelper();
    }

    public static FileHelper getFileHelper() {
        return instance.getFileHelper();
    }

    public static ConnectivityHelper getConnectivityHelper() {
        return instance.getConnectivityHelper();
    }

    public static ApiManagement getApiManagement() {
        return instance.getApiManagement();
    }

    public static LanguageHelper getLanguageHelper() {
        return instance.getLanguageHelper();
    }

    public static AuthHelper getAuthHelper(){
        return instance.getAuthHelper();
    }

}
