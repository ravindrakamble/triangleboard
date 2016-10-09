package com.r2apps.triangleboard.ui.login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.r2apps.triangleboard.R;
import com.r2apps.triangleboard.ui.core.BaseActivity;
import com.r2apps.triangleboard.ui.home.MainActivity;
import com.r2apps.triangleboard.util.AppPermissions;

import timber.log.Timber;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements Login.View, OnClickListener {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializePresenter();
        initializeViews();
        checkRequiredPermissions();
    }

    private void initializePresenter(){
        presenter = new LoginPresenter();
        presenter.takeView(this);
    }

    private void initializeViews(){
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                return id == R.id.login || id == EditorInfo.IME_NULL;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(this);
    }

    private void checkRequiredPermissions(){
        if (!AppPermissions.checkPermissionsForStorage(this)) {
            AppPermissions.askForRationalStorage(this);
        }
    }
    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        showLoading();
    }

    @Override
    public void hideProgress() {
        hideLoading();
    }

    @Override
    public void setUsernameError(int errorString) {
        mEmailView.setError(getString(errorString));
        setFocus(mEmailView);
    }

    @Override
    public void setPasswordError(int errorString) {
        mPasswordView.setError(getString(errorString));
        setFocus(mPasswordView);
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        presenter.clear();
        this.finish();
    }

    @Override
    public void onClick(View v) {
        hideSoftInput();
        presenter.validateCredentials(mEmailView.getText().toString(), mPasswordView.getText().toString());
    }

    @Override
    public void onResponse(byte requestCode, Object response) {
        hideLoading();
        Toast.makeText(this, "Response Received", Toast.LENGTH_SHORT).show();
        navigateToHome();
    }

    @Override
    public void onFailure(byte requestCode, byte responseCode, String message) {
        hideProgress();
        Toast.makeText(this, "Error Received", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case AppPermissions.PERMISSIONS_REQUEST_READ_WRITE_STORAGE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Timber.i("Permission granted");
                }
                break;
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isInternetAvailable() {
        return isConnectedToInternet();
    }

    @Override
    public void showNoInternetDialog() {

    }

    @Override
    public void hideSoftKeyboard() {
        hideSoftKeyboard();
    }
}

