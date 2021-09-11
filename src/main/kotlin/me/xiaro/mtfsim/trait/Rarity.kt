package me.xiaro.mtfsim.trait

import kotlinx.css.Color
import me.xiaro.mtfsim.utils.DisplayEnum

enum class Rarity(override val displayName: String, val weight: Int, val color: Color) : DisplayEnum {
    COMMON("普通", 87, Color("#fafafa")),
    RARE("稀有", 10, Color("#5bcffa")),
    EPIC("史诗", 2, Color("#f5abe9")),
    LEGENDARY("传说", 1, Color("#fd8918"))
}