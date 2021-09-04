package me.xiaro.mtfsim

import me.xiaro.mtfsim.attribute.Attribute
import me.xiaro.mtfsim.attribute.AttributeMap
import me.xiaro.mtfsim.event.Event
import me.xiaro.mtfsim.event.EventGroup
import me.xiaro.mtfsim.event.EventManager
import me.xiaro.mtfsim.event.EventResult
import me.xiaro.mtfsim.trait.Trait
import me.xiaro.mtfsim.utils.BitSet
import kotlin.js.Date
import kotlin.random.Random

class Simulation(
    private val baseAttribute: AttributeMap
) {
    var age = -1

    val random = Random(Date.now().toLong())
    private val eventResults = ArrayList<EventResult>()
    private val eventSet = BitSet()
    private val eventGroups = BitSet()

    private val traits = ArrayList<Trait>()
    var attributes = baseAttribute; private set

    val dead: Boolean
        get() {
            val health = attributes[Attribute.HEALTH]

            return health == Int.MIN_VALUE
                || health + attributes[Attribute.STRENGTH] < 0
        }

    fun grow(): EventResult {
        age++

        val event = EventManager.getGrowEvent(this)
        val result = EventResult(age, event, event.getMessage(this))

        eventResults.add(result)
        eventSet.add(event.id)
        eventGroups.add(event.group.id)

        val map = AttributeMap(baseAttribute)
        eventResults.forEach {
            it.event.applyModifier(map)
        }
        traits.forEach {
            it.applyModifier(map)
        }
        attributes = map

        return result
    }

    fun hasGroup(name: String): Boolean {
        return hasGroup(EventManager.getGroup(name))
    }

    fun hasGroup(group: EventGroup): Boolean {
        return hasGroup(group.id)
    }

    fun hasGroup(id: Int): Boolean {
        return eventGroups.contains(id)
    }

    fun hasEvent(name: String): Boolean {
        return hasEvent(EventManager.getEvent(name))
    }

    fun hasEvent(event: Event): Boolean {
        return hasEvent(event.id)
    }

    fun hasEvent(id: Int): Boolean {
        return eventSet.contains(id)
    }

    fun atAge(age: Int): Boolean {
        return this.age == age
    }

    fun notAtAge(age: Int): Boolean {
        return this.age != age
    }

    fun youngerThan(age: Int): Boolean {
        return this.age <= age
    }

    fun olderThan(age: Int): Boolean {
        return this.age >= age
    }

    fun withinAge(range: IntRange): Boolean {
        return this.age in range
    }

    val Attribute.value: Int
        get() = attributes[this]

    operator fun Attribute.compareTo(value: Int): Int {
        return this.value.compareTo(value)
    }

    operator fun Int.minus(attribute: Attribute): Int {
        return this - attribute.value
    }

    operator fun Attribute.plus(value: Int): Int {
        return this.value + value
    }

    operator fun Attribute.minus(value: Int): Int {
        return this.value - value
    }

    operator fun Attribute.times(value: Int): Int {
        return this.value * value
    }

    operator fun Attribute.div(value: Int): Int {
        return this.value / value
    }

    operator fun Attribute.times(value: Double): Double {
        return this.value * value
    }

    operator fun Attribute.div(value: Double): Double {
        return this.value / value
    }
}