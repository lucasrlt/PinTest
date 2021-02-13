package com.pindex.main.ui.blocks

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat
import com.pindex.main.R
import com.pindex.main.utils.MediaLoader

/**
 * Custom ImageView for the Borderless Image block.
 */
class BorderlessImageBlock @JvmOverloads constructor(
        imagePath: String?,
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle) {

    /**
     * Apply Borderless Image block styles to this ImageView.
     */
    init {
        // Adjust the image bounds to preserve the aspect ratio
        adjustViewBounds = true

        // Background colour to display while the image is being loaded
        setBackgroundColor(ResourcesCompat.getColor(resources, R.color.light_grey, null))

        // Load the image from Firebase
        MediaLoader.loadImageFromFirebase(imagePath!!, this)
    }
}
