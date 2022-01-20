package com.example.news;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.RoundedCorner;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.database.AppDatabase;
import com.example.news.database.NewsEntry;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private static final String DATE_FORMAT = "dd/MM/yyy";
    private static final String LOG_TAG  = NewsAdapter.class.getSimpleName();
    AppDatabase mDb;
    Context mContext;
    private List<NewsEntry> newsEntryList;


//    Gobal Varibales for bindviewholder
    NewsEntry mNewsEntry;
    String mNewsTitle;
    String mUrlToImage;
    Date publishedAt;



    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public NewsAdapter(Context context) {
        mContext = context;
        mDb = AppDatabase.getInstance(mContext);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_list_item,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        mNewsEntry = newsEntryList.get(position);

        mNewsTitle = mNewsEntry.getMtitle();

        mUrlToImage = mNewsEntry.getMurlToImage();
        Date publishedAt = mNewsEntry.getMpublishedAt();

        if (!TextUtils.isEmpty(mNewsTitle)) {
            holder.titleTextView.setText(mNewsTitle);
        }
        if (!TextUtils.isEmpty(mUrlToImage)) {
            Picasso.get().load(mUrlToImage).
                    networkPolicy(NetworkPolicy.OFFLINE).
                    fit().error(R.drawable.error).transform(new RoundedCornersTransformation(10,10)).
                    into(holder.newsIconImageView);
        }

        if (publishedAt != null) {
            holder.dateTextView.setText(
                    dateFormat.format(mNewsEntry.getMpublishedAt())
            );
        }

    }

    @Override
    public int getItemCount() {
        if (newsEntryList != null) {
            return newsEntryList.size();
        }
        return 0;
    }


    public void setNews(List<NewsEntry> newsEntries) {
        newsEntryList = newsEntries;
        notifyDataSetChanged();
    }


    class NewsViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView newsIconImageView;
        public TextView dateTextView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.newsTitleTextView);
            newsIconImageView = itemView.findViewById(R.id.newsIconImageView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }


}
