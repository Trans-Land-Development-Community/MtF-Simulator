package me.xiaro.mtfsim.attribute

class AttributeMap private constructor(private val array: IntArray) : Map<Attribute, Int> {
    constructor() : this(IntArray(Attribute.values().size))

    constructor(attributeMap: AttributeMap) : this(attributeMap.array.copyOf())

    override val size: Int
        get() = array.size

    override val entries: Set<Map.Entry<Attribute, Int>>
        get() = WrappedSet(
            Array(array.size) {
                Entry(Attribute.values()[it], array[it])
            }
        )
    override val keys: Set<Attribute>
        get() = keySet
    override val values: Collection<Int>
        get() = array.toList()

    val startupValues: IntArray
        get() = array.copyOf(4)

    operator fun Collection<Attribute>.plus(value: Int) {
        this.forEach {
            add(it, value)
        }
    }

    operator fun Collection<Attribute>.minus(value: Int) {
        this.forEach {
            add(it, -value)
        }
    }

    operator fun Attribute.plus(value: Int) {
        add(this, value)
    }

    operator fun Attribute.minus(value: Int) {
        add(this, -value)
    }

    operator fun set(attribute: Attribute, value: Int) {
        array[attribute.ordinal] = value
    }

    private fun add(type: Attribute, value: Int) {
        array[type.ordinal] = array[type.ordinal] + value
    }

    override fun isEmpty(): Boolean {
        return false
    }

    override fun containsKey(key: Attribute): Boolean {
        return true
    }

    override fun containsValue(value: Int): Boolean {
        throw UnsupportedOperationException()
    }

    override fun get(key: Attribute): Int {
        return array[key.ordinal]
    }

    override fun toString(): String {
        return entries.joinToString()
    }

    private class WrappedSet<T>(private val array: Array<T>) : Set<T> {
        override val size: Int
            get() = array.size

        override fun contains(element: T): Boolean {
            return array.contains(element)
        }

        override fun containsAll(elements: Collection<T>): Boolean {
            return elements.all {
                array.contains(it)
            }
        }

        override fun isEmpty(): Boolean {
            return array.isEmpty()
        }

        override fun iterator(): Iterator<T> {
            return array.iterator()
        }
    }

    private class Entry(
        override val key: Attribute,
        override val value: Int
    ) : Map.Entry<Attribute, Int> {
        override fun toString(): String {
            return "$key=$value"
        }
    }

    val Attribute.value: Int
        get() = this@AttributeMap[this]

    operator fun Attribute.compareTo(value: Int): Int {
        return this.value.compareTo(value)
    }

    operator fun Int.minus(attribute: Attribute): Int {
        return this - attribute.value
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

    companion object {
        private val keySet = WrappedSet(Attribute.values())

        fun random(points: Int = 40): AttributeMap {
            var pointsLeft = points
            val attributeMap = default()

            while (pointsLeft > 0) {
                val attribute = Attribute.startUpAttributes.random()
                val prev = attributeMap[attribute]
                if (prev >= 20) continue
                attributeMap[attribute] = prev + 2
                pointsLeft -= 2
            }

            return attributeMap
        }

        fun default(): AttributeMap = AttributeMap().apply {
            set(Attribute.BEAUTY, 0)
            set(Attribute.IQ, 0)
            set(Attribute.STRENGTH, 0)
            set(Attribute.ECONOMIC, 0)

            set(Attribute.HAPPINESS, 10)
            set(Attribute.HEALTH, 10)
            set(Attribute.FEMININITY, 0)
        }

    }
}