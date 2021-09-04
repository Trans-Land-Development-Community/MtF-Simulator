package me.xiaro.mtfsim.event

import me.xiaro.mtfsim.Simulation
import kotlin.math.max
import kotlin.math.roundToInt

class WeightBuilder(
    val simulation: Simulation,
    var weight: Int,
) {

    fun mulIf(value: Double, boolean: Boolean) {
        if (boolean) times(value)
    }

    fun addIf(value: Int, boolean: Boolean) {
        if (boolean) plus(value)
    }

    operator fun plus(value: Int) {
        weight += value
    }

    operator fun minus(value: Int) {
        weight += value
    }


    operator fun times(value: Int) {
        weight *= value
    }

    operator fun times(value: Double) {
        weight = (weight * value).roundToInt()
    }

    fun build(): Int {
        return max(weight, 0)
    }
}