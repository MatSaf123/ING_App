package com.msjtrs.ing_app.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msjtrs.ing_app.domain.PostProperty

class DetailViewModel(postProperty: PostProperty, app: Application): AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<PostProperty>()
    val selectedProperty: LiveData<PostProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = postProperty
    }
}