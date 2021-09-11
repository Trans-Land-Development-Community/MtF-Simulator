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

    fun getRandomTraits(size: Int, random: Random): List<Trait> {
        val set = BitSet()
        val list = ArrayList<Trait>()

        while (list.size < size) {
            var rarity = Rarity.COMMON
            var randomNumber = random.nextInt(100)

            for (r in Rarity.values()) {
                when {
                    randomNumber < r.weight -> {
                        rarity = r
                        break
                    }
                    else -> {
                        randomNumber -= r.weight
                    }
                }
            }

            val filteredTraits = rarityTraitsMap[rarity].filter {
                !set.contains(it.id) && it.check(list)
            }

            val trait = filteredTraits.randomOrNull(random) ?: continue
            set.add(trait.id)
            list.add(trait)
        }

        return list.toList()
    }

    fun getTrait(name: String): Trait {
        return nameTraitMap[name] ?: throw IllegalArgumentException("Invalid trait name $name")
    }

    fun registerTrait(trait: Trait) {
        nameTraitMap.put(trait.name, trait)?.let {
            console.error("Name ${trait.name} already register with $it")
            nameTraitMap[it.name] = it
            return
        }
        rarityTraitsMap[trait.rarity].add(trait)
        traits0.add(trait)
    }
}