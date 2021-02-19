package com.pindex.main.ui.fragments.blocks

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.pindex.main.R

/**
 * Custom Fragment for the Text block.
 */
class TextBlockFragment : Fragment(R.layout.fragment_pindex_block_text) {

    private lateinit var textView: AppCompatTextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView = view.findViewById(R.id.pindex_block_text)

        // Set the text
        textView.text = arguments?.getString("text")
    }

}
