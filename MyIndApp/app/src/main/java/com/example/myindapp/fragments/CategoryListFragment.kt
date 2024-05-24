package com.example.myindapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myindapp.Application352
import com.example.myindapp.databinding.FragmentCategoryListBinding
import com.example.myindapp.fragments.CategoryViewModel
import com.example.myindapp.fragments.CategoryViewModelFactory

class CategoryListFragment : Fragment() {

    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!
    private val categoryViewModel: CategoryViewModel by viewModels {
        CategoryViewModelFactory((activity?.application as MyApplication).database.categoryDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoryAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        categoryViewModel.categories.observe(viewLifecycleOwner) { categories ->
            categories?.let { adapter.submitList(it) }
        }

        categoryViewModel.fetchCategoriesFromApi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}