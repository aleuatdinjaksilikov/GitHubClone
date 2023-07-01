package com.example.githubclone.ui.RepositoryScreenHome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentRepositoryHomeBinding
import com.example.githubclone.ui.RepositoryScreen.vm.RepositoryFragmentVM
import com.example.githubclone.ui.RepositoryScreenHome.adapter.RepositoryHomeRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepositoryHomeFragment:Fragment(R.layout.fragment_repository_home) {
    private lateinit var binding : FragmentRepositoryHomeBinding
    private var adapter = RepositoryHomeRecyclerAdapter()
    private val viewModel : RepositoryFragmentVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRepositoryHomeBinding.bind(view)

        initVariables()
        initObservables()
        initListeners()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
    }

    private fun initObservables() {
        viewModel.getRepositoriesFlow.onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }

    private fun initVariables() {
        binding.rvRepositoryHome.adapter = adapter
        lifecycleScope.launch {
            viewModel.getRepositories()
        }
    }
}