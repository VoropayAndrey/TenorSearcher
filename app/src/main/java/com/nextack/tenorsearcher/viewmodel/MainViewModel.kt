package com.nextack.tenorsearcher.viewmodel

import androidx.lifecycle.ViewModel
import com.nextack.tenorsearcher.repository.Repository
import javax.inject.Inject

class MainViewModel : ViewModel {

    private lateinit var repository: Repository

    //@Inject
    constructor(repository: Repository) {
        this.repository = repository
    }

    override fun onCleared() {
        super.onCleared()
    }
}