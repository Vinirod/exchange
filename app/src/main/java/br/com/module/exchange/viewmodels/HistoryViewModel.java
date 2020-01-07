package br.com.module.exchange.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.module.exchange.local.dao.HistoryDAO;
import br.com.module.exchange.model.History;

public class HistoryViewModel extends AndroidViewModel {

    private Context mContext;

    private MutableLiveData<List<History>> listHistoryMLD;

    private HistoryDAO mHistoryDAO;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        mContext = application;
    }

    public LiveData<List<History>> getHistory() {
        loadHistory();
        return listHistoryMLD;
    }

    public void loadHistory(){
        mHistoryDAO = new HistoryDAO(mContext);
        listHistoryMLD = new MutableLiveData<List<History>>();
        listHistoryMLD.setValue(mHistoryDAO.loadHistory());
    }
}
