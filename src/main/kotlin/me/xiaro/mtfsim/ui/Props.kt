package me.xiaro.mtfsim.ui

import me.xiaro.mtfsim.Simulation
import react.PropsWithChildren

external interface PageProp : PropsWithChildren {
    var prevPage: () -> Unit
    var nextPage: () -> Unit
}

external interface PlayProp : PageProp {
    var simulation: Simulation
}