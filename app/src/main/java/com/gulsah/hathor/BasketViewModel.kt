package com.gulsah.hathor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gulsah.hathor.entity.Products
import com.gulsah.hathor.repo.BasketRepo

class BasketViewModel : ViewModel() {

    var basketList = MutableLiveData<List<Products>>()


    val bdaor = BasketRepo()

    init {
        getBasket()
        basketList = bdaor.basketGet()
    }

    fun getBasket() {
        bdaor.basketShow()
        basketList = bdaor.basketGet()
    }

    fun updateBasket(id: Int, basketStatus: Int) {
        bdaor.updateBasket(id, basketStatus)    }





}