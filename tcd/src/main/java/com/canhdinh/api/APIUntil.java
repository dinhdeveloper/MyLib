package com.canhdinh.api;

public abstract class APIUntil {
    public static APIService getBaseServer(String baseURL) {
        return APIClient.getApiClient(baseURL).create(APIService.class);
    }
}
