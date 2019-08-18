package com.example.simplesharedelement

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.core.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.simplesharedelement.SimpleSharedElementActivity.Companion.TRANSITION_NAME
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        ViewCompat.setTransitionName(logo, TRANSITION_NAME)

        Handler().postDelayed({
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.addSharedElement(logo, TRANSITION_NAME)
                ?.replace(R.id.container, SecondFragment())
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ?.commit()
        }, 1000)
    }
}
