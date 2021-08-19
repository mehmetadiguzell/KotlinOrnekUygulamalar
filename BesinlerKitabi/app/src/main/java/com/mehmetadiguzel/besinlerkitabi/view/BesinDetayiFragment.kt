package com.mehmetadiguzel.besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.mehmetadiguzel.besinlerkitabi.R
import com.mehmetadiguzel.besinlerkitabi.databinding.FragmentBesinDetayiBinding
import com.mehmetadiguzel.besinlerkitabi.util.gorselIndir
import com.mehmetadiguzel.besinlerkitabi.util.placeholderYap
import com.mehmetadiguzel.besinlerkitabi.viewmodel.BesinDetayiViewModel
import kotlinx.android.synthetic.main.fragment_besin_detayi.*
import kotlinx.coroutines.InternalCoroutinesApi


class BesinDetayiFragment : Fragment() {
    private lateinit var viewModel:BesinDetayiViewModel
        private var besinId=0
    private lateinit var besinBinding :FragmentBesinDetayiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        besinBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_besin_detayi,container,false)
        return besinBinding.root
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            besinId= BesinDetayiFragmentArgs.fromBundle(it).besinId

        }

        viewModel=ViewModelProviders.of(this).get(BesinDetayiViewModel::class.java)
        viewModel.roomVerisiniAl(besinId)


        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.besinLiveData.observe(viewLifecycleOwner, Observer { besin ->
            besin?.let { kontrol ->
                besinBinding.secilenBesin=besin
                /*
                besinIsmi.text = kontrol.besinIsim
                besinKalori.text = kontrol.besinKalori
                besinKarbon.text = kontrol.besinKarbonhidrat
                besinProtein.text = kontrol.besinProtein
                besinYag.text = kontrol.besinYag
                context?.let {
                    besinImage.gorselIndir(besin.besinGorsel, placeholderYap(it))
                }

                 */

            }
        })
    }

}