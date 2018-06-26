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

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;


public class FilterExampleActivity extends AppCompatActivity {

    private static final String TAG = FilterExampleActivity.class.getSimpleName();
    private Button btn;
    private TextView textView;

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
    /*
         * simple example by using filter operator to emit only even value
         *
         */
    private void doSomeWork() {
        Observable.just(1,2,3,4,5)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 ==0;
                    }
                })
                .subscribe(getObserver());
    }

    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG," onSubscribe : "+d.isDisposed());
            }

            @Override
            public void onNext(Integer integer) {
                textView.append(" onNext : ");
                textView.append(AppConstant.LINE_SEPARATOR);
                textView.append(" value : "+integer);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG,"onNext ");
                Log.d(TAG,"  value :"+integer);

            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError : ");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG,"onError :  "+e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append(" onComplete : ");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG,"onComplete  ");
            }
        };
    }
}
