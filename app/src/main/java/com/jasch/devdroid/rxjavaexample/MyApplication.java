package com.jasch.devdroid.rxjavaexample;

import android.app.Application;

import com.jasch.devdroid.rxjavaexample.model.Events;
import com.jasch.devdroid.rxjavaexample.ui.rxbus.RxBus;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by jashshah on 24/06/18.
 */

public class MyApplication extends Application {

    public static final String TAG = "MyApplication";
    private RxBus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        bus = new RxBus();
    }
    public void sendAutoEvent(){
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        bus.send(new Events.AutoEvent());
                    }
                });
    }
}
