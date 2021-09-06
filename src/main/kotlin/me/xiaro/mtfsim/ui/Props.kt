package me.xiaro.mtfsim.ui

import me.xiaro.mtfsim.Simulation
import me.xiaro.mtfsim.attribute.AttributeMap
import react.Props

external interface PageProp : Props {
    var prevPage: () -> Unit
    var nextPage: () -> Unit
}

external interface AttribProp : Props {
    var onExit: () -> Unit
    var onStart: (AttributeMap) -> Unit
}

external interface PlayProp : PageProp {
    var simulation: Simulation
}