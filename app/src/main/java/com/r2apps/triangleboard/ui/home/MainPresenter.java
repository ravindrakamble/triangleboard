package com.r2apps.triangleboard.ui.home;

import com.r2apps.triangleboard.model.response.NewsResponse;
import com.r2apps.triangleboard.rest.APIManager;
import com.r2apps.triangleboard.rest.ApiService;
import com.r2apps.triangleboard.util.AppConstants;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user on 7/13/2016.
 */
public class MainPresenter implements Main.Presenter{
    private Main.View mainActivityView;
    private ApiService apiService;

    public MainPresenter(){
        apiService = APIManager.getInstance().getAPIService();
    }

    public void fetchNews(){
        Observable<List<NewsResponse>> news = apiService.fetchNews();
        news.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<NewsResponse>>() {
                    @Override
                    public void onCompleted() {
                        /**
                         * Currently there is nothing to handle on complete.
                         */
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainActivityView.onFailure(AppConstants.Request.FETCH_NEWS, (byte) 0, e.getMessage());
                    }

                    @Override
                    public void onNext(List<NewsResponse> newsResponse) {
                        mainActivityView.onResponse(AppConstants.Request.FETCH_NEWS, newsResponse);
                    }
                });
    }

    @Override
    public void takeView(Main.View view) {
        this.mainActivityView = view;
    }

    @Override
    public boolean hasView() {
        if(mainActivityView != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Main.View view() {
        return mainActivityView;
    }

    @Override
    public void onDestroy() {
        mainActivityView = null;
        apiService = null;
    }
}
