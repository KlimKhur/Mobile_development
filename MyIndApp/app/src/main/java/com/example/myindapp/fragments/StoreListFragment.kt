package com.example.myindapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myindapp.databinding.FragmentStoreListBinding
import com.example.myindapp.repository.StoreRepository
import com.example.myindapp.fragments.StoreViewModel
import com.example.myindapp.fragments.StoreViewModelFactory

class StoreListFragment : Fragment() {

    private lateinit var binding: FragmentStoreListBinding
    private val args: StoreListFragmentArgs by navArgs()
    private val storeViewModel: StoreViewModel by viewModels {
        StoreViewModelFactory(StoreRepository((activity?.application as MyApplication).database.storeDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StoreAdapter { store ->
            val action = StoreListFragmentDirections.actionStoreListFragmentToStoreDetailFragment(store.id)
            findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        storeViewModel.getStores(args.categoryId).observe(viewLifecycleOwner) { stores ->
            stores?.let { adapter.submitList(it) }
        }

        storeViewModel.fetchStoresFromApi(args.categoryId)
    }
}