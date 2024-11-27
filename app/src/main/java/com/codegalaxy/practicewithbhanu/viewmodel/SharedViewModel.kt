package com.codegalaxy.practicewithbhanu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _sharedText = MutableLiveData<String>()
    val sharedText: LiveData<String> get() = _sharedText

    fun setSharedText(text: String) {
        _sharedText.value = text
    }
}
