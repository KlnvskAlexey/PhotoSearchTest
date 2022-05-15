package com.example.retrofitagain2.searchHistory;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitagain2.Application;
import com.example.retrofitagain2.BaseActivity;
import com.example.retrofitagain2.R;
import com.example.retrofitagain2.di.components.DaggerSearchHistoryViewComponent;
import com.example.retrofitagain2.di.module.SearchHistoryViewModule;

import java.util.ArrayList;

import javax.inject.Inject;

public class SearchHistoryActivity extends BaseActivity implements SearchHistoryView {

    RecyclerView recyclerView;
    SearchHistoryRecyclerViewAdapter recyclerViewAdapter;
    @Inject
    SearchHistoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);
        init();
        presenter.handleActivityOnCreate();
    }

    @Override
    protected void setupActivityComponent() {
        DaggerSearchHistoryViewComponent.builder()
                .appComponent(Application.getAppComponent())
                .searchHistoryViewModule(new SearchHistoryViewModule(this))
                .build()
                .inject(this);
    }

    void init() {
        recyclerView = findViewById(R.id.recyclerViewSearchHistory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewAdapter = new SearchHistoryRecyclerViewAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void showHistoryItems(ArrayList<String> historyQueriesList) {
        recyclerViewAdapter.updatePhotosListHistory(historyQueriesList);
    }
}