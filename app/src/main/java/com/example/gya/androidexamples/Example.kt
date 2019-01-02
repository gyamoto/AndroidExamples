package com.example.gya.androidexamples

import android.content.Context
import com.example.behaviornotify.BehaviorNotifyActivity
import com.example.coordinators.CoordinatorsActivity
import com.example.griddecoration.GridDecorationActivity
import com.example.multirecycler.MultiRecyclerActivity
import com.example.overscrolltodismiss.OverScrollToDismissListActivity
import com.example.simplesharedelement.SimpleSharedElementActivity
import com.example.viewmap.ViewMapActivity

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
    ViewMap("GoogleMa風UI ~Viewを添えて~", { ViewMapActivity.start(it) }),
    OverScrollToDismiss("OverScrollで画面を閉じる",
        { OverScrollToDismissListActivity.start(it) })
}
