package com.example.githubclone.ui.SearchFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentSearchRepoBinding
import com.example.githubclone.ui.SearchFragment.adapter.SearchRepoAdapter
import com.example.githubclone.ui.SearchFragment.vm.SearchFragmentVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchRepositoryFragment:Fragment(R.layout.fragment_search_repo) {
    private lateinit var binding:FragmentSearchRepoBinding
    private var adapter = SearchRepoAdapter()
    private val viewModel : SearchFragmentVM by viewModels()
    private val args : SearchRepositoryFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchRepoBinding.bind(view)

        initVariables()
        initObservables()
        initListeners()

    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initObservables() {
        viewModel.getRepositoriesByNameFlow.onEach {
            adapter.submitList(it.items)
        }.launchIn(lifecycleScope)
    }

    private fun initVariables() {
        binding.rvRepositorySearch.adapter = adapter
        lifecycleScope.launch {
            viewModel.getRepositoriesByName(args.text)
        }
    }
}