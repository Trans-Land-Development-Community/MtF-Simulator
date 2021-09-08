package me.xiaro.mtfsim.trait

import me.xiaro.mtfsim.utils.MutableNonNullMap
import me.xiaro.mtfsim.utils.NonNullMap

object TraitManager {
    private val traits0 = ArrayList<Trait>()
    private val nameTraitMap = HashMap<String, Trait>()
    private val rarityTraitsMap0 = MutableNonNullMap<Rarity, MutableList<Trait>>(HashMap()) {
        mutableListOf()
    }

    val traits: List<Trait>
        get() = traits0

    @Suppress("UNCHECKED_CAST")
    val rarityTraitsMap: NonNullMap<Rarity, List<Trait>>
        get() = rarityTraitsMap0 as MutableNonNullMap<Rarity, List<Trait>>

    fun getTrait(name: String): Trait {
        return nameTraitMap[name] ?: throw IllegalArgumentException("Invalid trait name $name")
    }

    fun registerTrait(trait: Trait) {
        traits0.add(trait)
        nameTraitMap[trait.name] = trait
        rarityTraitsMap0[trait.rarity].add(trait)
    }
}