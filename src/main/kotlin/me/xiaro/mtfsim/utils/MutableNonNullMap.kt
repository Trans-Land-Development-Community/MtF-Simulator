package me.xiaro.mtfsim.utils

class MutableNonNullMap<K, V : Any>(
    override val wrapped: MutableMap<K, V>,
    override val defaultValueFunc: (K) -> V
) : NonNullMap<K, V>(wrapped, defaultValueFunc), MutableMap<K, V> {
    override val size: Int
        get() = wrapped.size

    override fun containsKey(key: K): Boolean {
        return wrapped.containsKey(key)
    }

    override fun containsValue(value: V): Boolean {
        return wrapped.containsValue(value)
    }

    override fun get(key: K): V {
        return wrapped.getOrPut(key) {
            defaultValueFunc.invoke(key)
        }
    }

    override fun isEmpty(): Boolean {
        return wrapped.isEmpty()
    }

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = wrapped.entries
    override val keys: MutableSet<K>
        get() = wrapped.keys
    override val values: MutableCollection<V>
        get() = wrapped.values

    override fun clear() {
        wrapped.clear()
    }

    override fun put(key: K, value: V): V {
        return wrapped.put(key, value) ?: defaultValueFunc.invoke(key)
    }

    override fun putAll(from: Map<out K, V>) {
        wrapped.putAll(from)
    }

    override fun remove(key: K): V {
        return wrapped.remove(key) ?: defaultValueFunc.invoke(key)
    }
}