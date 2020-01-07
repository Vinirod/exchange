package br.com.module.exchange.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import br.com.module.exchange.R;
import br.com.module.exchange.model.History;
import br.com.module.exchange.viewmodels.HistoryViewModel;
import br.com.module.exchange.views.rvadapters.RVAHistory;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView mRvHistory;

    private RVAHistory mRVAHistory;

    private HistoryViewModel mHistoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mHistoryViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);

        bindAll();
        startAll();
    }

    private void bindAll() {
        mRvHistory = (RecyclerView) findViewById(R.id.idRvHistory);
    }

    private void startAll() {
        settingAndFillRecyclerView();
    }

    private void settingAndFillRecyclerView() {
        mRvHistory.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
        mHistoryViewModel.getHistory().observe(this, new Observer<List<History>>() {
            @Override
            public void onChanged(List<History> histories) {
                mRVAHistory = new RVAHistory(histories, HistoryActivity.this);
                mRvHistory.setAdapter(mRVAHistory);
            }
        });
    }
}
