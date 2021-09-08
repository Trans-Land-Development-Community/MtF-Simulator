package me.xiaro.mtfsim.trait

import me.xiaro.mtfsim.Simulation
import me.xiaro.mtfsim.attribute.AttributeMap

class Trait private constructor(
    val name: String,
    val id: Int,
    val description: String,
    private val modifier: AttributeMap.(Simulation) -> Unit
) {
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
        private var modifier: AttributeMap.(Simulation) -> Unit = {}

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
            return Trait(name, id, description, modifier)
        }

        private companion object {
            var idCounter = 0
        }
    }
}