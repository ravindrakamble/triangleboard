package com.r2apps.triangleboard.ui.core;

/**
 * Created by user on 7/12/2016.
 */
public interface ResponseHandlerView {
    void onResponse(byte requestCode, Object response);
    void onFailure(byte requestCode, byte responseCode, String message);
}
