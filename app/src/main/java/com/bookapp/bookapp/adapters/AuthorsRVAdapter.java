package com.bookapp.bookapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bookapp.bookapp.R;
import com.bookapp.bookapp.listeners.FavoritePressListener;
import com.bookapp.bookapp.local.models.Books;
import com.bookapp.bookapp.remote.models.Author;
import com.bookapp.bookapp.views.fragments.FavoriteBooksFragment;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AuthorsRVAdapter extends RecyclerView.Adapter<AuthorsRVAdapter.MyViewHolder> {

    private List<Author> authors;
    private OnItemClickListener onItemClickListener;
    private Context context;
    public FavoritePressListener favoritePressListener;
    private List<Books> books;

    public AuthorsRVAdapter(Context context, List<Author> authors, FavoritePressListener favoritePressListener, List<Books> books){
        this.favoritePressListener = favoritePressListener;
        this.authors = authors;
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public AuthorsRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_data, parent, false);

        return new AuthorsRVAdapter.MyViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final AuthorsRVAdapter.MyViewHolder holder, final int position) {
        final String title = authors.get(position).getTitle();
        String author = authors.get(position).getByStatement();
        if(author == null || author.equals("Edited by Damon Knight")){
            String[] key = authors.get(position).getAuthors().get(0).getKey().split("/");

            switch(key[2]){
                case "OL27278A" :
                    author = "Harry Harrison";
                    break;
                case "OL2628836A" :
                    author = "Damon Knight";
                    break;
                case "OL39006A" :
                    author = "Margaret Peterson Haddix";
                    break;
                case "OL444006A" :
                    author = "Jude Watson";
                    break;
                case "OL2162284A" :
                    author = "Stephen King";
                    break;
                default:
                    author = "Harry Harrison";
                    break;
            }
            authors.get(position).setByStatement(author);
        }
        String img;

        holder.titleTV.setText(title);
        holder.authorTV.setText(author);

        if(getIsFavorite(author, title)) {
            holder.favoriteIV.setImageResource(R.drawable.ic_action_like_yellow);
        } else {
            holder.favoriteIV.setImageResource(R.drawable.ic_action_like_blue);
        }

        try{
            if(authors.get(position).getCovers().get(0) != null){
                img = "https://covers.openlibrary.org/b/id/" + authors.get(position).getCovers().get(0).intValue() + "-M.jpg";
                authors.get(position).setOcaid(img);
            } else {
                img = "https://styluspub.presswarehouse.com/publishers/default_cover.png";
            }
        } catch (Exception e){
            img = "https://styluspub.presswarehouse.com/publishers/default_cover.png";
        }
        Glide.with(context).load(img).into(holder.coverIV);

        holder.favoriteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getIsFavorite(holder.authorTV.getText().toString(), title)) {
                    favoritePressListener.onFavoritePress(authors.get(position));
                    holder.favoriteIV.setImageResource(R.drawable.ic_action_like_yellow);
                } else {
                    favoritePressListener.onUnfavoritePress(authors.get(position));
                    holder.favoriteIV.setImageResource(R.drawable.ic_action_like_blue);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    private boolean getIsFavorite(String author, String title){

        for(Books book : books){
            if(book.getAuthor().equals(author) && book.getTitle().equals(title)){
                return true;
            }
        }
        return false;
    }

    public void setBooks(List<Books> books){
        this.books = books;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleTV, authorTV;
        ImageView coverIV, favoriteIV;
        OnItemClickListener onItemClickListener;


        public MyViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);
            view.setOnClickListener(this);
            favoriteIV = view.findViewById(R.id.like_iv_img);
            titleTV = view.findViewById(R.id.titleTV);
            authorTV = view.findViewById(R.id.authorTV);
            coverIV = view.findViewById(R.id.row_iv_img);

            this.onItemClickListener = onItemClickListener;

        }


        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v,getAdapterPosition());
        }
    }
}
