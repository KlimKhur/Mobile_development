package com.example.myindapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.myindapp.databinding.FragmentStoreDetailBinding
import com.example.myindapp.fragments.StoreDetailViewModel
import com.example.myindapp.fragments.StoreDetailViewModelFactory

class StoreDetailFragment : Fragment() {

    private lateinit var binding: FragmentStoreDetailBinding
    private val args: StoreDetailFragmentArgs by navArgs()
    private val storeDetailViewModel: StoreDetailViewModel by viewModels {
        StoreDetailViewModelFactory((activity?.application as MyApplication).database.storeDao(), args.storeId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storeDetailViewModel.store.observe(viewLifecycleOwner) { store ->
            store?.let {
                binding.storeName.text = it.name
                binding.storeAddress.text = it.address
                binding.storeHours.text = it.hours
                binding.storeDescription.text = it.description
                binding.storePhone.text = it.phone
                binding.storeEmail.text = it.email
                binding.storeWebsite.text = it.website
            }
        }
    }
}