package com.jasch.devdroid.rxjavaexample.ui.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;


import com.jasch.devdroid.rxjavaexample.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    public static String TAG = SearchActivity.class.getSimpleName();
    private SearchView searchView;
    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = (SearchView)findViewById(R.id.searchView);
        textViewResult = (TextView)findViewById(R.id.textViewResult);

        setUpSearchObservable();
        
    }

    private void setUpSearchObservable() {
        RxSearchObservable.fromView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        if(s.isEmpty()){
                            textViewResult.setText("");
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .distinctUntilChanged()
                .switchMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return dataFromNetwork(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        textViewResult.setText(s);
                    }
                });
    }

    /*
    Simulation of network data
     */
    private Observable<String> dataFromNetwork(final String query){
        return Observable.just(true)
                .delay(2,TimeUnit.SECONDS)
                .map(new Function<Boolean, String>() {
                    @Override
                    public String apply(Boolean aBoolean) throws Exception {
                        return query;
                    }
                });
    }
}
