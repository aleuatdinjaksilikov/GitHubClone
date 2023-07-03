package com.example.githubclone.ui.SearchFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubclone.R
import com.example.githubclone.data.models.HistoryData
import com.example.githubclone.databinding.FragmentSearchBinding
import com.example.githubclone.ui.SearchFragment.adapter.SearchHistoryAdapter
import com.example.githubclone.ui.SearchFragment.vm.SearchFragmentVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment:Fragment(R.layout.fragment_search) {
    private lateinit var binding:FragmentSearchBinding
    private var adapter = SearchHistoryAdapter()
    private val viewModel : SearchFragmentVM by viewModels()
    private var text = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        initVariables()
        initListeners()
        initObservables()

    }

    private fun initObservables() {

        viewModel.historyList.onEach {
            binding.rvHistory.adapter = adapter
            if (it.isEmpty()){
                binding.tvFindYourStuff.visibility = View.VISIBLE
                binding.tvDesc.visibility = View.VISIBLE
            }else{
                binding.apply {
                    tvRecentSearches.visibility = View.VISIBLE
                    tvClear.visibility = View.VISIBLE
                    rvHistory.visibility  = View.VISIBLE
                    binding.tvFindYourStuff.visibility = View.GONE
                    binding.tvDesc.visibility = View.GONE
                }
                adapter.submitList(it)
            }
        }.launchIn(lifecycleScope)
    }

    private fun initVariables() {
        binding.rvHistory.adapter = adapter

        lifecycleScope.launch {
            viewModel.getAllText()
        }
    }

    private fun initListeners() {

        adapter.onClick {
            binding.etSearch.setQuery(it.title,false)
            binding.tvRecentSearches.visibility = View.GONE
            binding.tvClear.visibility = View.GONE
            binding.rvHistory.visibility = View.GONE
        }

        binding.llRepositories.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSearchRepositoryFragment(text = text))
            lifecycleScope.launch {
                viewModel.insertText(HistoryData(0,text))
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.llPeople.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSearchUserFragment(name = text))
            lifecycleScope.launch {
                viewModel.insertText(HistoryData(0,text))
            }
        }

        binding.tvClear.setOnClickListener {
            lifecycleScope.launch {
                viewModel.deleteAll()
            }
        }

        binding.etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(string: String?): Boolean {
                if (string != null) {
                    text = string
                }
                binding.apply {
                    tvRepositories.text =
                        String.format(getString(R.string.tv_repositories_with), string)
                    tvIssues.text = String.format(getString(R.string.tv_issues_with), string)
                    tvPullRequests.text =
                        String.format(getString(R.string.tv_pull_requests_with), string)
                    tvPeople.text = String.format(getString(R.string.tv_people_with), string)
                    tvOrganizations.text =
                        String.format(getString(R.string.tv_organizations_with), string)
                    tvJumpTo.text = String.format(getString(R.string.tv_jump_to), string)
                    tvFindYourStuff.visibility = View.GONE
                    tvDesc.visibility = View.GONE
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    text = newText
                }
                if (binding.etSearch.isEmpty() || newText == "") {
                    binding.apply {
                        llRepositories.visibility = View.GONE
                        llIssues.visibility = View.GONE
                        llPullRequests.visibility = View.GONE
                        llPeople.visibility = View.GONE
                        llOrganizations.visibility = View.GONE
                        llJumpTo.visibility = View.GONE

//                        binding.tvRecentSearches.visibility = View.VISIBLE
//                        binding.tvClear.visibility = View.VISIBLE
//                        binding.rvHistory.visibility = View.VISIBLE

                        lifecycleScope.launch {
                            viewModel.historyList.collect {
                                binding.rvHistory.adapter = adapter
                                if (it.isEmpty()){
                                    binding.tvFindYourStuff.visibility = View.VISIBLE
                                    binding.tvDesc.visibility = View.VISIBLE
                                }else{
                                    binding.apply {
                                        tvRecentSearches.visibility = View.VISIBLE
                                        tvClear.visibility = View.VISIBLE
                                        rvHistory.visibility  = View.VISIBLE
                                        binding.tvFindYourStuff.visibility = View.GONE
                                        binding.tvDesc.visibility = View.GONE
                                    }
                                    adapter.submitList(it)
                                }
                            }
                        }
                    }
                } else {
                    binding.apply {
                        llRepositories.visibility = View.VISIBLE
                        llIssues.visibility = View.VISIBLE
                        llPullRequests.visibility = View.VISIBLE
                        llPeople.visibility = View.VISIBLE
                        llOrganizations.visibility = View.VISIBLE
                        llJumpTo.visibility = View.VISIBLE
                        tvRepositories.text =
                            String.format(getString(R.string.tv_repositories_with), newText)
                        tvIssues.text = String.format(getString(R.string.tv_issues_with), newText)
                        tvPullRequests.text =
                            String.format(getString(R.string.tv_pull_requests_with), newText)
                        tvPeople.text = String.format(getString(R.string.tv_people_with), newText)
                        tvOrganizations.text =
                            String.format(getString(R.string.tv_organizations_with), newText)
                        tvJumpTo.text = String.format(getString(R.string.tv_jump_to), newText)
                        tvFindYourStuff.visibility = View.GONE
                        tvDesc.visibility = View.GONE

                        binding.tvRecentSearches.visibility = View.GONE
                        binding.tvClear.visibility = View.GONE
                        binding.rvHistory.visibility = View.GONE
                    }
                }
                return false
            }

        })

//        app:iconifiedByDefault="false"
//        app:queryHint="Search GitHub"
    }
}