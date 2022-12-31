package com.androiddevs.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androiddevs.mvvmnewsapp.Article

//Data Access Object - defines function to access the database (read, save articles, etc)
@Dao
interface ArticleDao {

    // onConflict determines what happens if the article already exists in the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long  //(update)(insert): returns ID that was inserted

    // LiveData will notify any changes that happened in the List of Articles
    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}