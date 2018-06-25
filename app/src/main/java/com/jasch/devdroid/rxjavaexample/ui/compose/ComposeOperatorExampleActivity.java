package com.jasch.devdroid.rxjavaexample.ui.compose;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jasch.devdroid.rxjavaexample.R;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by jashshah on 24/06/18.
 */

public class ComposeOperatorExampleActivity extends AppCompatActivity {

private RxScheduler schedulers = new RxScheduler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_operator_example);

        Observable.just(1, 2, 3, 4, 5)
                .compose(schedulers.<Integer>applyObservableAsync())
                .subscribe(/**/);
        Flowable.just(1, 2, 3, 4, 5)
                .compose(schedulers.<Integer>applyFlowableAsync())
                .subscribe(/**/);
    }
}
