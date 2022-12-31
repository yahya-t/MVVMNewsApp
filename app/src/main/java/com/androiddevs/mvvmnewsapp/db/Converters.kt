package com.androiddevs.mvvmnewsapp.db

import androidx.room.TypeConverter
import com.androiddevs.mvvmnewsapp.models.Source

class Converters {

    /* - fun to convert Source to String
       - @TypeConverter declares this as a converter functions */
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.Name
    }

    /* - fun to convert String to Source
       - @TypeConverter declares this as a converter functions */
    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }

}