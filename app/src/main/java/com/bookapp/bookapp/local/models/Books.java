package com.bookapp.bookapp.local.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class Books extends RealmObject implements Parcelable {

    private String userName;
    private boolean isFavorite;
    private String author;
    private String title;
    private String cover;
    private String year;
    private String publisher;

    protected Books(Parcel in) {
        userName = in.readString();
        isFavorite = in.readByte() != 0;
        author = in.readString();
        title = in.readString();
        cover = in.readString();
        year = in.readString();
        publisher = in.readString();
    }

    public Books(){}

    public static final Creator<Books> CREATOR = new Creator<Books>() {
        @Override
        public Books createFromParcel(Parcel in) {
            return new Books(in);
        }

        @Override
        public Books[] newArray(int size) {
            return new Books[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(cover);
        dest.writeString(year);
        dest.writeString(publisher);
    }
}
