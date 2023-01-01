package com.androiddevs.mvvmnewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androiddevs.mvvmnewsapp.models.Article

@Database(
    // tables are passed in an array (in this case there is only one table)
    entities = [Article::class],
    version = 1
)

/* - database class for Room always has to be an Abstract class
   - tell database to add the TypeConverters class */
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase(){

    // function to return ArticleDao (from the ArticleDao interface)
    abstract fun getArticleDao(): ArticleDao

    // companion object to create the database
    companion object {

        /* - @Volatile allows other threads to see when a thread changes this instance
           - var to create a singleton instance of this ArticleDatabase class */
        @Volatile
        private var instance: ArticleDatabase? = null

        // to ensure there is only a single instance of the database at once
        private val LOCK = Any()

        /* - an "operator fun" is called whenever this object is instantiated
           - this function return the "instance" - if it is null then set that instance
             inside the synchronise block
           - synchronized() means code inside this block
             cannot be accessed by other threads at the same time */
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            /* - return the instance but with a null check
               - if it is null then call the function and set the current instance to it */
            instance ?: createDatabase(context).also { instance = it}
        }

        // function to create database
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }

}