package me.xiaro.mtfsim.attribute

class AttributeMap private constructor(private val array: IntArray) : Map<Attribute, Int> {
    constructor(): this(IntArray(Attribute.values().size))

    constructor(attributeMap: AttributeMap): this(attributeMap.array.copyOf())

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

    operator fun Attribute.plus(value: Int) {
        add(this, value)
    }

    operator fun Attribute.minus(value: Int) {
        add(this, -value)
    }

    infix fun Attribute.set(value: Int) {
        array[this.ordinal] = value
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

    private companion object {
        val keySet = WrappedSet(Attribute.values())
    }
}