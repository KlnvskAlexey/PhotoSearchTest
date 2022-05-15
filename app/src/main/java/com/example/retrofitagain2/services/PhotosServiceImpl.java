package com.example.retrofitagain2.services;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.retrofitagain2.ApiClientFlickr;
import com.example.retrofitagain2.ApiInterfaceFlickr;
import com.example.retrofitagain2.Photo;
import com.example.retrofitagain2.PhotosResponse;
import com.example.retrofitagain2.PhotosServiceListener;
import com.example.retrofitagain2.R;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class PhotosServiceImpl implements PhotosService {

    private static final String TAG = "PhotosServiceImpl";
    private final Context context;
    ApiInterfaceFlickr apiInterfaceFlickr;

    @Inject
    public PhotosServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public void fetchPhotosByQuery(String query, PhotosServiceListener photosServiceListener) {
        String apiKey = context.getResources().getString(R.string.my_flickr_api_key);
        apiInterfaceFlickr = ApiClientFlickr.getClient().create(ApiInterfaceFlickr.class);
        Call<PhotosResponse> callGetAll = apiInterfaceFlickr.getAllBySearch(query, apiKey);
        callGetAll.enqueue(new Callback<PhotosResponse>() {
            @Override
            public void onResponse(@NonNull Call<PhotosResponse> call, @NonNull Response<PhotosResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "onResponse: " + response.body());
                    List<Photo> photoListFromResponse = response.body().getPhotos().getPhoto();
                    photosServiceListener.onPhotoServiceSuccess(photoListFromResponse);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhotosResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                photosServiceListener.onFailure(t);
            }
        });
    }

    @Override
    public void loadPhotosByQuery(String urlString, String photoTitle) {
        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(urlString);
        DownloadManager.Request request = new DownloadManager.Request((Uri) downloadUri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle("Photo")
                .setMimeType("image/jpeg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator + "FlickrPhotos" + File.separator + photoTitle + System.currentTimeMillis() + ".jpg");
        dm.enqueue(request);
    }
}