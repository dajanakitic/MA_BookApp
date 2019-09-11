package com.bookapp.bookapp.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.bookapp.bookapp.R;
import com.bookapp.bookapp.adapters.BooksAdapter;
import com.bookapp.bookapp.adapters.BooksRVAdapter;
import com.bookapp.bookapp.listeners.FavoritePressListener;
import com.bookapp.bookapp.remote.models.Author;
import com.bookapp.bookapp.views.fragments.AllBooksFragment;
import com.bookapp.bookapp.views.fragments.FavoriteBooksFragment;
import com.google.android.material.tabs.TabLayout;

public class BooksActivity extends AppCompatActivity implements FavoritePressListener {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private BooksAdapter booksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        initWidgets();

    }

    private void initWidgets() {
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.booksVP);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        booksAdapter = new BooksAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(booksAdapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setPageTransformer(true, new AccordionTransformer());
    }

    @Override
    public void onFavoritePress(Author author) {
        FavoriteBooksFragment favoriteBooksFragment = (FavoriteBooksFragment) getSupportFragmentManager().getFragments().get(1);
        AllBooksFragment allBooksFragment= (AllBooksFragment) getSupportFragmentManager().getFragments().get(0);
        favoriteBooksFragment.onFavoritePress(author);
        allBooksFragment.onFavoritePress(author);
    }

    @Override
    public void onUnfavoritePress(Author author) {
        FavoriteBooksFragment favoriteBooksFragment = (FavoriteBooksFragment) getSupportFragmentManager().getFragments().get(1);
        AllBooksFragment allBooksFragment= (AllBooksFragment) getSupportFragmentManager().getFragments().get(0);
        favoriteBooksFragment.onUnfavoritePress(author);
        allBooksFragment.onUnfavoritePress(author);
    }
}
