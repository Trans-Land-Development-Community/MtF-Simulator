package me.xiaro.mtfsim.trait

import me.xiaro.mtfsim.attribute.AttributeMap

class Trait private constructor(
    val name: String,
    val id: Int,
    val description: String,
    private val modifier: (AttributeMap) -> Unit
) {
    fun applyModifier(attributeBuilder: AttributeMap) {
        modifier.invoke(attributeBuilder)
    }

    override fun toString(): String {
        return "$name($id)"
    }

    class Builder(private val name: String) {
        private val id = idCounter++
        private lateinit var description: String
        private var modifier: (AttributeMap) -> Unit = {}

        fun modify(block: AttributeMap.() -> Unit) {
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