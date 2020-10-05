package com.canhdinh;

import com.canhdinh.api.ApiManagement;
import com.canhdinh.helper.AppCleanerHelper;
import com.canhdinh.helper.AuthHelper;
import com.canhdinh.helper.ConnectivityHelper;
import com.canhdinh.helper.FileHelper;
import com.canhdinh.helper.ImageHelper;
import com.canhdinh.helper.InstallationHelper;
import com.canhdinh.helper.LanguageHelper;
import com.canhdinh.mylib.SharePrefs;

public interface ObjectProviderInterface {
    SharePrefs getPreferences();

//    DatabaseHelper getDatabaseHelper();

    ImageHelper getImageHelper();

    ConnectivityHelper getConnectivityHelper();

    InstallationHelper getInstallationHelper();

    AppCleanerHelper getAppCleanerHelper();

    FileHelper getFileHelper();

    ApiManagement getApiManagement();

    LanguageHelper getLanguageHelper();

    AuthHelper getAuthHelper();

}
