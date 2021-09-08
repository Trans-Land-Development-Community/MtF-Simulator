package me.xiaro.mtfsim.attribute

import me.xiaro.mtfsim.utils.DisplayEnum

enum class Attribute(override val displayName: String, val startup: Boolean, val hidden: Boolean) : DisplayEnum {
    BEAUTY("颜值", true, false),
    IQ("智商", true, false),
    STRENGTH("体质", true, false),
    ECONOMIC("家境", true, false),
    HAPPINESS("快乐", false, false),
    HEALTH("生命", false, true),
    FEMININITY("女性化", false, true);

    override fun toString(): String {
        return displayName
    }

    companion object {
        val startUpAttributes by lazy {
            values().filter { it.startup }
        }

        val allAttributes by lazy {
            listOf(BEAUTY, IQ, STRENGTH, ECONOMIC, HAPPINESS)
        }
    }
}