package com.example.gya.androidexamples

import android.content.Context
import com.example.gya.androidexamples.behaviornotify.BehaviorNotifyActivity
import com.example.gya.androidexamples.coordinators.CoordinatorsActivity
import com.example.gya.androidexamples.griddecoration.GridDecorationActivity
import com.example.gya.androidexamples.multirecycler.MultiRecyclerActivity
import com.example.gya.androidexamples.scrollablemap.CanvasMapActivity
import com.example.gya.androidexamples.scrollablemap.ViewMapActivity
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
    Coordinators("いろいろなCoordinatorLayoutのパターンを試してみる",
        { CoordinatorsActivity.start(it) }),
    CanvasMap("GoogleMa風UI ~Canvasを添えて~", { CanvasMapActivity.start(it) }),
    ViewMap("GoogleMa風UI ~Viewを添えて~", { ViewMapActivity.start(it) }),
}
