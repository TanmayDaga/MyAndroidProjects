package com.example.news;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.news.database.AppDatabase;
import com.example.news.database.NewsEntry;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class FetchData {
    private static final String LOG_TAG = FetchData.class.getSimpleName();

    private static final String API_KEY = "332ec178c7ee470b9fbfd4500f07e43d";
    static ArticleResponse mArticleResponse;
    private static Context mContext;
    private static Activity mActivity;

    public static void getAndSetData(NewsAdapter newsAdapter, Context context, Activity activity, String state) {
        mContext = context;
        mActivity = activity;
        switch (state) {
            case MainActivity.NOT_REQUIRED_TO_REFRESH:setData(newsAdapter);
            default:fetchData(newsAdapter);

        }
    }

    private static void fetchData(NewsAdapter newsAdapter) {

        Log.d(LOG_TAG, "Data fetching");
        NewsApiClient newsApiClient = new NewsApiClient(API_KEY);
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder().pageSize(10).q("")
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {

                    @Override
                    public void onSuccess(ArticleResponse articleResponse) {
                        mArticleResponse = articleResponse;
                        insertData(newsAdapter);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.d(LOG_TAG, throwable.toString());
                    }
                }
        );


    }

    private static void insertData(NewsAdapter newsAdapter) {
        if (mArticleResponse != null) {

            AppDatabase mDb = AppDatabase.getInstance(mContext);

            AppExecutors.getsInstance().diskIo().execute(new Runnable() {
                @Override
                public void run() {

                    mDb.newsDao().clearAll();
                    Log.d(LOG_TAG, String.valueOf(mArticleResponse.getTotalResults()));

                    for (Article article : mArticleResponse.getArticles()) {
                        NewsEntry newsEntry = null;
                        try {
                            newsEntry = new NewsEntry(
                                    article.getSource().getName(),
                                    article.getTitle(),
                                    article.getUrlToImage(),
                                    article.getUrl(),
                                    Utilities.convertDate(article.getPublishedAt())
                            );
                            Log.d(LOG_TAG, newsEntry.getDesc());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        mDb.newsDao().insertNews(newsEntry);
                    }
                    setData(newsAdapter);


                }


            });

        }
    }

    private static void setData(NewsAdapter newsAdapter) {

        AppExecutors.getsInstance().diskIo().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase mDb = AppDatabase.getInstance(mContext);
                List<NewsEntry> allNews = mDb.newsDao().loadAllNews();

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        newsAdapter.setNews(allNews);
                    }
                });
            }
        });


    }


}
