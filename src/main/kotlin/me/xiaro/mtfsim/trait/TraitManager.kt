package me.xiaro.mtfsim.trait

import me.xiaro.mtfsim.utils.BitSet
import me.xiaro.mtfsim.utils.MutableNonNullMap
import kotlin.random.Random

object TraitManager {
    private val traits0 = ArrayList<Trait>()
    private val nameTraitMap = HashMap<String, Trait>()
    private val rarityTraitsMap = MutableNonNullMap<Rarity, MutableList<Trait>>(HashMap()) {
        mutableListOf()
    }

    val traits: List<Trait>
        get() = traits0

    fun getRandomTraits(size: Int): List<Trait> {
        val set = BitSet()
        val list = ArrayList<Trait>()

        while (list.size < 10) {
            var rarity = Rarity.COMMON
            var random = Random.nextInt(100)

            for (r in Rarity.values()) {
                when {
                    random < r.weight -> {
                        rarity = r
                        break
                    }
                    else -> {
                        random -= r.weight
                    }
                }
            }

            val trait = rarityTraitsMap[rarity].random()
            if (set.contains(trait.id)) continue
            if (!trait.check(list)) continue

            set.add(trait.id)
            list.add(trait)
        }

        return list.toList()
    }

    fun getTrait(name: String): Trait {
        return nameTraitMap[name] ?: throw IllegalArgumentException("Invalid trait name $name")
    }

    fun registerTrait(trait: Trait) {
        traits0.add(trait)
        nameTraitMap[trait.name] = trait
        rarityTraitsMap[trait.rarity].add(trait)
    }
}