package com.bookapp.bookapp.listeners;

import com.bookapp.bookapp.remote.models.Author;

public interface FavoritePressListener {

    void onFavoritePress(Author author);
    void onUnfavoritePress(Author author);
}
