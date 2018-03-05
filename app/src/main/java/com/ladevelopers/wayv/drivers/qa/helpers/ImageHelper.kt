package com.ladevelopers.wayv.drivers.qa.helpers

import android.content.Context

class ImageHelper {

    companion object {
        fun getImageUrl(imageId: String): String? =
                if (imageId.isEmpty())
                    null
                else
                    "https://res.cloudinary.com/newco/iu/q_auto/$imageId"

        fun getThumbUrl(imageId: String, width: Int, height: Int): String? =
                if (imageId.isEmpty()) null
                else "https://res.cloudinary.com/newco/iu/c_fit,h_$height,q_auto,w_$width/$imageId"

        fun getThumbUrl(imageId: String, width: Int, height: Int, context: Context): String? {
            val density = context.resources.displayMetrics.density
            return getThumbUrl(imageId, (width * density).toInt(), (height * density).toInt())
        }
    }
}