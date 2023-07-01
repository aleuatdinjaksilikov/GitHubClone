package com.example.githubclone.ui.HomeScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentHomeBinding
import com.example.githubclone.ui.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment:Fragment(R.layout.fragment_home) {
    private lateinit var binding:FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initListeners()
    }

    private fun initListeners() {
        binding.llRepositories.setOnClickListener {
            val navController =
                Navigation.findNavController(requireActivity(), R.id.mainFragmentContainerView)
            navController.navigate(MainFragmentDirections.actionFragmentMainToRepositoryHomeFragment())
        }

        binding.btnSearch.setOnClickListener {
            val navController =
                Navigation.findNavController(requireActivity(), R.id.mainFragmentContainerView)
            navController.navigate(MainFragmentDirections.actionFragmentMainToSearchFragment())
        }
    }
}