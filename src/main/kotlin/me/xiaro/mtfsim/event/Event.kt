package me.xiaro.mtfsim.event

import me.xiaro.mtfsim.Simulation
import me.xiaro.mtfsim.attribute.AttributeMap

class Event private constructor(
    val id: Int,
    val name: String,
    val group: EventGroup,
    private val messages: ArrayList<(Simulation) -> String>,
    private val check: Simulation.() -> Boolean,
    private val weight: Simulation.() -> Int,
    private val modifier: AttributeMap.(Simulation) -> Unit,
    private val repeatable: Boolean
) {

    fun check(simulation: Simulation): Boolean {
        return (repeatable || !simulation.hasEvent(this))
            && group.check.invoke(simulation)
            && check.invoke(simulation)
    }

    fun getMessage(simulation: Simulation): String {
        return messages.random().invoke(simulation)
    }

    fun calcWeight(simulation: Simulation): Int {
        return weight.invoke(simulation)
    }

    fun applyModifier(simulation: Simulation, attributeMap: AttributeMap) {
        modifier.invoke(attributeMap, simulation)
    }

    override fun toString(): String {
        return "$name($id)"
    }

    class Builder(private val name: String) {
        private val id = idCounter++
        private lateinit var group: EventGroup
        private val description = ArrayList<(Simulation) -> String>()
        private var check: Simulation.() -> Boolean = { true }
        private lateinit var weight: Simulation.() -> Int
        private var modifier: AttributeMap.(Simulation) -> Unit = {}
        private var repeatable: Boolean = false

        fun group(group: EventGroup) {
            this.group = group
        }

        fun desc(string: String) {
            description.add { string }
        }

        fun desc(block: Simulation.() -> String) {
            description.add(block)
        }

        fun check(block: Simulation.() -> Boolean) {
            check = block
        }

        fun weight(value: Int) {
            weight = { value }
        }

        fun weight(block: Simulation.() -> Int) {
            weight = block
        }

        fun Simulation.buildWeight(baseWeight: Int, block: WeightBuilder.() -> Unit): Int {
            return WeightBuilder(this, baseWeight).apply(block).build()
        }

        fun modify(block: AttributeMap.(Simulation) -> Unit) {
            modifier = block
        }

        fun repeatable() {
            repeatable = true
        }

        fun build(): Event {
            require(description.isNotEmpty()) { "Missing description for event $name!" }
            return Event(id, name, group, description, check, weight, modifier, repeatable)
        }

        private companion object {
            var idCounter = 0
        }
    }
}