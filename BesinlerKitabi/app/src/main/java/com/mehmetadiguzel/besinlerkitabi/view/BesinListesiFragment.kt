package com.mehmetadiguzel.besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmetadiguzel.besinlerkitabi.R
import com.mehmetadiguzel.besinlerkitabi.adapter.BesinAdapter
import com.mehmetadiguzel.besinlerkitabi.viewmodel.BesinListesiViewModel
import kotlinx.android.synthetic.main.fragment_besin_listesi.*
import kotlinx.coroutines.InternalCoroutinesApi


class BesinListesiFragment : Fragment() {
    private lateinit var viewModel :BesinListesiViewModel
    private val recyclearBesinAdapter =BesinAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_listesi, container, false)
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(BesinListesiViewModel::class.java)
        viewModel.refreshFromInternet()

        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=recyclearBesinAdapter
        swipeReflesh.setOnRefreshListener {
            progressBar.visibility=View.VISIBLE
            besinHata.visibility=View.GONE
            recyclerView.visibility=View.GONE

            viewModel.refreshData()
            swipeReflesh.isRefreshing=false
        }

        observeLiveData()

    }

    fun observeLiveData(){
        viewModel.besinler.observe(viewLifecycleOwner, Observer { besinler ->
            besinler?.let {
                recyclerView.visibility=View.VISIBLE
                recyclearBesinAdapter.besinListesiGuncelle(besinler)
            }
        })
        viewModel.besinHataMesaji.observe(viewLifecycleOwner, Observer { hata ->
            hata?.let {
                if(it){
                    besinHata.visibility=View.VISIBLE
                    recyclerView.visibility=View.GONE
                }else{
                    besinHata.visibility=View.GONE
                }
            }
        })
        viewModel.besinYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor ->
            yukleniyor?.let {
                if(it){
                    recyclerView.visibility=View.GONE
                    besinHata.visibility=View.GONE
                    progressBar.visibility=View.VISIBLE
                }else{
                    progressBar.visibility=View.GONE
                }
            }
        })
    }


}