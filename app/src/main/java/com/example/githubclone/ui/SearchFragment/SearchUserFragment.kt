package com.example.githubclone.ui.SearchFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentSearchUserBinding
import com.example.githubclone.ui.SearchFragment.adapter.SearchUserAdapter
import com.example.githubclone.ui.SearchFragment.vm.SearchFragmentVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchUserFragment:Fragment(R.layout.fragment_search_user) {
    private lateinit var binding: FragmentSearchUserBinding
    private var adapter = SearchUserAdapter()
    private val viewModel : SearchFragmentVM by viewModels()
    private val args : SearchUserFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchUserBinding.bind(view)


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
        viewModel.getUsersFlow.onEach {
            adapter.submitList(it.items)
            binding.progressBar.visibility = View.GONE
        }.launchIn(lifecycleScope)
    }

    private fun initVariables() {
        binding.rvUsers.adapter = adapter
        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getSearchUser(args.name)
        }
    }
}