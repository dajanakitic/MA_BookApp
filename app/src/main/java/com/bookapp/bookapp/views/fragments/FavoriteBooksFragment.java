package com.bookapp.bookapp.views.fragments;

import android.content.Intent;
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
import com.bookapp.bookapp.R;
import com.bookapp.bookapp.adapters.BooksRVAdapter;
import com.bookapp.bookapp.application.ApplicationClass;
import com.bookapp.bookapp.local.models.Books;
import com.bookapp.bookapp.remote.models.Author;
import com.bookapp.bookapp.repository.DataRepository;
import com.bookapp.bookapp.utils.Constants;
import com.bookapp.bookapp.views.activities.DetailsActivity;
import java.util.List;

public class FavoriteBooksFragment extends Fragment {

    private DataRepository repository;
    private BooksRVAdapter mDataAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mProgressBar;
    private List<Books> books;

    public FavoriteBooksFragment() {
        // Required empty public constructor
    }

    public static FavoriteBooksFragment newInstance(String title) {
        FavoriteBooksFragment favoriteBooksFragment = new FavoriteBooksFragment();
        return favoriteBooksFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_books, container, false);
        mProgressBar = view.findViewById(R.id.favoriteBooksPB);
        repository = DataRepository.getInstance(this.getContext(), ApplicationClass.getInstance());
        books = repository.getBooksFromDB(Constants.user.getUsername());
        initRecycleView(view);
        setmProgressBar();
        initListener();

        return view;
    }

    public void setmProgressBar(){
            if ((mDataAdapter.getItemCount() > 0)) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                mProgressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setVisibility(View.GONE);
                    }
                }, 10000);
            }
    }

    public void initRecycleView(View view){
        mRecyclerView = view.findViewById(R.id.favoriteBooksRV);
        mProgressBar = view.findViewById(R.id.favoriteBooksPB);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mDataAdapter = new BooksRVAdapter(getContext().getApplicationContext(), books);
        mRecyclerView.setAdapter(mDataAdapter);
    }

    public void initListener(){
        mDataAdapter.setOnClickListener(new BooksRVAdapter.OnFavoriteClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra(Constants.BOOKS, books.get(position));
                startActivity(intent);
                }
        });
    }


    public void onFavoritePress(Author author) {
        repository.insertBooksToDB(Constants.user.getUsername(), true, author.getByStatement() == null ? "" : author.getByStatement(), author.getTitle(),
                                    author.getOcaid(), author.getPublishDate(), author.getPublishers().get(0) == null ? "" : author.getPublishers().get(0));
        List<Books> books = repository.getBooksFromDB(Constants.user.getUsername());
        mDataAdapter.setBooks(books);
    }

    public void onUnfavoritePress(Author author){
        repository.deleteBookFromDB(Constants.user.getUsername(), author.getTitle(), author.getByStatement());
        List<Books> books = repository.getBooksFromDB(Constants.user.getUsername());
        mDataAdapter.setBooks(books);
        setmProgressBar();
    }


}
