package com.jesus.moviesskell.features.onboarding.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.jesus.moviesskell.R
import com.jesus.moviesskell.databinding.FragmentOnboardingBinding
import com.jesus.moviesskell.domain.models.onboarding.OnboardingScene
import com.jesus.moviesskell.features.onboarding.view.adapter.OnBoardingPagerAdapter
import com.jesus.moviesskell.features.onboarding.viewModel.OnboardingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "OnboardingFragment"
class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {
    private lateinit var binding: FragmentOnboardingBinding
    private val numberOfPages by lazy { OnboardingScene.values().size }
    private val viewModel by viewModel<OnboardingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        this.setUpView(binding = binding)
        this.onEventChange()
        return binding.root
    }

    private fun setUpView(binding: FragmentOnboardingBinding) {
        this.onTapEvents(binding = binding)
        
        with(binding.slider) {
            adapter = OnBoardingPagerAdapter()
            addSlideChangeListener(binding = binding)
            val wormDotsIndicator = binding.pageIndicator
            wormDotsIndicator.attachTo(this)
        }
    }

    private fun onEventChange() {
        this.viewModel.onboardingFinished.observe(viewLifecycleOwner) { isOnboardingFinished ->
            if (isOnboardingFinished) {
                this.showMovies()
            }
        }
    }

    private fun addSlideChangeListener(binding: FragmentOnboardingBinding) {

        binding.slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (numberOfPages > 1) {
                    val newProgress = (position + positionOffset) / (numberOfPages - 1)
                    binding.onboardingRoot.progress = newProgress
                }
            }
        })
    }

    private fun onTapEvents(binding: FragmentOnboardingBinding) {
        binding.nextButton.setOnClickListener {
            Log.i(TAG, "OnNextButtonTap")
            this.goToNextSlide(binding = binding)
        }

        binding.startButton.setOnClickListener {
            Log.i(TAG, "OnStartButtonTap")
            this.viewModel.disableOnboarding()
        }
    }

    private fun goToNextSlide(binding: FragmentOnboardingBinding) {
        val nextSlidePos: Int = binding.slider.currentItem.plus(1)
        binding.slider.setCurrentItem(nextSlidePos, true)
    }

    private fun showMovies() {
        this.findNavController().navigate(OnboardingFragmentDirections.goToMovies())
    }


}
