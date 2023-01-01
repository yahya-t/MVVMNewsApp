package com.androiddevs.mvvmnewsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// declares this class as a table in the database
@Entity(
    tableName = "articles"
)
data class Article(
    @PrimaryKey(autoGenerate = true) // automatically increments the PK
    val id: Int? = null, //Primary Key
    val author: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String
)