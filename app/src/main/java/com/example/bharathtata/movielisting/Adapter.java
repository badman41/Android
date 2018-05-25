package com.example.bharathtata.movielisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
   private Context mContext;
    private ArrayList<movie_Items> mList;
    public Adapter(Context mContext,ArrayList<movie_Items> List) {
        this.mContext = mContext;
        mList=List;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.movie_cards,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      movie_Items currentItem = mList.get(position);
     String imageUrl = currentItem.getImageUrl();
      String releaseDate = currentItem.getReleaseDate();
      String movieName=currentItem.getMovieName();
      double rating=currentItem.getRating();
      holder.mMovieName.setText(movieName);
      String rating_in_string = "Rating: "+String.valueOf(rating);
      holder.mRating.setText(rating_in_string);
      holder.mReleaseDate.setText(releaseDate);
      Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageUrl);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageUrl;
        TextView mReleaseDate,mRating,mMovieName;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageUrl=itemView.findViewById(R.id.image_view);
            mReleaseDate=itemView.findViewById(R.id.release_date);
            mRating=itemView.findViewById(R.id.rating);
            mMovieName=itemView.findViewById(R.id.movie_name);
        }
    }
}
