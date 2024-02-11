package org.itechnology.ITechnologyapp.ui.categories;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class CategoriesViewModel extends ViewModel {
    public MutableLiveData<Object> mutableLiveData = new MutableLiveData<>();
    CategoriesRepo repo = new CategoriesRepo();

    public void selectCategories() {
        repo.selectCategories().observeForever(new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                mutableLiveData.setValue(o);
            }
        });
    }
}
