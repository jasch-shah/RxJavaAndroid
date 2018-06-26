package com.jasch.devdroid.rxjavaexample.ui.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jasch.devdroid.rxjavaexample.R;
import com.jasch.devdroid.rxjavaexample.utils.AppConstant;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jashshah on 26/06/18.
 */

public class DelayExampleActivity extends AppCompatActivity {

    private static final String TAG = DelayExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeWork();
            }
        });
    }

    private void doSomeWork() {
        getObservable().delay(2, TimeUnit.SECONDS)
                //Run on Background thread
                .subscribeOn(Schedulers.io())
                // Be notified on main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());

    }

    private Observer<String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG," onSubscribe "+d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                textView.append(" onNext : value : "+s);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, "onNext : value : "+s);
            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError : " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" onComplete");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }
        };
    }

    private Observable<String> getObservable() {
        return Observable.just("Jash");
    }

}
