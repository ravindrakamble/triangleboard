package com.r2apps.triangleboard.rest;

import com.r2apps.triangleboard.util.AppConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 7/12/2016.
 */
public class APIManager {
    private static APIManager manager;
    private ApiService service;

    private APIManager(){

    }

    public static synchronized APIManager getInstance(){
        if(manager == null){
            manager = new APIManager();
        }
        return manager;
    }

    public ApiService getAPIService(){
        if(service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.URL.BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            service = retrofit.create(ApiService.class);
        }
        return service;
    }
}
