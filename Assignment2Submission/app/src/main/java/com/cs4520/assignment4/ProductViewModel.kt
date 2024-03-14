package com.cs4520.assignment4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel (application: Application) : AndroidViewModel(application) {
    private val repository = ProductRepository(application)

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    /*init {
        fetchProducts()
    } */

    fun fetchProducts(page: Int? = null) {
        viewModelScope.launch {
            _products.postValue(repository.getProducts(page))
        }
    }
}