package com.example.githubclone.ui.RepositoryScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentRepositoryBinding
import com.example.githubclone.ui.ProfileScreen.vm.ProfileFragmentVM
import com.example.githubclone.ui.RepositoryScreen.adapter.RepositoryRecyclerAdapter
import com.example.githubclone.ui.RepositoryScreen.vm.RepositoryFragmentVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepositoryFragment:Fragment(R.layout.fragment_repository) {
    private lateinit var binding: FragmentRepositoryBinding
    private var adapter = RepositoryRecyclerAdapter()
    private val viewModel : RepositoryFragmentVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRepositoryBinding.bind(view)

        initVariables()
        initObservable()
        initListeners()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
    }

    private fun initVariables() {
        binding.rvRepositoryFragment.adapter = adapter
        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getRepositories()
        }
    }

    private fun initObservable() {
        viewModel.getRepositoriesFlow.onEach {
            adapter.submitList(it)
            binding.progressBar.visibility = View.GONE
        }.launchIn(lifecycleScope)
    }

}