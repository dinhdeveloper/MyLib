package com.canhdinh.mylib.api;

public class APIUntil {
    private static String baseURL = "https://hospital-booking.com/"; // https://mobishops.herokuapp.com/ http://vtnshop.herokuapp.com/

    public static APIService getServer() {
        return APIClient.getApiClient(baseURL).create(APIService.class);
    }
}
