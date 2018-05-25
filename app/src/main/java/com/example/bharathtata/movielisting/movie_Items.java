package com.example.bharathtata.movielisting;

public class movie_Items {
    String mImageUrl;
    String mMovieName;
    String mRleaseDate;
    double mRating;
    public movie_Items(String ImageUrl,String MovieName,String ReleaseDate,double Rating){
        mImageUrl= ImageUrl;
        mMovieName=MovieName;
        mRleaseDate=ReleaseDate;
        mRating=Rating;
    }
    public String getImageUrl(){ return mImageUrl;}
    public String getMovieName(){ return mMovieName;}
    public String getReleaseDate(){ return mRleaseDate;}
    public double getRating(){ return mRating;}
}
