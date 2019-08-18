package com.example.coordinators

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.plusAssign
import kotlinx.android.synthetic.main.fragment_cooridnator_list.*

class CoordinatorListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cooridnator_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        COORDINATORS.map {
            val (name, layout) = it

            layoutInflater.inflate(R.layout.view_coordinator_item_text, list, false)
                .apply {
                    (this as? TextView)?.let {
                        it.text = name
                        it.setOnClickListener { checkCoordinator(layout) }
                    }
                }
        }.forEach {
            list += it
        }

    }

    private fun checkCoordinator(@LayoutRes layoutId: Int) {
        fragmentManager?.beginTransaction()
            ?.replace(
                R.id.container,
                CoordinatorCheckFragment.newInstance(layoutId)
            )
            ?.addToBackStack(null)
            ?.commit()
    }

    companion object {

        private val COORDINATORS: List<Pair<String, Int>> = listOf(
            "Toolbar" to R.layout.fragment_coordinator_collapse_toolbar,
            "Long Toolbar" to R.layout.fragment_coordinator_collapse_long_toolbar,
            "Tabs" to R.layout.fragment_coordinator_collapse_tabs
        )

        fun newInstance() = CoordinatorListFragment()
    }
}
