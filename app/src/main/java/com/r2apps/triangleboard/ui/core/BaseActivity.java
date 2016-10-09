package com.r2apps.triangleboard.ui.core;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.r2apps.triangleboard.R;
import com.r2apps.triangleboard.ui.home.MainActivity;

import timber.log.Timber;

/**
 * Created by user on 7/12/2016.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     * Class tag. Used for debug.
     */
    private String TAG;
    private ProgressDialog progressDialog;

    public void setTAG(String tag) {
        this.TAG = tag;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTAG(this.getClass().getCanonicalName());
    }

    public void setFocus(View view) {
        view.requestFocus();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            final Intent homeIntent = new Intent(this, MainActivity.class);
            startActivity(homeIntent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Show progress dialog.
     */
    public final void showLoading() {
        Timber.i(TAG, "showLoading");
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();
    }

    /**
     * Show progress dialog.
     */
    public final void updateLoading(int message) {
        if (progressDialog != null) {
            progressDialog.setMessage(getString(message));
        }
    }

    /**
     * Hide progress dialog.
     */
    public final void hideLoading() {
        Timber.i(TAG, "hideLoading");
        if (null != progressDialog) {
            progressDialog.dismiss();
            progressDialog.cancel();
        }
        progressDialog = null;
    }

    /**
     * Checks for the internet connectivity
     * @return true if connected otherwise false.
     */
    public boolean isConnectedToInternet(){
        if (this.checkCallingOrSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    /**
     * Hide soft keyboard from the screen
     */
    public void hideSoftInput() {
        InputMethodManager manager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            manager.hideSoftInputFromWindow(this.getWindow().getCurrentFocus().getWindowToken(), 0);
            manager.hideSoftInputFromWindow(this.getWindow().getCurrentFocus().getApplicationWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
