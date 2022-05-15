package com.example.retrofitagain2.photoList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import android.graphics.Bitmap;

import com.example.retrofitagain2.PhotosServiceListener;
import com.example.retrofitagain2.services.PhotosService;
import com.example.retrofitagain2.services.SearchHistoryService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PhotoListPresenterImplTest {
    @Mock
    PhotosService photosService;
    @Mock
    SearchHistoryService searchHistoryService;
    @Mock
    PhotoListView photoListView;
    PhotoListPresenterImpl photoListPresenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.photoListPresenter = new PhotoListPresenterImpl(photosService, searchHistoryService, photoListView);
    }

    @AfterEach
    void afterEach() {
        reset(photosService);
        reset(searchHistoryService);
        reset(photoListView);
    }

    @Test
    void HandleSearchHistoryButtonClickTest() {
        photoListPresenter.handleHistoryButtonClick();
        verify(photoListView, times(1)).showSearchHistoryActivity();
    }

    @Test
    void HandleSearchViewQueryTest() {
        photoListPresenter.handleSearchViewQuery(anyString());
        verify(searchHistoryService, times(1)).addHistoryQuery(anyString());
        verify(photoListView, times(1)).showProgressBar();
        verify(photoListView, times(1)).showToast(anyString());
        verify(photosService, times(1)).fetchPhotosByQuery(anyString(), any(PhotosServiceListener.class));
    }

    @Test
    void HandleDownloadButtonClickTest() {
        photoListPresenter.handleDownloadButtonClick(anyString(), anyString());
        verify(photosService, times(1)).loadPhotosByQuery(anyString(), anyString());
    }

    @Test
    void HandleImageButtonClickTest() {
        photoListPresenter.handleImageButtonClick(mock(Bitmap.class));
        verify(photoListView, times(1)).hideKeyboard();
        verify(photoListView, times(1)).showPhotoDetailsActivity(any());
    }
}