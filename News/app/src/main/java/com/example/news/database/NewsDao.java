package com.example.news.database;


import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM mynews ORDER BY mpublishedAt")
    List<NewsEntry> loadAllNews();

    @Insert
    void insertNews(NewsEntry newsEntry);

    @Update(onConflict  = REPLACE)
    void  updateNews(NewsEntry newsEntry);

    @Delete
    void deleteNews(NewsEntry newsEntry);

    @Query("SELECT COUNT(*) FROM mynews")
    int getNoOfItems();

    @Query("SELECT * FROM mynews WHERE id = :ID")
    LiveData<NewsEntry> getItemFromId(int ID);

    @Query("delete from mynews")
    void clearAll();



}
