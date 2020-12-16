package com.app.knowyourism.Api;

import com.app.knowyourism.Model.Result;
import com.app.knowyourism.Model.UserImageData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class FacebookApi {
    private static final String BASE_URL = "https://graph.facebook.com/";

    public static PostService postService = null;
    public static PostService getService()
    {
        if(postService==null)
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory( GsonConverterFactory.create())
                    .build();
            postService=retrofit.create(PostService.class);
        }
        return postService;

    }

    public interface PostService {
        @GET("v7.0/{ID}/picture?redirect=0&height=200&width=200&type=normal")
        Call<UserImageData> getImageLink(
                @Path("ID") String id
        );
    }
}
