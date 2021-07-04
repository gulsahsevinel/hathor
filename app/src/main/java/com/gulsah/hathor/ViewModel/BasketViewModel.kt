package com.gulsah.hathor.ViewModel

import androidx.lifecycle.ViewModel
import com.gulsah.hathor.repo.BasketRepo

class BasketViewModel : ViewModel() {
    val bdaor = BasketRepo()
}