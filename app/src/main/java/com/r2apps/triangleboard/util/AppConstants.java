package com.r2apps.triangleboard.util;

/**
 * Created by user on 7/12/2016.
 */
public interface AppConstants {
    interface Request{
        byte LOGIN = 1;
        byte FETCH_NEWS = 2;
    }

    interface URL{
        String BASE = "http://triangleboard.herokuapp.com/";
        String LOGIN = "api/v1/users/login";
        String COLLEGE_LIST = "api/v1/colleges";
    }
}
