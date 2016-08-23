package com.vierbom.gooddoc;

import android.app.Application;

import xiaofei.library.hermeseventbus.HermesEventBus;

/**
 * Created by vierbom on 16/7/29.
 */
public class DocApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        HermesEventBus.getDefault().connectApp(this, "xiaofei.library.hermeseventbustest");
    }
}
