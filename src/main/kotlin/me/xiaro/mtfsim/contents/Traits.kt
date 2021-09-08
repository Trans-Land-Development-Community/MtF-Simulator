package me.xiaro.mtfsim.contents

import me.xiaro.mtfsim.attribute.Attribute
import me.xiaro.mtfsim.trait.Rarity
import me.xiaro.mtfsim.trait.Trait
import me.xiaro.mtfsim.trait.TraitManager

private operator fun String.invoke(block: Trait.Builder.() -> Unit) {
    TraitManager.registerTrait(Trait.Builder(this).apply(block).build())
}

fun initTraits() {
    // Debuff
    "天生抑郁" {
        rarity(Rarity.COMMON)
        desc("快乐-3")
        modify {
            Attribute.HAPPINESS - 3
        }
    }

    "痘痘脸" {
        rarity(Rarity.COMMON)
        desc("颜值-2")
        modify {
            Attribute.BEAUTY - 2
        }
    }

    "早产儿" {
        rarity(Rarity.COMMON)
        desc("所有属性-1")
        modify {
            Attribute.allAttributes - 1
        }
    }

    "天生残疾" {
        rarity(Rarity.COMMON)
        desc("体质-1")
        modify {
            Attribute.STRENGTH - 1
        }
    }

    "占位符" {
        rarity(Rarity.COMMON)
        desc("占用一个天赋位")
    }

    "平凡之人" {
        rarity(Rarity.COMMON)
        desc("无事发生的几率提高")
    }


    "农村人" {
        rarity(Rarity.COMMON)
        desc("出生在农村里")
        exclude("城里人")
    }

    "城里人" {
        rarity(Rarity.COMMON)
        desc("出生在城市里")
        exclude("农村人")
    }

    "怪异" {
        rarity(Rarity.RARE)
        desc("听说能用来实现愿望")
    }

    "网络巨魔" {
        rarity(Rarity.RARE)
        desc("快乐+3")
        modify {
            Attribute.HAPPINESS + 3
        }
    }

    "基因改良" {
        rarity(Rarity.RARE)
        desc("所有属性+1")
        modify {
            Attribute.allAttributes + 1
        }
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

    "基因飞升一段" {
        rarity(Rarity.EPIC)
        desc("所有属性+2")
        modify {
            Attribute.allAttributes + 2
        }
    }

    "基因飞升二段" {
        rarity(Rarity.LEGENDARY)
        desc("所有属性+3")
        modify {
            Attribute.allAttributes + 3
        }
    }

    "天子" {
        rarity(Rarity.LEGENDARY)
        desc("会在未来称帝")
    }
}