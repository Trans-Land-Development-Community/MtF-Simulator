package me.xiaro.mtfsim.event

import me.xiaro.mtfsim.Simulation

class EventGroup private constructor(
    val name: String,
    val id: Int,
    val check: (Simulation) -> Boolean,
    val events: List<Event>
) {
    override fun toString(): String {
        return "$name($id)"
    }

    class Builder(private val name: String) {
        private val id = idCounter++
        private var check: (Simulation) -> Boolean = { true }
        private val eventBuilders = ArrayList<Event.Builder>()

        operator fun String.invoke(block: Event.Builder.() -> Unit) {
            eventBuilders.add(Event.Builder(this).apply(block))
        }

        fun check(block: Simulation.() -> Boolean) {
            check = block
        }

        fun Simulation.exclude(): Boolean {
            return !this.hasGroup(id)
        }

        fun Simulation.include(): Boolean {
            return this.hasGroup(id)
        }

        fun build(): EventGroup {
            val list = ArrayList<Event>()
            val group = EventGroup(name, id, check, list)

            eventBuilders.mapTo(list) {
                it.group(group)
                it.build()
            }

            return group
        }

        private companion object {
            var idCounter = 0
        }
    }
}