package me.xiaro.mtfsim.trait

import me.xiaro.mtfsim.Simulation
import me.xiaro.mtfsim.attribute.AttributeMap
import me.xiaro.mtfsim.utils.BitSet

class Trait private constructor(
    val name: String,
    val id: Int,
    val rarity: Rarity,
    val description: String,
    private val excludeNames: List<String>,
    private val modifier: AttributeMap.(Simulation) -> Unit
) {
    private val excludes by lazy {
        BitSet().apply {
            excludeNames.mapTo(this) {
                TraitManager.getTrait(it).id
            }
        }
    }

    fun check(currentTraits: List<Trait>): Boolean {
        return currentTraits.none {
            excludes.contains(it.id)
        }
    }

    fun applyModifier(simulation: Simulation, attributeBuilder: AttributeMap) {
        modifier.invoke(attributeBuilder, simulation)
    }

    override fun toString(): String {
        return "$name($id)"
    }

    class Builder(private val name: String) {
        private val id = idCounter++
        private lateinit var rarity: Rarity
        private lateinit var description: String
        private val excludes = ArrayList<String>()
        private var modifier: AttributeMap.(Simulation) -> Unit = {}

        fun exclude(name: String) {
            excludes.add(name)
        }

        fun exclude(vararg names: String) {
            excludes.addAll(names)
        }

        fun rarity(value: Rarity) {
            rarity = value
        }

        fun desc(string: String) {
            description = string
        }

        fun modify(block: AttributeMap.(Simulation) -> Unit) {
            modifier = block
        }

        fun build(): Trait {
            return Trait(name, id, rarity, description, excludes, modifier)
        }

        private companion object {
            var idCounter = 0
        }
    }
}