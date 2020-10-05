package com.canhdinh.helper;

import com.canhdinh.api.ApiManagement;

public interface ObjectProviderInterface {
    ImageHelper getImageHelper();

    ConnectivityHelper getConnectivityHelper();

    InstallationHelper getInstallationHelper();

    AppCleanerHelper getAppCleanerHelper();

    FileHelper getFileHelper();

    ApiManagement getApiManagement();

    LanguageHelper getLanguageHelper();

    AuthHelper getAuthHelper();
}
