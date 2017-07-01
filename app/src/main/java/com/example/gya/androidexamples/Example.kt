package com.example.gya.androidexamples

import android.content.Context
import com.example.gya.androidexamples.griddecoration.GridDecorationActivity
import com.example.gya.androidexamples.behaviornotify.BehaviorNotifyActivity

enum class Example(val description: String, val action: (Context) -> Unit) {
    BehaviorNotify("RecyclerViewのスクロールをBehaviorでうけとる", { BehaviorNotifyActivity.start(it) }),
    MultiRecycler("RecyclerViewを縦に2つ重ねてみる", { Unit }),
    FragmentShared("FragmentTransitionしたときViewをSharedElementでアニメーション", { Unit }),
    GridDecoration("GridLayoutManagerでViewHolder/SpanSizeごとにDecoration", { GridDecorationActivity.start(it) }),
}
