package org.itechnology.ITechnologyapp.ui.categories;

import androidx.lifecycle.MutableLiveData;

import org.itechnology.ITechnologyapp.networks.API;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoriesRepo {
    private MutableLiveData<Object> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Object> selectCategories() {
        Observable observable = API.getInstance().getCategory()
                .subscribeOn(Schedulers.io());
        Observer<Object> observer = new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                mutableLiveData.postValue(o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mutableLiveData.postValue(e);

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
        return mutableLiveData;
    }
}
