package com.canhdinh.api;

public abstract class APIUntil {
    public static APIService1 getBaseServer(String baseURL) {
        return APIClient.getApiClient(baseURL).create(APIService1.class);
    }
}
