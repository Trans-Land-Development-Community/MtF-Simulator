package me.xiaro.mtfsim.event

import me.xiaro.mtfsim.Simulation

object EventManager {
    private val groups0 = ArrayList<EventGroup>()
    private val events0 = ArrayList<Event>()

    private val nameGroupMap = HashMap<String, EventGroup>()
    private val nameEventMap = HashMap<String, Event>()

    val group: List<EventGroup>
        get() = groups0

    val events: List<Event>
        get() = events0

    fun getGroup(name: String): EventGroup {
        return nameGroupMap[name] ?: throw IllegalArgumentException("Invalid event group name $name")
    }

    fun getEvent(name: String): Event {
        return nameEventMap[name] ?: throw IllegalArgumentException("Invalid event name $name")
    }

    fun getGrowEvent(simulation: Simulation): Event {
        val filtered = arrayListOf(events0[0])

        if (simulation.atAge(0)) {
            events0.filterTo(filtered) {
                it.group.name == "出生" && it.check(simulation)
            }
        } else {
            events0.filterTo(filtered) {
                it.check(simulation)
            }
        }

        val mapped = filtered.map {
            it.calcWeight(simulation).toLong() to it
        }

        val sumOfWeight = mapped.sumOf { it.first }
        var random = simulation.random.nextLong(sumOfWeight)

        var event: Event? = null

        for ((weight, e) in mapped) {
            when {
                weight == 9999L -> return e
                event == null && random < weight -> event = e
                else -> random -= weight
            }
        }

        return event!!
    }

    fun registerGroup(group: EventGroup) {
        groups0.add(group)
        nameGroupMap[group.name] = group
    }

    fun registerEvent(event: Event) {
        events0.add(event)
        nameEventMap[event.name] = event
    }
}