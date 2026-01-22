package com.xiaoyuanlv.pokedex.utils

import android.animation.ObjectAnimator
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar

fun ProgressBar.setProgressSmooth(
target: Int,
duration: Long = 800
) {
    val animator = ObjectAnimator.ofInt(this, "progress", progress, target)
    animator.duration = duration
    animator.interpolator = DecelerateInterpolator()
    animator.start()
}