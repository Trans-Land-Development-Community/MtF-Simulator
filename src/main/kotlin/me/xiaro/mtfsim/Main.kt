package me.xiaro.mtfsim

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.dom.clear
import kotlinx.html.div
import kotlinx.html.dom.append
import me.xiaro.mtfsim.attribute.Attribute
import me.xiaro.mtfsim.attribute.AttributeMap
import me.xiaro.mtfsim.contents.initEvents
import me.xiaro.mtfsim.event.EventManager
import org.w3c.dom.Node

private lateinit var simulation: Simulation

fun main() {
    initEvents()
    console.info("${EventManager.events.size} events initialized")
    println(EventManager.events)
    println(EventManager.group)

    simulation = newSimulation()

    window.onclick = {
        try {
            document.body?.grow()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun newSimulation(): Simulation {
    return Simulation(
        AttributeMap().apply {
            Attribute.BEAUTY set 20
            Attribute.IQ set 20
            Attribute.STRENGTH set 20
            Attribute.ECONOMIC set 20

            Attribute.HAPPINESS set 10
            Attribute.HEALTH set 10
            Attribute.FEMININITY set 0
        }
    )
}

fun Node.grow() {
    println("Clicked!")

    if (simulation.dead) {
        println("Restarted!")
        document.body?.clear()
        simulation = newSimulation()
    }

    val result = simulation.grow()

    append {
        div {
            + "${simulation.age}岁: ${result.message}"
        }
    }

    println(simulation.attributes)
}