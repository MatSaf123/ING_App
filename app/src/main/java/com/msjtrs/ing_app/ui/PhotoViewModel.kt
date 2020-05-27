package com.msjtrs.ing_app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msjtrs.ing_app.domain.PostProperty
import com.msjtrs.ing_app.domain.UserProperty

class PhotoViewModel(userProperty : UserProperty, app: Application): AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<UserProperty>()
    val selectedProperty: LiveData<UserProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = userProperty
    }
}