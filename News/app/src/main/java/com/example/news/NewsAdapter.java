package com.example.news;


import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.database.AppDatabase;
import com.example.news.database.NewsEntry;
import com.facebook.drawee.view.SimpleDraweeView;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private static final String DATE_FORMAT = "dd/MM/yyy";


    private static final String LOG_TAG = NewsAdapter.class.getSimpleName();
    AppDatabase mDb;
    Context mContext;
    private List<NewsEntry> newsEntryList;
    private OnNewsListener mOnNewsListener;

    TextView emptyTextView;
    RecyclerView mRecylerView;

    //    Global Variables for bindViewHolder
    NewsEntry mNewsEntry;
    String mNewsTitle;
    String mUrlToImage;
    Date publishedAt;


    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public NewsAdapter(Context context, OnNewsListener listener, RecyclerView recyclerView, TextView emptyTextView) {
        this.mRecylerView = recyclerView;
        this.emptyTextView = emptyTextView;
        mOnNewsListener = listener;
        mContext = context;
        mDb = AppDatabase.getInstance(mContext);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(mContext).inflate(R.layout.news_list_item, parent, false);
        return new NewsViewHolder(view, mOnNewsListener);

    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        mNewsEntry = newsEntryList.get(position);

        mNewsTitle = mNewsEntry.getMtitle();

        mUrlToImage = mNewsEntry.getMurlToImage();
        publishedAt = mNewsEntry.getMpublishedAt();

        holder.clickUrl = mNewsEntry.getMurl();


        if (!TextUtils.isEmpty(mNewsTitle)) {
            holder.titleTextView.setText(mNewsTitle);
        }

        if (!TextUtils.isEmpty(mUrlToImage)) {
            holder.newsIconImageView.setImageURI(Uri.parse(mUrlToImage));
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
        } else {
            return 0;
        }


    }


    public void setNews(List<NewsEntry> newsEntries) {
        if (newsEntries == null || newsEntries.size() == 0) {
            Utilities.setEmptyLayout(mRecylerView, emptyTextView);
        } else {
            Utilities.setFullLayout(mRecylerView, emptyTextView);

        }
        newsEntryList = newsEntries;
        notifyDataSetChanged();

    }


    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titleTextView;
        public SimpleDraweeView newsIconImageView;
        public TextView dateTextView;
        public String clickUrl = "youtube.com";

        OnNewsListener onNewsListener;


        public NewsViewHolder(@NonNull View itemView, OnNewsListener onNewsListener) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.newsTitleTextView);
            newsIconImageView = itemView.findViewById(R.id.newsIconImageView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            this.onNewsListener = onNewsListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNewsListener.onNewsClick(clickUrl);
        }
    }

    public interface OnNewsListener {
        void onNewsClick(String url);
    }

}
