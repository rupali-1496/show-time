package com.example.showtime.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/3/movie/3/lists?api_key=aca35d034c9330fea578760de19df054&language=en-US/")
    Call<ContentNodes> doGetListResources();
}
