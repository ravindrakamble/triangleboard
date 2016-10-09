package com.r2apps.triangleboard.ui.login;

import android.text.TextUtils;

import com.r2apps.triangleboard.R;
import com.r2apps.triangleboard.model.request.LoginRequest;
import com.r2apps.triangleboard.model.response.LoginResponse;
import com.r2apps.triangleboard.rest.APIManager;
import com.r2apps.triangleboard.rest.ApiService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.r2apps.triangleboard.util.AppConstants.Request;

/**
 * Created by user on 7/12/2016.
 */
public class LoginPresenter implements Login.Presenter{
    private ApiService service;
    private Login.View view;
    public LoginPresenter(){
        service = APIManager.getInstance().getAPIService();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            view.setUsernameError(R.string.error_field_required);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            view.setPasswordError(R.string.error_field_required);
            return;
        }

        if(view.isInternetAvailable()) {
            view.showProgress();
            executeLoginRequest(new LoginRequest(username, password));
        }else{
            view.showNoInternetDialog();
        }
    }

    private void executeLoginRequest(LoginRequest request) {
        Observable<LoginResponse> login = service.login(request);
        login.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {
                        /**
                         * Currently there is nothing to handle on complete.
                         */
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.onFailure(Request.LOGIN, (byte) 0, e.getMessage());
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        view.onResponse(Request.LOGIN, loginResponse);
                    }
                });
    }

    @Override
    public void takeView(Login.View view) {
        this.view = view;
    }

    @Override
    public boolean hasView() {
        if(view != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Login.View view() {
        return view;
    }

    public void onDestroy(){
        clear();
    }

    public void clear(){
        view = null;
        service = null;
    }
}
