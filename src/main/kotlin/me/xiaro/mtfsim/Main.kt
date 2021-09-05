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
    val width = min(window.innerWidth, 480)
    val scale = (width - 240) / 240.0 * 8.0 + 8.0
    val rounded = round(scale * 10000.0) / 10000.0
    document.documentElement!!.attributes["style"]!!.value = "font-size: ${rounded}px;"
}