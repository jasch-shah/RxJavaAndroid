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

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by jashshah on 26/06/18.
 */

public class BufferExampleActivity extends AppCompatActivity {
    private static final String TAG = BufferExampleActivity.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeWork();
            }
        });
    }

    private void doSomeWork() {

        Observable<List<String>> buffered = getObservable().buffer(3, 1);

        // 3 means,  it takes max of three from its start index and create list
        // 1 means, it jumps one step every time
        // so the it gives the following list
        // 1 - one, two, three
        // 2 - two, three, four
        // 3 - three, four, five
        // 4 - four, five
        // 5 - five

        buffered.subscribe(getObserver());
    }

    private Observer<List<String>> getObserver() {
        return new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"onSubscribe:"+d.isDisposed());
            }

            @Override
            public void onNext(List<String> strings) {
                textView.append(" onNext Size : "+strings.size());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG,"onNext :  Size : "+strings.size());
                for(String value : strings){
                    textView.append(" value : "+value);
                    textView.append(AppConstant.LINE_SEPARATOR);
                    Log.d(TAG," : value :"+value);
                }

            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError : "+e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG," onError : "+e.getMessage());

            }


            @Override
            public void onComplete() {
                textView.append(" onComplete ");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG," onComplete");
            }
        };
    }

    private Observable<String> getObservable() {

        return Observable.just("one","two","three","four","five");
    }
}
