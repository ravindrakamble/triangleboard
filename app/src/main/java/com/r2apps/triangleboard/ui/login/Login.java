package com.r2apps.triangleboard.ui.login;

import com.r2apps.triangleboard.ui.core.MVP;
import com.r2apps.triangleboard.ui.core.ResponseHandlerView;

/**
 * Created by user on 8/11/2016.
 */

public interface Login {
    interface View extends MVP.View,ResponseHandlerView {
        void setUsernameError(int errorString);

        void setPasswordError(int  errorString);

        void navigateToHome();
    }

    interface Model extends MVP.Model{
        Model prepare();
    }

    interface Presenter extends MVP.Presenter<Login.View>{
        void validateCredentials(String username, String password);
    }
}
