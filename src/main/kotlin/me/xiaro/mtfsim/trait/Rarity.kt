package me.xiaro.mtfsim.trait

import me.xiaro.mtfsim.utils.DisplayEnum

enum class Rarity(override val displayName: String, val weight: Int) : DisplayEnum {
    COMMON("普通", 75),
    RARE("稀有", 18),
    EPIC("史诗", 4),
    LEGENDARY("传说", 1)
}