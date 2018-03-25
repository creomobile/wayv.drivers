package com.ladevelopers.wayv.drivers.qa.helpers

import android.databinding.BindingAdapter
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.ViewSwitcher
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ladevelopers.wayv.drivers.qa.infrastructure.GlideApp

@BindingAdapter("android:showFirstView")
fun setShowFirstView(viewSwitcher: ViewSwitcher, showFirstView: Boolean?) {
    viewSwitcher.displayedChild = if (showFirstView == false) 1 else 0
}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context).load(imageUrl).into(view)
}

@BindingAdapter("android:imageId")
fun loadImageId(view: ImageView, imageId: String) {
    val vto = view.viewTreeObserver
    view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            if (vto.isAlive) vto.removeOnPreDrawListener(this)
            val context = view.context
            val url = ImageHelper.getThumbUrl(imageId, view.width, view.height)
            GlideApp
                    .with(context)
                    .load(url)
                    .apply(RequestOptions.circleCropTransform())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view)
            return true
        }
    })
}
