package com.bookapp.bookapp.local.models;
import io.realm.RealmObject;

public class Users extends RealmObject {

    private String userName = "";
    private String password = "";
    private boolean isFavorite = false;
    private boolean isForAfterReading =  false;
    private String bookKey = "";

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private boolean isActive = false;

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isForAfterReading() {
        return isForAfterReading;
    }

    public void setForAfterReading(boolean forAfterReading) {
        isForAfterReading = forAfterReading;
    }

    public String getBookKey() {
        return bookKey;
    }

    public void setBookKey(String bookKey) {
        this.bookKey = bookKey;
    }
}
