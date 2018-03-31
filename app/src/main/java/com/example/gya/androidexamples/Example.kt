package com.example.gya.androidexamples

import android.content.Context
import com.example.gya.androidexamples.behaviornotify.BehaviorNotifyActivity
import com.example.gya.androidexamples.griddecoration.GridDecorationActivity
import com.example.gya.androidexamples.multirecycler.MultiRecyclerActivity
import com.example.gya.androidexamples.simplesharedelement.SimpleSharedElementActivity

enum class Example(val description: String, val action: (Context) -> Unit) {
    BehaviorNotify("RecyclerViewのスクロールをBehaviorでうけとる",
            { BehaviorNotifyActivity.start(it) }),
    MultiRecycler("RecyclerViewを縦に2つ重ねてみる",
            { MultiRecyclerActivity.start(it) }),
    GridDecoration("ViewHolder/SpanSizeごとにDecorationを切り替える",
            { GridDecorationActivity.start(it) }),
    SimpleSharedElement("FragmentTransitionしたときViewをSharedElementでアニメーション",
            { SimpleSharedElementActivity.start(it) }),
}
