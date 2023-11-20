package com.jesus.moviesskell.features.splash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jesus.moviesskell.R
import com.jesus.moviesskell.databinding.FragmentSplashBinding
import com.jesus.moviesskell.features.splash.viewModel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment(R.layout.fragment_splash) {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        this.onEventChange()
        this.viewModel.checkPreferences()
        return binding.root
    }

    private fun onEventChange() {
        viewModel.isFirstLaunch.observe(viewLifecycleOwner) { isFirstLaunch ->
            if (isFirstLaunch) {
                this.showOnboarding()
            } else {
                this.showMovies()
            }
        }
    }

    private fun showOnboarding() {
        this.findNavController().navigate(SplashFragmentDirections.goToOnboarding())
    }

    private fun showMovies() {
        this.findNavController().navigate(SplashFragmentDirections.goToMovies())
    }
}
