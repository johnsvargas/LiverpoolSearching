package com.johnsapps.liverpoolsearching.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.johnsapps.liverpoolsearching.R
import com.johnsapps.liverpoolsearching.databinding.FragmentSearchBinding
import com.johnsapps.liverpoolsearching.search.ui.adapters.ProductAdapter
import com.johnsapps.liverpoolsearching.search.viewmodel.SearchViewModel
import androidx.recyclerview.widget.RecyclerView


class SearchFragment: Fragment() {
    private lateinit var binding : FragmentSearchBinding
    private val adapter by lazy { ProductAdapter() }
    private val viewModel by viewModels<SearchViewModel>()
    var queryToSearch:String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search,container,false)
        binding.lifecycleOwner = this

        viewModel.listProducts.observe(viewLifecycleOwner){stateApp->
            stateApp.fold({
                if(it.isNotEmpty()){
                    adapter.setData(it)
                }else{
                    if(adapter.itemCount ==0)
                        Toast.makeText(requireContext(),"No se encontraron resultados",Toast.LENGTH_SHORT).show()
                }
            },{ e,_->
                Toast.makeText(requireContext(),"Ocurrio un error: ${e.message}",Toast.LENGTH_SHORT).show()
            })


        }
        viewModel.isLanding.observe(viewLifecycleOwner){
            binding.pbLoader.isVisible = it
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView(){

        with(binding){

            val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            rvSearchListOfProducts.layoutManager = layoutManager
            rvSearchListOfProducts.adapter = adapter


            rvSearchListOfProducts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        val visibleItemCount = layoutManager.childCount
                        val totalItemCount = layoutManager.itemCount
                        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                        if (!binding.pbLoader.isVisible && viewModel.isHaveNextPage) {
                            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                                viewModel.getPlp(queryToSearch)
                            }
                        }
                    }
                }
            })

            tieSearchProduct.setOnEditorActionListener { v, actionId, event ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        queryToSearch = tieSearchProduct.text.toString()
                        if(queryToSearch.isNotEmpty()){
                            viewModel.getPlp(queryToSearch,true)
                            adapter.clearListProducts()
                        }
                        true
                    }
                    else -> false
                }
            }
        }
    }
}