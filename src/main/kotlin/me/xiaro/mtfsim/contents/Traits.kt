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

    "天残党" {
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

    "易胖体质" {
        rarity(Rarity.COMMON)
        desc("颜值更容易降低")
    }

    "平凡之人" {
        rarity(Rarity.COMMON)
        desc("无事发生的几率提高")
    }

    "独生子女" {
        rarity(Rarity.COMMON)
        desc("你没有兄弟姐妹")
    }


    "抖M" {
        rarity(Rarity.COMMON)
        desc("家境-2，快乐+2")
        modify {
            Attribute.ECONOMIC - 2
            Attribute.HAPPINESS + 2
        }
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

    "成人礼" {
        rarity(Rarity.COMMON)
        desc("18岁时快乐+2")
        modify {
            if (it.olderThan(18)) Attribute.HAPPINESS + 2
        }
    }

    "整容" {
        rarity(Rarity.COMMON)
        desc("家境>20时颜值+3")
        modify {
            if (Attribute.ECONOMIC > 20) Attribute.HAPPINESS + 2
        }
    }

    "网络巨魔" {
        rarity(Rarity.RARE)
        desc("快乐+3")
        modify {
            Attribute.HAPPINESS + 3
        }
    }

    "女大十八变" {
        rarity(Rarity.RARE)
        desc("18岁时颜值+4")
        modify {
            if (it.olderThan(18)) Attribute.BEAUTY + 4
        }
    }

    "形象管理" {
        rarity(Rarity.RARE)
        desc("24岁时颜值+4")
        modify {
            if (it.olderThan(24)) Attribute.BEAUTY + 4
        }
    }

    "成熟" {
        rarity(Rarity.RARE)
        desc("18岁时智力+4")
        modify {
            if (it.olderThan(18)) Attribute.IQ + 4
        }
    }

    "超进化" {
        rarity(Rarity.RARE)
        desc("所有属性>5时，所有属性+2")
        modify {
            if (Attribute.allAttributes.all { this[it] > 5 }) Attribute.allAttributes + 2
        }
    }

    "祖传药丸" {
        rarity(Rarity.RARE)
        desc("功能不明")
    }


    "怪异" {
        rarity(Rarity.EPIC)
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
        desc("家境+8，提升出柜成功率")
        modify {
            Attribute.ECONOMIC + 8
        }
    }

    "天赋党" {
        rarity(Rarity.EPIC)
        desc("颜值+8")
        modify {
            Attribute.BEAUTY + 8
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
        desc("所有属性+4")
        modify {
            Attribute.allAttributes + 4
        }
    }

    "天子" {
        rarity(Rarity.LEGENDARY)
        desc("会在未来称帝")
    }
}