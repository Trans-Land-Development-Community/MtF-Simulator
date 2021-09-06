package me.xiaro.mtfsim.contents

import me.xiaro.mtfsim.attribute.Attribute
import me.xiaro.mtfsim.event.EventGroup
import me.xiaro.mtfsim.event.EventManager

private operator fun String.invoke(block: EventGroup.Builder.() -> Unit) {
    val group = EventGroup.Builder(this).apply(block).build()

    group.events.forEach {
        EventManager.registerEvent(it)
    }

    EventManager.registerGroup(group)
}

fun initEvents() {
    "通用" {
        "空白" {
            desc("什么都没有发生。")
            weight(1)
            repeatable()
        }
        "死亡" {
            desc { "你死了，享年${age}岁。" }
            weight {
                buildWeight(-400) {
                    plus(age * 10 - Attribute.STRENGTH * 25)
                }
            }
            modify {
                set(Attribute.HEALTH, Int.MIN_VALUE)
            }
        }
    }

    // 出生
    "出生" {
        check { exclude() && atAge(0) }

        "出生_男" {
            desc("你出生了。")
            weight(1000)
        }
        "出生_XXY" {
            desc("你出生了。")
            weight(50)
        }
        "出生_XXXY" {
            desc("你出生了。")
            weight(25)
        }
        "出生_真双性" {
            desc("你出生了。")
            weight(5)
        }
    }

    "真双性" {
        check { hasEvent("出生_真双性") }

        "发现真双性" {
            desc("体检发现自己是真双性人")
            check { olderThan(3) }
            weight(100)
        }
    }

    "兄弟姐妹" {
        check { exclude() && atAge(1) }

        "独生子女" {
            desc("你是父母唯一一个孩子。")
            weight(2000)
        }
        "哥哥" {
            desc("你有一个哥哥。")
            weight(400)
        }
        "姐姐" {
            desc("你有一个姐姐。")
            weight(500)
        }
    }

    // 童年
    "童年" {
        check { youngerThan(5) }

        "生儿育女" {
            desc("父母想要个女孩子，于是把你当成女孩子养了")
            check { youngerThan(3) }
            weight(50)
            modify {
                Attribute.FEMININITY + 4
            }
        }
        "顽皮" {
            desc("顽皮的你经常打坏东西。")
            check { !hasEvent("乖巧") }
            weight {
                buildWeight(300) {
                    minus(Attribute.FEMININITY * 50)
                }
            }
            modify {
                Attribute.FEMININITY - 2
                Attribute.ECONOMIC - 1
                Attribute.STRENGTH + 1
            }
        }
        "乖巧" {
            desc("别人都夸你安静乖巧。")
            check { !hasEvent("顽皮") }
            weight {
                buildWeight(200) {
                    addIf(20, hasEvent("生儿育女"))
                }
            }
            modify {
                Attribute.FEMININITY + 1
            }
        }
        "积木" {
            desc("你喜欢玩积木。")
            weight {
                buildWeight(150) {
                    addIf(20, hasEvent("哥哥"))
                    addIf(-120, hasEvent("生儿育女"))
                }
            }
            modify {
                Attribute.IQ + 1
            }
        }
        "洋娃娃" {
            desc("你喜欢玩洋娃娃。")
            weight {
                buildWeight(80) {
                    addIf(-75, hasEvent("积木"))
                    addIf(20, hasEvent("姐姐"))
                    addIf(20, hasEvent("乖巧"))
                    addIf(100, hasEvent("生儿育女"))
                }
            }
            modify {
                Attribute.FEMININITY + 3
            }
        }
    }

    "哥哥" {
        check { include() || hasEvent("哥哥") }

        "马里魔赛车" {
            desc("哥哥经常和你玩一个叫马里魔赛车的游戏，该游戏亦可赛艇。")
            check { withinAge(5..12) }
            weight(1000)
        }
        "空之缘" {
            desc("你偷偷看到了哥哥在看一部叫《空之缘》的动漫")
            check { withinAge(8..16) && Attribute.FEMININITY > 1 }
            weight {
                buildWeight(100) {
                    addIf(50, hasEvent("二次元"))
                }
            }
        }
        "哥哥的眼神" {
            desc("哥哥有一天用着和平时不太一样的眼神看着你")
            check { withinAge(8..16) && hasEvent("空之缘") && Attribute.FEMININITY > 8 && Attribute.BEAUTY > 12 }
            weight(100)
        }
    }

    "姐姐" {
        check { include() || hasEvent("姐姐") }

        "套裙子" {
            desc("姐姐给你穿上了裙子，你很喜欢。")
            check { withinAge(5..9) }
            weight(100)
            modify {
                Attribute.FEMININITY + 2
            }
        }
        "奇迹冷冷" {
            desc("你发现姐姐经常玩一个叫“奇迹冷冷”的游戏。")
            check { withinAge(5..18) }
            weight(200)
        }
        "换装游戏" {
            desc("姐姐经常和你玩换装游戏，给你穿各种衣服。")
            check { youngerThan(18) && hasEvent("奇迹冷冷") }
            weight(300)
            modify {
                Attribute.FEMININITY + 3
            }
        }
        "姐姐教化妆" {
            desc("姐姐手把手教你化妆。")
            check { olderThan(12) }
            weight {
                buildWeight(200) {
                    addIf(100, hasEvent("换装游戏"))
                }
            }
            modify {
                Attribute.BEAUTY + 1
            }
        }
    }

    // 发育
    "发育" {
        check { exclude() }

        "发育_缓慢" {
            desc("体检的时候医生康了一下你的身高，发现你发育不太正常。")
            check { withinAge(8..12) }
            weight(250)
            modify {
                Attribute.STRENGTH - 1
            }
        }
        "发育_一般" {
            desc("体检的时候医生康你发育很正常。")
            check { withinAge(8..12) }
            weight(400)
        }
        "发育_优秀" {
            desc("体检的时候医生发现你身体发育得很好。")
            check { withinAge(8..12) }
            weight(200)
            modify {
                Attribute.STRENGTH + 1
            }
        }
    }

    // 小学
    "小学" {
        check { exclude() }

        "普通小学" {
            desc("你上了个普通小学。")
            check { olderThan(5) }
            weight {
                buildWeight(2000) {
                    times((age - 5) * 0.5 + 1.0)
                }
            }
            modify {
                Attribute.IQ + 1
            }
        }
        "贵族小学" {
            desc("你上了个贵族小学。")
            check { olderThan(5) && Attribute.ECONOMIC > 12 }
            weight {
                buildWeight(500) {
                    plus((Attribute.ECONOMIC - 12) * 200)
                    times((age - 5) * 0.5 + 1.0)
                }
            }
            modify {
                Attribute.IQ + 2
            }
        }
    }

    "初中" {
        check { exclude() && hasGroup("小学") }

        "普通初中" {
            desc("你上了个普通初中。")
            check { olderThan(11) }
            weight {
                buildWeight(2000) {
                    times((age - 11) * 0.5 + 1.0)
                }
            }
            modify {
                Attribute.IQ + 1
            }
        }
        "贵族初中" {
            desc("你上了个贵族初中。")
            check { olderThan(11) && Attribute.ECONOMIC > 14 }
            weight {
                buildWeight(500) {
                    plus((Attribute.ECONOMIC - 14) * 200)
                    addIf(400, hasEvent("贵族小学"))
                    times((age - 11) * 0.5 + 1.0)
                }
            }
            modify {
                Attribute.IQ + 2
            }
        }
    }

    "高中" {
        check { exclude() && olderThan(14) && hasGroup("初中") }

        "职高" {
            desc("中考落榜的你随便找了家职高。")
            check { Attribute.IQ < 10 }
            weight {
                buildWeight(1000) {
                    plus((10 - Attribute.IQ) * 250)
                }
            }
        }
        "普通高中" {
            desc("中考成绩一般般，上了个普通高中。")
            weight {
                buildWeight(2000) {
                    addIf(-500, Attribute.IQ > 16)
                    addIf(-500, Attribute.ECONOMIC > 16)
                }
            }
            modify {
                Attribute.IQ + 1
            }
        }
        "重点高中" {
            desc("中考发挥的很好，上了重点高中。")
            check { Attribute.IQ > 14 }
            weight {
                buildWeight(2000) {
                    addIf(200, hasEvent("贵族初中"))
                    plus((Attribute.IQ - 14) * 200)
                    times((age - 11) * 0.5 + 1.0)
                }
            }
            modify {
                Attribute.IQ + 2
            }
        }
        "贵族高中" {
            desc("家里砸钱让你上了个贵族高中。")
            check { Attribute.ECONOMIC > 16 }
            weight {
                buildWeight(2000) {
                    addIf(400, hasEvent("贵族初中"))
                    plus((Attribute.ECONOMIC - 16) * 200)
                    times((age - 11) * 0.5 + 1.0)
                }
            }
            modify {
                Attribute.IQ + 2
            }
        }
    }

    "大学" {
        check { exclude() && olderThan(17) && hasGroup("高中") && !hasEvent("职高") }

        "本科" {
            desc("高考成绩一般，读了个一般的本科。")
            check { Attribute.IQ < 10 }
            weight(2000)
        }
        "重本" {
            desc("高考发挥不错，过了重本线。")
            check { Attribute.IQ > 18 }
            weight {
                buildWeight(2000) {
                    plus((Attribute.IQ - 18) * 200)
                }
            }
        }
        "985" {
            desc("高考成功，考上了家985。")
            check { Attribute.IQ > 20 }
            weight {
                buildWeight(2000) {
                    plus((Attribute.IQ - 20) * 400)
                }
            }
        }
    }

    "青年" {
        "二次元" {
            desc("自从被同学安利了之后，你爱上了二次元。")
            desc("你沉迷于二次元，天天看番。")
            desc("你沉迷于二次元，天天看漫画。")
            check { withinAge(10..30) }
            weight(100)
        }
        "生日礼物_游戏机" {
            desc("过生日收到了父母送的新款游戏机Xbox420")
            desc("过生日收到了父母送的新款游戏机PS69")
            check { withinAge(8..18) && Attribute.ECONOMIC > 10 }
            weight(25)
            modify {
                Attribute.IQ + 1
            }
        }
        "肥宅快乐水" {
            desc("你喜欢上了肥宅快乐水。")
            check { withinAge(5..20) }
            weight(50)
            modify {
                Attribute.STRENGTH - 1
                Attribute.HAPPINESS + 1
            }
        }
    }

    "中年" {

    }

    "本子" {
        check { withinAge(10..30) && hasEvent("二次元") }
        "看本子" {
            desc("你喜欢在粉色APP上康好康的。")
            desc("网友经常给你发涩涩的本子，你很喜欢。")
            check { olderThan(12) }
            weight(50)
        }
        "代入本子女角色" {
            desc("你发现你每次看小黄本代入的都是女性角色。")
            desc("看本子的时候你经常幻想自己是女主。")
            check { olderThan(12) && hasEvent("看本子") }
            weight {
                buildWeight(25) {
                    times(Attribute.FEMININITY * 0.2 + 1.0)
                }
            }
            modify {
                Attribute.FEMININITY + 1
            }
        }
    }

    "游戏" {
        check { withinAge(12..30) }

        "撸啊撸" {
            desc("你喜欢玩撸啊撸。")
            weight {
                buildWeight(50) {
                    addIf(-40, hasGroup("游戏"))
                    addIf(-5, hasEvent("农药"))
                }
            }
        }
        "原批" {
            desc("你喜欢玩原神。")
            weight {
                buildWeight(50) {
                    addIf(-40, hasGroup("游戏"))
                }
            }
        }
        "农药" {
            desc("你喜欢玩亡者农药。")
            weight {
                buildWeight(50) {
                    addIf(-40, hasGroup("游戏"))
                    addIf(-5, hasEvent("撸啊撸"))
                }
            }
        }
        "Galgame" {
            desc("你喜欢玩Galgame。")
            weight {
                buildWeight(50) {
                    addIf(-40, hasGroup("游戏"))
                    addIf(40, hasEvent("二次元"))
                }
            }
        }
    }

    "代练上分" {
        check {
            exclude() && withinAge(12..30) && (hasEvent("撸啊撸") || hasEvent("农药"))
        }

        "代练上分_1" {
            desc("你在网上帮别人代练上分。")
            weight(100)
        }
        "代练上分_2" {
            desc("你在网上帮别人代练上分，赚了点小钱")
            weight(60)
            modify {
                Attribute.ECONOMIC + 1
            }
        }
        "代练上分_3" {
            desc("你在网上帮别人代练上分，认识了很多朋友。")
            weight(80)
            modify {
                Attribute.HAPPINESS + 1
            }
        }
    }

    "恋爱绮谭" {
        check { exclude() && hasGroup("接触MTF") }

        "恋爱绮谭_1" {
            desc("你跟风玩了某破防小游戏，并没有什么感觉。")
            weight {
                buildWeight(50) {
                    plus((Attribute.FEMININITY - 2) * 25)
                }
            }
        }
        "恋爱绮谭_2" {
            desc("你跟风玩了某破防小游戏，破防哭了一场。")
            weight {
                buildWeight(30) {
                    plus(Attribute.FEMININITY * 25)
                }
            }
            modify {
                Attribute.FEMININITY + 1
            }
        }
        "恋爱绮谭_3" {
            desc("你跟风玩了某破防小游戏，破防大哭了一场。")
            check { Attribute.FEMININITY > 4 }
            weight {
                buildWeight(40) {
                    plus(Attribute.FEMININITY * 10)
                }
            }
            modify {
                Attribute.FEMININITY + 2
            }
        }
        "恋爱绮谭_4" {
            desc("你跟风玩了某破防小游戏，苏半夏竟是你自己。")
            check { Attribute.FEMININITY > 8 }
            weight {
                buildWeight(50) {
                    plus(Attribute.FEMININITY * 10)
                }
            }
            modify {
                Attribute.FEMININITY + 3
            }
        }
    }


    "女性化" {
        "感觉是女孩" {
            desc("感觉自己应该是个女孩。")
            check { withinAge(3..16) && Attribute.FEMININITY > 5 }
            weight(500)
            modify {
                Attribute.FEMININITY + 1
            }
        }
        "被当女孩子" {
            desc("总是被当成女孩子。")
            check { withinAge(3..15) && Attribute.FEMININITY > 8 }
            weight {
                buildWeight(80) {
                    plus((Attribute.FEMININITY - 8) * 10)
                }
            }
            modify {
                Attribute.FEMININITY + 4
            }
        }
        "偷穿表姐裙子" {
            desc("你偷偷穿上了表姐的裙子，对着镜子照了半天。")
            check { withinAge(3..18) && Attribute.FEMININITY > 4 }
            weight {
                buildWeight(50) {
                    plus((Attribute.FEMININITY - 4) * 10)
                }
            }
            modify {
                Attribute.FEMININITY + 3
            }
        }
        "被男生偷看_1" {
            desc("做操体转运动时，男同学总是偷看你。")
            check { withinAge(3..6) && Attribute.FEMININITY > 8 }
            weight {
                buildWeight(40) {
                    plus((Attribute.FEMININITY - 8) * 10)
                }
            }
            modify {
                Attribute.FEMININITY + 1
            }
        }
        "网上性别女_1" {
            desc("你在网上用的性别是女。")
            check { withinAge(8..16) && Attribute.FEMININITY > 2 }
            weight {
                buildWeight(100) {
                    plus(Attribute.FEMININITY * 5)
                }
            }
            modify {
                Attribute.FEMININITY + 1
            }
        }
    }

    "和女孩子玩" {
        "和女孩子玩_1" {
            desc("跟邻家女孩子玩的很开心。")
            check { withinAge(3..12) && Attribute.FEMININITY > 4 }
            weight {
                buildWeight(50) {
                    plus((Attribute.FEMININITY - 4) * 10)
                }
            }
            modify {
                Attribute.FEMININITY + 2
            }
        }
        "和女孩子玩_2" {
            desc("你经常和女同学玩跳皮筋。")
            check { withinAge(6..16) && Attribute.FEMININITY > 6 }
            weight {
                buildWeight(40) {
                    plus((Attribute.FEMININITY - 6) * 10)
                }
            }
            modify {
                Attribute.FEMININITY + 2
            }
        }
    }

    // 吃糖
    "接触MTF" {
        check { exclude() && olderThan(12) }

        "接触MTF_1" {
            desc("在网上接触到MTF群体，觉醒了性别认同")
            weight {
                buildWeight(50) {
                    addIf(100, hasEvent("生儿育女"))
                }
            }
            modify {
                Attribute.FEMININITY + 1
            }
        }
        "接触MTF_1" {
            desc("在QQ群聊天的时候，有群友突然聊到了“药娘”，好奇的你去百度了一下，打开了新世界的大门。")
            desc("在QQ群聊天的时候，有群友突然聊到了“木桶饭”，好奇的你去百度了一下，打开了新世界的大门。")
            desc("在贴吧上看到有吧友在聊“药娘”，好奇的你去百度了一下，打开了新世界的大门。")
            desc("在贴吧上看到有吧友在聊“木桶饭”，好奇的你去百度了一下，打开了新世界的大门。")
            weight(100)
        }
    }

    "开始吃糖" {
        check { exclude() && olderThan(12) && hasGroup("接触MTF") }

        "开始吃糖_1" {
            desc("你在网上了解到了雌性激素药物，决定开始吃糖，但没有途径的你只能选择吃避孕药。")
            weight {
                buildWeight(50) {
                    addIf(100, Attribute.IQ < 8 || Attribute.ECONOMIC < 6)
                }
            }
            modify {
                Attribute.FEMININITY + 4
                Attribute.STRENGTH - 1
                Attribute.HAPPINESS - 1
            }
        }
        "开始吃糖_2" {
            desc("你在同类群里听说了补加乐和色谱龙，询问之下找到了药商，买到了药。")
            weight(80)
            modify {
                Attribute.FEMININITY + 4
                Attribute.STRENGTH - 1
            }
        }
        "开始吃糖_3" {
            desc("跟父母出柜，他们非常开心的带着你去刷证，并给你买药")
            check { hasEvent("生儿育女") || Attribute.FEMININITY > 10 && Attribute.ECONOMIC > 6 }
            weight(500)
            modify {
                Attribute.HAPPINESS + 1
                Attribute.FEMININITY + 4
            }
        }
    }
}