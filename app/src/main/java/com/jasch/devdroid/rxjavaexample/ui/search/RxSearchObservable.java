package com.jasch.devdroid.rxjavaexample.ui.search;

import android.widget.SearchView;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by jashshah on 25/06/18.
 */

class RxSearchObservable {

    public RxSearchObservable() {

    }

    public static Observable<String> fromView(SearchView searchView){
        final PublishSubject<String> subject = PublishSubject.create();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                subject.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                subject.onNext(newText);
                return true;
            }
        });
        return subject;
    }
}
