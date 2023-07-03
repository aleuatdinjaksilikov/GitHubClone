package com.example.githubclone.ui.LoginScreen

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubclone.R
import com.example.githubclone.databinding.FragmentLoginBinding
import com.example.githubclone.ui.LoginScreen.vm.LoginFragmentVM
import com.example.githubclone.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment:Fragment(R.layout.fragment_login) {
    private lateinit var binding:FragmentLoginBinding
    private val viewModel : LoginFragmentVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        if (SharedPref.pref.getInt("login_amount",-1)>=1){
            findNavController().navigate(
                LoginFragmentDirections.actionFragmentLoginToFragmentMain()
            )
        }else{
            initObservers()
            initListeners()
        }
    }

    private fun initListeners() {
        binding.btnSignInGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize?client_id=8f3cf5f09bd0c93a0528&scope=repo"))
            startActivity(intent)
        }
    }

    private fun initObservers() {

        viewModel.loginLiveData.observe(viewLifecycleOwner) {
            SharedPref.pref.edit().putInt("login_amount",1).apply()
            SharedPref.pref.edit().putString("access_token",it?.access_token).apply()
            findNavController().navigate(LoginFragmentDirections.actionFragmentLoginToFragmentMain())
        }
        paramSpannableText()
    }

    override fun onResume() {
        super.onResume()
        val uri:Uri? = requireActivity().intent.data
        if (uri!=null){
            val code = uri.getQueryParameter("code")
            if (code != null) {
                Log.d("Codeeee",code)
                SharedPref.pref.edit().putString("code",code).apply()
                lifecycleScope.launch {
                    viewModel.login()
                }
            } else if ((uri.getQueryParameter("error")) != null) {
                Toast.makeText(requireContext(),"Error Code",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun paramSpannableText() {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://docs.github.com/en/site-policy/github-terms/github-terms-of-service")
                )
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#1867C1")
            }
        }
        val clickableSpan2 = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://docs.github.com/en/site-policy/privacy-policies/github-privacy-statement")
                )
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#1867C1")
            }
        }
        val tou: SpannableString =
            SpannableString("By signing in you accept our Terms of use and").apply {
                setSpan(
                    clickableSpan,
                    29,
                    41,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        val pp: SpannableString = SpannableString("Privacy policy").apply {
            setSpan(
                clickableSpan2,
                0,
                14,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        binding.textLogin1.text = tou
        binding.textLogin1.movementMethod = LinkMovementMethod.getInstance()
        binding.privacyPolicy.text = pp
        binding.privacyPolicy.movementMethod = LinkMovementMethod.getInstance()
    }

}