package me.xiaro.mtfsim.event

import me.xiaro.mtfsim.Simulation
import kotlin.math.max
import kotlin.math.roundToInt

class WeightBuilder(
    val simulation: Simulation,
    var weight: Int,
) {

    inline fun mulIf(value: Double, block: Simulation.() -> Boolean) {
        if (block.invoke(simulation)) times(value)
    }

    inline fun addIf(value: Int, block: Simulation.() -> Boolean) {
        if (block.invoke(simulation)) weight += value
    }

    inline fun add(block: Simulation.() -> Int) {
        plus(block.invoke(simulation))
    }

    inline fun sub(block: Simulation.() -> Int) {
        plus(-block.invoke(simulation))
    }

    inline fun mul(block: Simulation.() -> Double) {
        times(block.invoke(simulation))
    }

    operator fun plus(value: Int) {
        weight += value
    }

    operator fun times(value: Double) {
        weight = (weight * value).roundToInt()
    }

    fun build(): Int {
        return max(weight, 0)
    }
}