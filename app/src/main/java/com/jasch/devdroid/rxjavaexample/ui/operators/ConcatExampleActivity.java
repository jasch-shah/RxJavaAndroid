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

import org.w3c.dom.Text;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by jashshah on 26/06/18.
 */

public class ConcatExampleActivity extends AppCompatActivity {

    private static final String TAG = ConcatExampleActivity.class.getSimpleName();
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
     * Using concat operator to combine Observable : concat maintain
     * the order of Observable.
     * It will emit all the 7 values in order
     * here - first "A1", "A2", "A3", "A4" and then "B1", "B2", "B3"
     * first all from the first Observable and then
     * all from the second Observable all in order
     */

    private void doSomeWork() {
        final String[] aStrings = {"A1","A2","A3","A4"};
        final String[] bStrings = {"B1","B2","B3"};

        final Observable<String> aObservable = Observable.fromArray(aStrings);
        final Observable<String> bObservable = Observable.fromArray(bStrings);

        Observable.concat(aObservable, bObservable)
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
                textView.append(" onNext : value : " + s);
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG," onNext : value : "+s);

            }

            @Override
            public void onError(Throwable e) {
                textView.append(" onError :  " + e.getMessage());
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG," onError : "+e.getMessage());

            }

            @Override
            public void onComplete() {

                textView.append(" onComplete :  ");
                textView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG," onComplete : ");
            }
        };
    }
}
