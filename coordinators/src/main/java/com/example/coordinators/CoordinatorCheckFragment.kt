package com.example.coordinators

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf

class CoordinatorCheckFragment : Fragment() {

    private val layoutId by lazy { arguments?.getInt(KEY_LAYOUT) ?: throw IllegalStateException() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    companion object {

        private const val KEY_LAYOUT = "layout"

        fun newInstance(@LayoutRes layout: Int): CoordinatorCheckFragment {
            return CoordinatorCheckFragment().apply {
                arguments = bundleOf(KEY_LAYOUT to layout)
            }
        }
    }
}
