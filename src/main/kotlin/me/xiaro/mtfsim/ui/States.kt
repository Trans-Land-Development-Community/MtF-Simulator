package me.xiaro.mtfsim.ui

import react.PropsWithChildren
import react.State

external interface AppState : State {
    var page: EnumPage
}

external interface PlayState : State {
    var results: MutableList<String>
}