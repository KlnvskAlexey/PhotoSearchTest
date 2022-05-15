package com.example.retrofitagain2.di.module;

import com.example.retrofitagain2.searchHistory.SearchHistoryPresenter;
import com.example.retrofitagain2.searchHistory.SearchHistoryPresenterImpl;
import com.example.retrofitagain2.searchHistory.SearchHistoryView;
import com.example.retrofitagain2.services.SearchHistoryService;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchHistoryViewModule {

    private final SearchHistoryView view;

    public SearchHistoryViewModule(SearchHistoryView view) {
        this.view = view;
    }

    @Provides
    SearchHistoryView provideSearchHistoryView() {
        return view;
    }

    @Provides
    SearchHistoryPresenter provideSearchHistoryPresenter(SearchHistoryService searchHistoryService, SearchHistoryView view) {
        return new SearchHistoryPresenterImpl(searchHistoryService, view);
    }
}