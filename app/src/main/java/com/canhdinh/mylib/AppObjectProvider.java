package com.canhdinh.mylib;

import android.content.Context;

import com.canhdinh.AppProvider;
import com.canhdinh.ObjectProviderInterface;
import com.canhdinh.api.ApiManagement;
import com.canhdinh.helper.AppCleanerHelper;
import com.canhdinh.helper.AuthHelper;
import com.canhdinh.helper.ConnectivityHelper;
import com.canhdinh.helper.FileHelper;
import com.canhdinh.helper.ImageHelper;
import com.canhdinh.helper.InstallationHelper;
import com.canhdinh.helper.LanguageHelper;

import java.security.Security;

public class AppObjectProvider  implements ObjectProviderInterface {
    private final Context context;

    // singleton instances
    private SharePrefs preferences;
    private InstallationHelper installationHelper;
    private Security security;
    //    private DatabaseHelper databaseHelper;
    private ImageHelper imageHelper;
    private AppCleanerHelper appCleanerHelper;
    private FileHelper fileHelper;
    private ConnectivityHelper connectivityHelper;
    private ApiManagement apiManagement;
    private LanguageHelper languageHelper;
    private AuthHelper authHelper;

    public AppObjectProvider(Context context) {
        this.context = context;

       ObjectProviderInterface objectProviderInterface1 = new AppObjectProvider(context);

        AppProvider.init(objectProviderInterface1);
    }

//    @Override
//    public DatabaseHelper getDatabaseHelper() {
//        return (databaseHelper == null) ? (databaseHelper = new DatabaseHelper(context)) : databaseHelper;
//    }

    @Override
    public ImageHelper getImageHelper() {
        return (imageHelper == null) ? (imageHelper = new ImageHelper(context)) : imageHelper;
    }

    @Override
    public SharePrefs getPreferences() {
        return (preferences == null) ? (preferences = new SharePrefs(context)) : preferences;
    }

    @Override
    public InstallationHelper getInstallationHelper() {
        return (installationHelper == null) ? (installationHelper = new InstallationHelper(context)) : installationHelper;
    }

    @Override
    public AppCleanerHelper getAppCleanerHelper() {
        return (appCleanerHelper == null) ? (appCleanerHelper = new AppCleanerHelper(context, getInstallationHelper())) : appCleanerHelper;
    }

    public FileHelper getFileHelper() {
        return (fileHelper == null) ? (fileHelper = new FileHelper(context)) : fileHelper;
    }

    @Override
    public ConnectivityHelper getConnectivityHelper() {
        return (connectivityHelper == null) ? (connectivityHelper = new ConnectivityHelper(context)) : connectivityHelper;
    }

    public ApiManagement getApiManagement() {
        return (apiManagement == null) ? (apiManagement = new ApiManagement()) : apiManagement;
    }

    @Override
    public LanguageHelper getLanguageHelper() {
        return (languageHelper == null) ? (languageHelper = new LanguageHelper()) : languageHelper;
    }

    @Override
    public AuthHelper getAuthHelper() {
        return (authHelper == null) ? (authHelper = new AuthHelper()) : authHelper;
    }
}