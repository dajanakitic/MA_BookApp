package com.bookapp.bookapp.adapters;

import com.bookapp.bookapp.listeners.FavoritePressListener;
import com.bookapp.bookapp.views.fragments.AllBooksFragment;
import com.bookapp.bookapp.views.fragments.FavoriteBooksFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BooksAdapter extends FragmentPagerAdapter {

    private FavoritePressListener favoritePressListener;

    public BooksAdapter(FragmentManager fm, FavoritePressListener favoritePressListener) {
        super(fm);
        this.favoritePressListener = favoritePressListener;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return AllBooksFragment.newInstance("All Books", favoritePressListener);
            case 1: return FavoriteBooksFragment.newInstance("Favorite Books");
        }
        return AllBooksFragment.newInstance("", favoritePressListener);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "All Books";
            case 1: return "Favorite Books";
        }
        return "All Books";
    }
}
