package com.r2apps.triangleboard.ui.home;

import com.r2apps.triangleboard.ui.core.MVP;
import com.r2apps.triangleboard.ui.core.ResponseHandlerView;

/**
 * Created by user on 8/19/2016.
 */

public interface Main {
    interface View extends MVP.View,ResponseHandlerView {

        void navigateToHome();
    }

    interface Model extends MVP.Model{
        Main.Model prepare();
    }

    interface Presenter extends MVP.Presenter<Main.View>{
    }
}
