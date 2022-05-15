package com.example.retrofitagain2.di.module;

import com.example.retrofitagain2.photoList.PhotoListActivity;
import com.example.retrofitagain2.photoList.PhotoListPresenter;
import com.example.retrofitagain2.photoList.PhotoListPresenterImpl;
import com.example.retrofitagain2.photoList.PhotoListView;
import com.example.retrofitagain2.services.PhotosService;
import com.example.retrofitagain2.services.SearchHistoryService;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoListViewModule {

    private final PhotoListView view;

    public PhotoListViewModule(PhotoListActivity view) {
        this.view = view;
    }

    @Provides
    PhotoListView providePhotoListView() {
        return view;
    }

    @Provides
    PhotoListPresenter providePhotoListPresenter(PhotosService photoService, SearchHistoryService searchHistoryService, PhotoListView view) {
        return new PhotoListPresenterImpl(photoService, searchHistoryService, view);
    }
}