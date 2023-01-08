package com.androiddevs.mvvmnewsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.mvvmnewsapp.repository.NewsRepository

/* - This class defines how the ViewModel should be created
   - Inherits from ViewModel.Factory
 */
class NewsViewModelProviderFactory(val newsRepository: NewsRepository) : ViewModelProvider.Factory {

    //override function to return an instance of the NewsViewModel
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}