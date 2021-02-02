package com.pindex.main.ui.blocks

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.pindex.main.R

// Custom TextView for the Text block
class TextBlock @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {

    // Big header style
    init {
        typeface = ResourcesCompat.getFont(context, R.font.montserrat_light)
        textSize = 16f
        setTextColor(Color.LTGRAY)
    }

}
