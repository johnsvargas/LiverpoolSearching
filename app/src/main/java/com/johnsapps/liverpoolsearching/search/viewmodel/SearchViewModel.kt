package com.johnsapps.liverpoolsearching.search.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.johnsapps.liverpoolsearching.search.data.model.PlpState
import com.johnsapps.liverpoolsearching.search.data.model.Product
import com.johnsapps.liverpoolsearching.search.data.repository.SearchRepositoryImpl
import com.johnsapps.liverpoolsearching.util.LiveDataState
import com.johnsapps.liverpoolsearching.util.MutableLiveDataState
import com.johnsapps.liverpoolsearching.util.StateApp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel:ViewModel() {
    private val repository by lazy { SearchRepositoryImpl() }

    private val _listProducts: MutableLiveDataState<ArrayList<Product>> by lazy { MutableLiveDataState() }
    val listProducts:LiveDataState<ArrayList<Product>> = _listProducts

    private val _isLanding :MutableLiveData<Boolean> by lazy{ MutableLiveData() }
    val isLanding:LiveData<Boolean> = _isLanding.distinctUntilChanged()

    private var _plpState: PlpState? = null

    var isHaveNextPage = false
    private var page = 1

    fun getPlp(search: String,clearSearch:Boolean = false){
        if(clearSearch){
            page = 1
        }

        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler{ _, throwable ->
            _listProducts.postValue(StateApp.Error(throwable))
            _isLanding.postValue( false)
            //_listDataForSearching.postValue(StateApp.Error(throwable))
        }){
            _isLanding.postValue(true)
            val response = repository.getPlp(search,page)
            _listProducts.postValue(StateApp.Success(response.plpResults.records))
            _plpState = response.plpResults.plpState
            isHaveNextPage = _plpState?.isHaveNextPage?:false
            if(isHaveNextPage){
                page++
            }
            _isLanding.postValue(false)
        }
    }
}