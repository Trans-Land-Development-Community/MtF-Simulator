package me.xiaro.mtfsim.ui

import me.xiaro.mtfsim.attribute.AttributeMap
import me.xiaro.mtfsim.trait.Trait
import me.xiaro.mtfsim.utils.MutableNonNullMap
import react.State

external interface AppState : State {
    var page: App.Page
    var traits: List<Trait>
    var attributeMap: AttributeMap
}

external interface PlayState : State {
    var results: MutableList<String>
}

external interface TraitState : State {
    var points: Int
    var traitMap: MutableNonNullMap<Trait, Boolean>
}

external interface AttribState : State {
    var points: Int
    var attributeMap: AttributeMap
}