package com.bookapp.bookapp.repository;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.bookapp.bookapp.local.localDB.RealmDB;
import com.bookapp.bookapp.local.models.Books;
import com.bookapp.bookapp.local.models.Users;
import com.bookapp.bookapp.remote.api.Api;
import com.bookapp.bookapp.remote.models.Author;
import com.bookapp.bookapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DataRepository {

    private Context context;
    private List<Author> authors;
    private static DataRepository instance;
    
    public static DataRepository getInstance(Context context, Application application){
        if (instance == null){
            instance = new DataRepository(context, application);
        }
        return instance;
    }

    private DataRepository(Context context, Application applicaton) {
        this.context = context;
//        RealmDB.initRealm(applicaton);
        getUsersRemote();
        authors = new ArrayList<>();
    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(Constants.URL_AUTHORS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    private static Api getApi() {
        return getRetrofitInstance().create(Api.class);
    }

    private void getUsersRemote() {
        DataRepository.getApi().getKing("/type/edition", "/authors/OL2162284A", 7, "").enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call, final Response<List<Author>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    setAuthors(response.body());
                    Log.d(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable t) {
            }
        });

        DataRepository.getApi().getHarrison("/type/edition", "/authors/OL27278A", 2, "").enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call, final Response<List<Author>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    setAuthors(response.body());
                    Log.d(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable t) {
            }
        });

        DataRepository.getApi().getKnight("/type/edition", "/authors/OL2628836A", 6, "" ).enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call, final Response<List<Author>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    setAuthors(response.body());
                    Log.d(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable t) {
            }
        });

        DataRepository.getApi().getHaddix("/type/edition", "/authors/OL39006A", 3, "").enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call, final Response<List<Author>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    setAuthors(response.body());
                    Log.d(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable t) {
            }
        });

        DataRepository.getApi().getWatson("/type/edition", "/authors/OL444006A", 2, "").enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call, final Response<List<Author>> response) {

                if (response.isSuccessful() && response.body() != null) {
                    setAuthors(response.body());
                    Log.d(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void insertUserIntoDB(String userName, String password){
        RealmDB.insertUserIntoDB(context, userName, password);
    }


    public Users getUsersFromDB(String userName, String password) {
        try {
            return RealmDB.getUserFromDB(context, userName, password);
        } catch (NullPointerException e){
            Toast.makeText(context, "Manji problemi, probajte login ponovno", Toast.LENGTH_SHORT).show();
        }
        return new Users();
    }

    public boolean getUsersFromDB(String newUserName) {
        return RealmDB.getUserNameExistanceFromDB(context, newUserName);
    }

    public void deleteBookFromDB(String newUserName, String title, String author){
        RealmDB.deleteBookFromDB(context, newUserName, title, author);
    }
    
    public void setAuthors(List<Author> authors){
        this.authors.addAll(authors);
        Log.d(TAG, "setAuthors: " + authors.get(0).getTitle());

        if(this.authors.size() > 12) {
            Intent intent = new Intent();
            intent.setAction("DataFetched");
            context.sendBroadcast(intent);
        }
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Books> getBooksFromDB(String username) {
        return RealmDB.getBooksFromDB(context, username);
    }

    public void insertBooksToDB(final String userName, boolean isFavorite, final String author, final String title, final String cover, final String year, final String publisher){
        RealmDB.insertBooksToDB(context, userName, true, author, title, cover, year, publisher);
    }
}
