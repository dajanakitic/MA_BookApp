package com.bookapp.bookapp.remote.api;

import com.bookapp.bookapp.remote.models.Author;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

        @GET("query.json")
        Call<List<Author>> getHaddix(@Query("type") String type,
                                        @Query("authors") String authors,
                                        @Query("limit") int count ,
                                        @Query("*") String star);

        @GET("query.json")
        Call<List<Author>> getKnight(@Query("type") String type,
                                        @Query("authors") String authors,
                                        @Query("limit") int count ,
                                        @Query("*") String star);

        @GET("query.json")
        Call<List<Author>> getHarrison(@Query("type") String type,
                                        @Query("authors") String authors,
                                        @Query("limit") int count ,
                                        @Query("*") String star);

        @GET("query.json")
        Call<List<Author>> getKing(@Query("type") String type,
                                        @Query("authors") String authors,
                                        @Query("limit") int count ,
                                        @Query("*") String star);

        @GET("query.json")
        Call<List<Author>> getWatson(@Query("type") String type,
                                        @Query("authors") String authors,
                                        @Query("limit") int count ,
                                        @Query("*") String star);
}
