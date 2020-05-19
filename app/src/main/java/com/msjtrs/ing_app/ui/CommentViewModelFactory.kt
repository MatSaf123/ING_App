package com.msjtrs.ing_app.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.msjtrs.ing_app.domain.PostProperty

class CommentViewModelFactory(
    private val postProperty: PostProperty,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            return CommentViewModel(postProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}