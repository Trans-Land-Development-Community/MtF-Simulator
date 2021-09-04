package me.xiaro.mtfsim.attribute

enum class Attribute(val displayName: String, val startup: Boolean, val hidden: Boolean) {
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
}