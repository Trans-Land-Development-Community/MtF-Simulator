package me.xiaro.mtfsim.contents

import me.xiaro.mtfsim.attribute.Attribute
import me.xiaro.mtfsim.trait.Rarity
import me.xiaro.mtfsim.trait.Trait
import me.xiaro.mtfsim.trait.TraitManager

fun initTraits() {
    operator fun String.invoke(block: Trait.Builder.() -> Unit) {
        TraitManager.registerTrait(Trait.Builder(this).apply(block).build())
    }

    "占位符" {
        rarity(Rarity.COMMON)
        desc("占用一个天赋位")
    }

    "平凡之人" {
        rarity(Rarity.COMMON)
        desc("什么事都不会发生的几率提高")
    }

    "农村人" {
        rarity(Rarity.COMMON)
        desc("出生在农村里")
    }

    "城里人" {
        rarity(Rarity.COMMON)
        desc("出生在城市里")
    }

    "怪异" {
        rarity(Rarity.RARE)
        desc("听说能用来实现愿望")
    }

    "神奇海螺" {
        rarity(Rarity.EPIC)
        desc("不如我们来问问神奇海螺吧")
    }

    "中奖党" {
        rarity(Rarity.EPIC)
        desc("出生必定真双性别")
    }

    "家长党" {
        rarity(Rarity.EPIC)
        desc("家境+5，提升出柜成功率")
        modify {
            Attribute.ECONOMIC + 5
        }
    }

    "天赋党" {
        rarity(Rarity.EPIC)
        desc("颜值+5")
        modify {
            Attribute.BEAUTY + 5
        }
    }

    "天子" {
        rarity(Rarity.LEGENDARY)
        desc("会在未来称帝")
    }
}