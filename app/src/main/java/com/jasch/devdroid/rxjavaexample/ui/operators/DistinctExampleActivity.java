package com.jasch.devdroid.rxjavaexample.ui.operators;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jasch.devdroid.rxjavaexample.R;
import com.jasch.devdroid.rxjavaexample.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;

/**
 * Created by jashshah on 26/06/18.
 */

public class DistinctExampleActivity extends AppCompatActivity {
    private static final String TAG = DistinctExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomeWork();
            }
        });
    }

    /*
     * distinct() suppresses duplicate items emitted by the source Observable.
     */
    private void doSomeWork() {

        getObservable()
                .distinct()
                .subscribe(getObserver());
    }

    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                textView.append(" onNext : value : " + integer);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext value : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG," onComplete");
            }
        };
    }

    private Observable<Integer> getObservable() {
        return Observable.just(1, 2, 1, 1, 2, 3, 4, 6, 4);
    }

}
