package com.msjtrs.ing_app.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.msjtrs.ing_app.domain.PostProperty

class DetailViewModelFactory(
    private val postProperty: PostProperty,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(postProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}