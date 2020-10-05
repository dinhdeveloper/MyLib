package com.canhdinh.helper;

import com.canhdinh.api.ApiManagement;

import okhttp3.Interceptor;

public class AppProvider {
    private static ObjectProviderInterface instance;

    public static void init(ObjectProviderInterface objectProviderInterface)
    {
        instance = objectProviderInterface;
    }

    public static ImageHelper getImageHelper()
    {
        return instance.getImageHelper();
    }

    public static InstallationHelper getInstallationHelper()
    {
        return instance.getInstallationHelper();
    }

    public static AppCleanerHelper getAppCleanerHelper()
    {
        return instance.getAppCleanerHelper();
    }

    public static FileHelper getFileHelper()
    {
        return instance.getFileHelper();
    }

    public static ConnectivityHelper getConnectivityHelper()
    {
        return instance.getConnectivityHelper();
    }
    public static ApiManagement getApiManagement()
    {
        return instance.getApiManagement();
    }

    public static LanguageHelper getLanguageHelper()
    {
        return instance.getLanguageHelper();
    }

    public static Interceptor getAuthHelper()
    {
        return instance.getAuthHelper();
    }

}
