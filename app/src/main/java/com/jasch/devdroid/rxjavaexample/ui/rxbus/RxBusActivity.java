package com.jasch.devdroid.rxjavaexample.ui.rxbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jasch.devdroid.rxjavaexample.MyApplication;
import com.jasch.devdroid.rxjavaexample.R;
import com.jasch.devdroid.rxjavaexample.model.Events;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxBusActivity extends AppCompatActivity {

    public static final String TAG = RxBusActivity.class.getSimpleName();
    TextView textView;
    Button button;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);
        textView = (TextView)findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);


        disposables.add(((MyApplication) getApplication())
                .bus()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (object instanceof Events.AutoEvent) {
                            textView.setText("Auto Event Received");
                        } else if (object instanceof Events.TapEvent) {
                            textView.setText("Tap Event Received");
                        }
                    }
                }));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ( (MyApplication) getApplication())
                        .bus()
                        .send(new Events.TapEvent());
            }
        });
    }
}
