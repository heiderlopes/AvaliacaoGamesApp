package com.example.heiderlopes.sensogames.api;


import com.example.heiderlopes.sensogames.model.Game;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GameAPI {

    @GET("game/ranking")
    public Call<List<Game>> getRanking();

    @POST("game/votar")
    public Call<Void> votar(@Body Game game);

}
