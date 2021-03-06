package com.example.riji.Month_related;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MonthViewModel extends AndroidViewModel {
    private MonthRepository mRepository;
    private LiveData<List<Month>> mAllMonths;

    public MonthViewModel(Application application) {
        super(application);
        mRepository = new MonthRepository(application);
        mAllMonths = mRepository.getAllMonths();
    }

    LiveData<List<Month>> getAllMonths() {
        return mAllMonths;
    }

    public void insert(Month month) {
        mRepository.insertMonth(month);
    }

}
