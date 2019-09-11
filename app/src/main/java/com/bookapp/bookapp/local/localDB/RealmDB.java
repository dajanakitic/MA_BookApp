package com.bookapp.bookapp.local.localDB;

import android.app.Application;
import android.content.Context;

import com.bookapp.bookapp.local.models.Books;
import com.bookapp.bookapp.local.models.Users;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmDB {

    public static void insertUserIntoDB(Context context, final String username, final String password){
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                    Users users = realm.createObject(Users.class);
                    users.setUsername(username);
                    users.setPassword(password);
                    users.setActive(true);
                }
            });
        realm.close();
    }

    public static Users getUserFromDB(Context context, String userName, String password){
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Users> user = realm.where(Users.class)
                .equalTo("userName", userName)
                .findAll();
        return user.size() <= 0 ? new Users() : user.get(0);
    }

    public static void deleteBookFromDB(Context context, final String userName, final String title, final String author){
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Books> books = realm.where(Books.class)
                        .equalTo("userName", userName)
                        .findAll()
                        .where()
                        .equalTo("author", author)
                        .findAll()
                        .where()
                        .equalTo("title", title)
                        .findAll();
                books.deleteAllFromRealm();
            }
        });
        realm.close();
    }

    public static boolean getUserNameExistanceFromDB(Context context, String newUserName){
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Users> user = realm.where(Users.class)
                .equalTo("userName", newUserName).findAll();
        return (user.size() == 0);
    }

    public static List<Books> getBooksFromDB(Context context, String userName){
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Books> books = realm.where(Books.class)
                .equalTo("userName", userName).findAll();
        return books;
    }

    public static void insertBooksToDB(Context context, final String userName, boolean isFavorite, final String author, final String title, final String cover, final String year, final String publisher){
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Books books = realm.createObject(Books.class);
                books.setTitle(title);
                books.setYear(year);
                books.setFavorite(true);
                books.setPublisher(publisher);
                books.setAuthor(author);
                books.setCover(cover);
                books.setUserName(userName);
            }
        });
        realm.close();
    }

    public static void initRealm(Application application) {
        Realm.init(application);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }


}
