package me.xiaro.mtfsim.utils

open class NonNullMap<K, V : Any>(
    protected open val wrapped: Map<K, V>,
    protected open val defaultValueFunc: (K) -> V
) : Map<K, V> {
    override val size: Int
        get() = wrapped.size

    override fun containsKey(key: K): Boolean {
        return wrapped.containsKey(key)
    }

    override fun containsValue(value: V): Boolean {
        return wrapped.containsValue(value)
    }

    override fun get(key: K): V {
        return wrapped[key] ?: defaultValueFunc.invoke(key)
    }

    override fun isEmpty(): Boolean {
        return wrapped.isEmpty()
    }

    override val entries: Set<Map.Entry<K, V>>
        get() = wrapped.entries
    override val keys: Set<K>
        get() = wrapped.keys
    override val values: Collection<V>
        get() = wrapped.values

    override fun toString(): String {
        return wrapped.toString()
    }
}