package com.bookapp.bookapp.views.fragments;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bookapp.bookapp.application.ApplicationClass;
import com.bookapp.bookapp.listeners.FavoritePressListener;
import com.bookapp.bookapp.listeners.OnFetchedListener;
import com.bookapp.bookapp.R;
import com.bookapp.bookapp.adapters.AuthorsRVAdapter;
import com.bookapp.bookapp.local.models.Books;
import com.bookapp.bookapp.recievers.DataFetchedReciever;
import com.bookapp.bookapp.remote.models.Author;
import com.bookapp.bookapp.repository.DataRepository;
import com.bookapp.bookapp.utils.Constants;
import com.bookapp.bookapp.views.activities.DetailsActivity;

import java.util.List;


public class AllBooksFragment extends Fragment implements OnFetchedListener {

    private AuthorsRVAdapter mDataAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mProgressBar;
    private DataRepository repository;
    private View view;
    private boolean stationsFetched = false;
    private List<Author> authors;
    private static FavoritePressListener fav;
    private List<Books> books;


    public AllBooksFragment() {
    }

    public static AllBooksFragment newInstance(String title, FavoritePressListener favoritePressListener) {
        AllBooksFragment allBooksFragment = new AllBooksFragment();
        fav = favoritePressListener;
        return allBooksFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all_books, container, false);
        repository = DataRepository.getInstance(this.getContext(), ApplicationClass.getInstance());
        books = repository.getBooksFromDB(Constants.user.getUsername());
        mProgressBar = view.findViewById(R.id.allBooksPB);
        setmProgressBar();
    //    if (stationsFetched){
        initRecycleView(view);
        initListener();
        registerReceiver();

        return view;
    }

    private void setmProgressBar(){
        try {
            if ((mDataAdapter.getItemCount() > 0)) {
                mProgressBar.setVisibility(View.INVISIBLE);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                }, 4000);
            }
        } catch (NullPointerException e){}
    }

    private void initRecycleView(View view){
        mRecyclerView = view.findViewById(R.id.allBooksRV);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        authors = repository.getAuthors();
        mDataAdapter = new AuthorsRVAdapter(this.getContext(), repository.getAuthors(), fav, books);
        mRecyclerView.setAdapter(mDataAdapter);
    }

    private void initListener() {
        try {
            mDataAdapter.setOnItemClickListener(new AuthorsRVAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    Intent intent = new Intent(getContext(), DetailsActivity.class);
                    intent.putExtra(Constants.AUTHORS, authors.get(position));
                    startActivity(intent);
                }
            });
        } catch (NullPointerException e) {}
    }

    @Override
    public void onAuthorsFetched() {
        stationsFetched = true;
        if(view != null) {
            authors = repository.getAuthors();
            initRecycleView(view);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void registerReceiver(){
        IntentFilter intent = new IntentFilter();
        intent.addAction("DataFetched");

        DataFetchedReciever mReceiver = new DataFetchedReciever(this.getActivity(), this);
        try {
            this.getActivity().registerReceiver(mReceiver, intent);
        } catch (NullPointerException e){}
    }

    public void onFavoritePress(Author author) {
        List<Books> books = repository.getBooksFromDB(Constants.user.getUsername());
        mDataAdapter.setBooks(books);
    }

    public void onUnfavoritePress(Author author){
        List<Books> books = repository.getBooksFromDB(Constants.user.getUsername());
        mDataAdapter.setBooks(books);
        setmProgressBar();
    }
}
