package com.jesus.moviesskell.features.onboarding.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.RawRes
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.jesus.moviesskell.R
import com.jesus.moviesskell.domain.models.onboarding.OnboardingScene

class OnBoardingPagerAdapter(private val onBoardingPageList:Array<OnboardingScene> = OnboardingScene.values())
: RecyclerView.Adapter<PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PagerViewHolder {
        return LayoutInflater.from(parent.context).inflate(
            PagerViewHolder.LAYOUT_ITEM_VIEW, parent, false
        ).let { PagerViewHolder(it) }
    }

    override fun getItemCount() = onBoardingPageList.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(onBoardingPageList[position])
    }
}

class PagerViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

    private val titleTextView = itemView.findViewById<TextView>(R.id.titleText)
    private val descriptionTextView = itemView.findViewById<TextView>(R.id.descriptionText)
    private val animationView = itemView.findViewById<LottieAnimationView>(R.id.animationView)
    fun bind(onBoardingPage: OnboardingScene) {
        val res = root.context.resources
        titleTextView.text = res.getString(onBoardingPage.title)
        descriptionTextView.text = res.getString(onBoardingPage.description)
        animationView.setAnimation(onBoardingPage.animation)
        animationView.playAnimation()

    }

    companion object {
        @LayoutRes
        val LAYOUT_ITEM_VIEW = R.layout.onboarding_item
    }
}
