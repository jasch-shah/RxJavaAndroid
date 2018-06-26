package com.jasch.devdroid.rxjavaexample.ui.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jasch.devdroid.rxjavaexample.R;
import com.jasch.devdroid.rxjavaexample.model.Car;
import com.jasch.devdroid.rxjavaexample.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by jashshah on 26/06/18.
 */

public class DeferExampleActivity extends AppCompatActivity {
    private static final String TAG = DeferExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = (Button)findViewById(R.id.btn);
        textView = (TextView)findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeWork();
            }
        });
    }

    /*
     * Defer used for Deferring Observable code until subscription in RxJava
     */

    private void doSomeWork() {
        Car car = new Car();
        Observable<String> brandDeferObservable = car.brandDeferObservable();
        car.setBrand("BMW");        //// Even if we are setting the brand after creating Observable
        // we will get the brand as BMW.
        // If we had not used defer, we would have got null as the brand.

        brandDeferObservable
                .subscribe(getObserver());
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG," onSubscribe : "+d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                textView.append(" onNext : value : "+s);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG,"onNext : value : "+s);

            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError  : "+e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG,"onError : "+e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" onComplete : ");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG,"onComplete :  ");
            }
        };
    }
}
