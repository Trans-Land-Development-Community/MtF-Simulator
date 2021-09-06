package me.xiaro.mtfsim

import kotlinx.browser.document
import kotlinx.browser.window
import me.xiaro.mtfsim.contents.initEvents
import me.xiaro.mtfsim.ui.App
import org.w3c.dom.get
import react.dom.render
import kotlin.math.min
import kotlin.math.round

private lateinit var simulation: Simulation

fun main() {
    initEvents()

    window.onload = {
        updateFontSize()

        render(document.getElementById("root")) {
            child(App::class) {}
        }
    }

    window.onresize = {
        updateFontSize()
    }
}

private fun updateFontSize() {
    val width = min(window.innerWidth, 640)
    val scale = (width - 320) / 320.0 * 12.0 + 12.0
    val rounded = round(scale * 10000.0) / 10000.0
    println()
    document.documentElement!!.attributes["style"]!!.value = "font-size: ${rounded}px;"
}