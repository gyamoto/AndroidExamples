package com.example.simplesharedelement

import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet

class SimpleSharedElementTransition : TransitionSet() {
    init {
        addTransition(ChangeBounds())
            .addTransition(ChangeTransform())
            .addTransition(ChangeImageTransform())
    }
}
