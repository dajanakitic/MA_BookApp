package com.bookapp.bookapp.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.http.Query;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.bookapp.bookapp.R;
import com.bookapp.bookapp.local.models.Books;
import com.bookapp.bookapp.remote.models.Author;
import com.bookapp.bookapp.utils.Constants;
import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    private Intent intent;
    private ImageView imageView;
    private TextView titleTV, authorTV, publisherTV, yearTV;
    private Books books;
    private Author author;
    private String img = "https://styluspub.presswarehouse.com/publishers/default_cover.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initWidget();
        getIntents();
    }

    private void initWidget(){
        imageView = findViewById(R.id.detailsIV);
        titleTV = findViewById(R.id.titleTV);
        authorTV = findViewById(R.id.authorTV);
        publisherTV = findViewById(R.id.publisherTV);
        yearTV = findViewById(R.id.yearTV);
    }

    private void getIntents() {

        intent = getIntent();

        if (intent.hasExtra(Constants.BOOKS)) {
            books = intent.getParcelableExtra(Constants.BOOKS);
            uploadBookWidgets(books);
        } else if (intent.hasExtra(Constants.AUTHORS)) {
            author = intent.getParcelableExtra(Constants.AUTHORS);
            uploadAuthorWidget(author);
        }
    }

    private void uploadBookWidgets(Books books) {
        try {
        titleTV.setText(books.getTitle());
        authorTV.setText(books.getAuthor());
        publisherTV.setText(books.getPublisher());
        yearTV.setText(books.getYear());
        img = books.getCover();
        } catch (NullPointerException e){
        }
        Glide.with(this).load(img).into(imageView);
    }

    private void uploadAuthorWidget(Author author) {
        try{
        titleTV.setText(author.getTitle());
        authorTV.setText(author.getByStatement());
        publisherTV.setText(author.getPublishers().get(0));
        yearTV.setText(author.getPublishDate());

            img = author.getOcaid();
        } catch (NullPointerException e){
        }
        Glide.with(this).load(img).into(imageView);
    }


}
