package com.bookapp.bookapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookapp.bookapp.R;
import com.bookapp.bookapp.local.models.Books;
import com.bumptech.glide.Glide;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BooksRVAdapter extends RecyclerView.Adapter<BooksRVAdapter.ViewHolder> {

    private List<Books> books;
    private OnFavoriteClickListener onFavoriteClickListener;
    private Context context;

    public BooksRVAdapter(Context context, List<Books> books){
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public BooksRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_data, parent, false);

        return new ViewHolder(itemView, onFavoriteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksRVAdapter.ViewHolder holder, int position) {
        String title = books.get(position).getTitle();
        String author = books.get(position).getAuthor();
        String img = "";

        holder.favoriteIV.setVisibility(View.INVISIBLE);
        holder.titleTV.setText(title);
        holder.authorTV.setText(author);

        if(books.get(position).getCover() == null){
            img = "https://styluspub.presswarehouse.com/publishers/default_cover.png";
        } else {
            img = books.get(position).getCover();
        }
        Glide.with(context).load(img).into(holder.coverIV);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public interface OnFavoriteClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(OnFavoriteClickListener onFavoriteClickListener){
        this.onFavoriteClickListener = onFavoriteClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleTV, authorTV;
        ImageView coverIV, favoriteIV;
        OnFavoriteClickListener onFavoriteClickListener;


        public ViewHolder (View view, OnFavoriteClickListener onFavoriteClickListener) {
            super(view);
            view.setOnClickListener(this);
            titleTV = view.findViewById(R.id.titleTV);
            authorTV = view.findViewById(R.id.authorTV);
            coverIV = view.findViewById(R.id.row_iv_img);
            favoriteIV = view.findViewById(R.id.like_iv_img);
            this.onFavoriteClickListener = onFavoriteClickListener;

        }

        @Override
        public void onClick(View v) {
            onFavoriteClickListener.onItemClick(v,getAdapterPosition());

        }
    }

    public void setBooks(List<Books> books){
        this.books = books;
        notifyDataSetChanged();
    }
}
