package com.androiddevs.mvvmnewsapp.ui

import androidx.lifecycle.ViewModel
import com.androiddevs.mvvmnewsapp.repository.NewsRepository


/* - cannot use constructor parameters by default for own ViewModels
   - to do this, create a class that extends ViewModelProvider.Factory
   -
 */
class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {
}