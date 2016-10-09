package com.r2apps.triangleboard.rest;

import com.r2apps.triangleboard.model.request.LoginRequest;
import com.r2apps.triangleboard.model.response.LoginResponse;
import com.r2apps.triangleboard.model.response.NewsResponse;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;
import static com.r2apps.triangleboard.util.AppConstants.URL;

/**
 * Created by user on 7/12/2016.
 */
public interface ApiService {
    @POST(URL.LOGIN)
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET(URL.COLLEGE_LIST)
    Observable<List<NewsResponse>> fetchNews();
}
