package com.msjtrs.ing_app.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.msjtrs.ing_app.domain.PostProperty
import com.msjtrs.ing_app.domain.UserProperty

class PhotoViewModelFactory(
    private val userProperty: UserProperty,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoViewModel::class.java)) {
            return PhotoViewModel(userProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}