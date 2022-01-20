package com.example.news.database;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "mynews")
public class NewsEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String msource;
    private String mtitle;
    private String murlToImage;
    private String murl;
    private Date mpublishedAt;
    public NewsEntry(){}
    public NewsEntry(String source, String title, String urlToImage, String url, Date publishedAt) {

        msource = source;
        mtitle = title;
        murlToImage = urlToImage;
        murl = url;
        mpublishedAt = publishedAt;

    }

    public String getMsource() {
        return msource;
    }

    public void setMsource(String msource) {
        this.msource = msource;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMurlToImage() {
        return murlToImage;
    }

    public void setMurlToImage(String murlToImage) {
        this.murlToImage = murlToImage;
    }

    public String getMurl() {
        return murl;
    }

    public void setMurl(String murl) {
        this.murl = murl;
    }

    public Date getMpublishedAt() {
        return mpublishedAt;
    }

    public void setMpublishedAt(Date mpublishedAt) {
        this.mpublishedAt = mpublishedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc(){
        return msource+mtitle+murlToImage+murl;
    }
}
