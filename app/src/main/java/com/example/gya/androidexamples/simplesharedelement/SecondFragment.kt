package com.example.gya.androidexamples.simplesharedelement

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gya.androidexamples.R
import com.example.gya.androidexamples.simplesharedelement.SimpleSharedElementActivity.Companion.TRANSITION_NAME
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            logo.transitionName = TRANSITION_NAME
            sharedElementEnterTransition = SimpleSharedElementTransition()
            sharedElementReturnTransition = SimpleSharedElementTransition()
        }
    }
}
