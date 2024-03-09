package com.example.aviatickets.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aviatickets.R
import com.example.aviatickets.adapter.OfferListAdapter
import com.example.aviatickets.databinding.FragmentOfferListBinding
import com.example.aviatickets.model.entity.Offer
import com.example.aviatickets.model.network.ApiClient
import com.example.aviatickets.model.service.FakeService
import com.google.android.material.snackbar.Snackbar


class OfferListFragment : Fragment() {

    companion object {
        fun newInstance() = OfferListFragment()
    }

    private var _binding: FragmentOfferListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: OfferListAdapter by lazy {
        OfferListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        adapter.setItems(FakeService.offerList)
        fetchOffers()
    }

    private fun fetchOffers() {
        ApiClient.getApiService().getOffers().enqueue(object : retrofit2.Callback<List<Offer>> {
            override fun onResponse(
                call: retrofit2.Call<List<Offer>>,
                response: retrofit2.Response<List<Offer>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { offers ->
                        adapter.setItems(offers)
                    }
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Offer>>, t: Throwable) {
                Snackbar.make(binding.root, "Failed to load offers: ${t.localizedMessage}", Snackbar.LENGTH_LONG).show()
            }

        })
    }
    private fun setupUI() {
        with(binding) {
            offerList.adapter = adapter

            sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.sort_by_price -> {
                        /**
                         * implement sorting by price
                         */
                        adapter.sortByPrice()
                    }

                    R.id.sort_by_duration -> {
                        /**
                         * implement sorting by duration
                         */
                        adapter.sortByDuration()
                    }
                }
            }
        }
    }
}