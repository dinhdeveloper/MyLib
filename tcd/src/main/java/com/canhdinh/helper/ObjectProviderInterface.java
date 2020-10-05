package com.canhdinh.helper;

import com.canhdinh.api.ApiManagement;

import okhttp3.Interceptor;

public interface ObjectProviderInterface {
    ImageHelper getImageHelper();

    ConnectivityHelper getConnectivityHelper();

    InstallationHelper getInstallationHelper();

    AppCleanerHelper getAppCleanerHelper();

    FileHelper getFileHelper();

    ApiManagement getApiManagement();

    LanguageHelper getLanguageHelper();

    Interceptor getAuthHelper();
}
