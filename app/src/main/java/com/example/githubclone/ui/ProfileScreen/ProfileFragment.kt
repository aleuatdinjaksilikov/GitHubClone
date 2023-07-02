package com.example.githubclone.ui.ProfileScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentProfileBinding
import com.example.githubclone.ui.MainFragmentDirections
import com.example.githubclone.ui.ProfileScreen.adapter.RecyclerAdapterProfile
import com.example.githubclone.ui.ProfileScreen.vm.ProfileFragmentVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

@AndroidEntryPoint
class ProfileFragment:Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel : ProfileFragmentVM by viewModels()
    private val recyclerAdapterProfile = RecyclerAdapterProfile()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)


        initVariables()
        initObservers()
        initListeners()
    }

    private fun initVariables() {
        binding.rvRepository.adapter = recyclerAdapterProfile
        lifecycleScope.launch {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getUserInfo()
            viewModel.getRepositories()
        }
    }

    private fun initListeners() {
        binding.llRepositories.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.mainFragmentContainerView)
            navController.navigate(MainFragmentDirections.actionFragmentMainToRepositoryFragment())
        }
        binding.btnBack.setOnClickListener {
            exitProcess(0)
        }
    }

    private fun initObservers() {
        viewModel.getUserInfoFlow.onEach{
                Glide.with(this)
                    .load(it.avatar_url)
                    .into(binding.ivUser)
                binding.tvName.text = it.name
                binding.tvUsername.text = it.login
                binding.tvLocation.text = it.location
                binding.tvFollowers.text = "${it.followers} followers"
                binding.tvFollowing.text = "${it.following} following"
            binding.progressBar.visibility = View.GONE
        }.launchIn(lifecycleScope)

        viewModel.getRepositoriesFlow.onEach {
            recyclerAdapterProfile.submitList(it)
            binding.tvAmountRepository.text = it.size.toString()
        }.launchIn(lifecycleScope)
    }
}