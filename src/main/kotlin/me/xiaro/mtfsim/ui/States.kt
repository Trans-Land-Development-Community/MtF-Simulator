package me.xiaro.mtfsim.ui

import me.xiaro.mtfsim.attribute.AttributeMap
import react.State

external interface AppState : State {
    var page: App.Page
    var attributeMap: AttributeMap
}

external interface PlayState : State {
    var results: MutableList<String>
}

external interface AttribState : State {
    var points: Int
    var attributeMap: AttributeMap
}