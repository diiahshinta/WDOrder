package com.example.warehousedeliveryorder;


public class Constant {
    public static final String URL = "https://wms.kosme.co.id/";
    //public static final String URL = "https://wmsstag.kosme.co.id:5443/";
    private Constant() {}


    public static APIService getAPIService() {

        return RetrofitClient.getClient(URL).create(APIService.class);
    }

}
