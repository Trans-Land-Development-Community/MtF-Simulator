package me.xiaro.mtfsim.contents

import me.xiaro.mtfsim.attribute.Attribute
import me.xiaro.mtfsim.trait.Rarity
import me.xiaro.mtfsim.trait.Trait
import me.xiaro.mtfsim.trait.TraitManager

private operator fun String.invoke(block: Trait.Builder.() -> Unit) {
    TraitManager.registerTrait(Trait.Builder(this).apply(block).build())
}

fun initTraits() {
    "天生抑郁" {
        rarity(Rarity.COMMON)
        desc("快乐-4")
        modify {
            Attribute.HAPPINESS - 4
        }
    }
    "天残党" {
        rarity(Rarity.COMMON)
        desc("颜值-4")
        modify {
            Attribute.BEAUTY - 4
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
        desc("体质-2")
        modify {
            Attribute.STRENGTH - 2
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
        desc("家境-3，快乐+3")
        modify {
            Attribute.ECONOMIC - 3
            Attribute.HAPPINESS + 3
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
        desc("18岁时快乐+3")
        modify {
            if (it.olderThan(18)) Attribute.HAPPINESS + 3
        }
    }
    "整容" {
        rarity(Rarity.COMMON)
        desc("家境>20时颜值+4")
        modify {
            if (Attribute.ECONOMIC > 20) Attribute.HAPPINESS + 4
        }
    }

    "网络巨魔" {
        rarity(Rarity.RARE)
        desc("快乐+5")
        modify {
            Attribute.HAPPINESS + 5
        }
    }
    "女大十八变" {
        rarity(Rarity.RARE)
        desc("18岁时颜值+6")
        modify {
            if (it.olderThan(18)) Attribute.BEAUTY + 6
        }
    }
    "形象管理" {
        rarity(Rarity.RARE)
        desc("24岁时颜值+7")
        modify {
            if (it.olderThan(24)) Attribute.BEAUTY + 7
        }
    }
    "成熟" {
        rarity(Rarity.RARE)
        desc("18岁时智力+6")
        modify {
            if (it.olderThan(18)) Attribute.IQ + 6
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


    "随身玉佩" {
        rarity(Rarity.COMMON)
        desc("或许有护佑作用")
    }
    "红肚兜" {
        rarity(Rarity.COMMON)
        desc("小时候死亡率降低")
    }
    "乐观" {
        rarity(Rarity.COMMON)
        desc("快乐+1")
        modify {
            Attribute.HAPPINESS + 1
        }
    }
    "乡间微风" {
        rarity(Rarity.COMMON)
        desc("你出生在农村")
        exclude("天龙人", "城中高楼", "美籍华人")
    }
    "城中高楼" {
        rarity(Rarity.COMMON)
        desc("你出生在城市")
        exclude("天龙人", "乡间微风", "美籍华人")
    }
    "水性良好" {
        rarity(Rarity.COMMON)
        desc("不会被淹死")
    }
    "先天免疫" {
        rarity(Rarity.COMMON)
        desc("你不会得艾滋病")
    }
    "红颜薄命" {
        rarity(Rarity.COMMON)
        desc("颜值+3，体质-3")
        modify {
            Attribute.BEAUTY + 3
            Attribute.STRENGTH - 3
        }
    }
    "属蛇" {
        rarity(Rarity.COMMON)
        desc("不会被蛇咬死")
    }
    "彩虹之下" {
        rarity(Rarity.COMMON)
        desc("可能和同性交往")
        exclude("三胎人生")
    }
    "桃花连连" {
        rarity(Rarity.COMMON)
        desc("恋爱机会提升")
    }
    "宠物大师" {
        rarity(Rarity.COMMON)
        desc("宠物不会意外死亡")
    }
    "十死无生" {
        rarity(Rarity.COMMON)
        desc("体质-10")
        modify {
            Attribute.STRENGTH - 10
        }
    }
    "家运不顺" {
        rarity(Rarity.COMMON)
        desc("家境-2")
        modify {
            Attribute.ECONOMIC - 2
        }
    }
    "头着地" {
        rarity(Rarity.COMMON)
        desc("智力-2")
        modify {
            Attribute.IQ - 2
        }
    }
    "胎教" {
        rarity(Rarity.COMMON)
        desc("智力+1")
        modify {
            Attribute.IQ + 1
        }
    }
    "班中红人" {
        rarity(Rarity.COMMON)
        desc("和同学容易处好关系")
    }
    "骑士" {
        rarity(Rarity.COMMON)
        desc("能轻松学会骑车")
    }
    "戒律" {
        rarity(Rarity.COMMON)
        desc("赌毒不沾")
    }
    "少数民族" {
        rarity(Rarity.COMMON)
        desc("高考+5分")
    }
    "老司机" {
        rarity(Rarity.COMMON)
        desc("你和家人不会发生车祸")
    }
    "低压" {
        rarity(Rarity.COMMON)
        desc("你的家人不会心脏病")
    }
    "战功" {
        rarity(Rarity.COMMON)
        desc("你退伍后会当官")
    }
    "三十而立" {
        rarity(Rarity.COMMON)
        desc("30岁时家境+2")
        modify {
            Attribute.ECONOMIC + 2
        }
    }
    "四十不惑" {
        rarity(Rarity.COMMON)
        desc("40岁时智力+2")
        modify {
            Attribute.IQ + 2
        }
    }
    "知天命" {
        rarity(Rarity.COMMON)
        desc("50岁时智力、快乐+1")
        modify {
            Attribute.HAPPINESS + 1
            Attribute.IQ + 1
        }
    }
    "耳顺" {
        rarity(Rarity.COMMON)
        desc("60岁时快乐+2")
        modify {
            Attribute.HAPPINESS + 2
        }
    }
    "从心所欲" {
        rarity(Rarity.COMMON)
        desc("70岁时快乐+3")
        modify {
            Attribute.HAPPINESS + 3
        }
    }
    "鹤发童颜" {
        rarity(Rarity.COMMON)
        desc("70岁时颜值+3")
        modify {
            Attribute.BEAUTY + 3
        }
    }
    "迟来之财" {
        rarity(Rarity.COMMON)
        desc("90岁时家境+4")
        modify {
            Attribute.ECONOMIC + 4
        }
    }
    "理财达人" {
        rarity(Rarity.COMMON)
        desc("40岁时家境+3")
        modify {
            Attribute.ECONOMIC + 3
        }
    }
    "成年礼" {
        rarity(Rarity.COMMON)
        desc("18岁时快乐+1")
        modify {
            Attribute.HAPPINESS + 1
        }
    }
    "开光之胎" {
        rarity(Rarity.COMMON)
        desc("初始可用属性点+1")
    }
    "精准扶贫" {
        rarity(Rarity.COMMON)
        desc("家境为0时家境+1")
        modify {
            Attribute.ECONOMIC + 1
        }
    }
    "命悬一线" {
        rarity(Rarity.COMMON)
        desc("体质为0时体质+1")
        modify {
            Attribute.STRENGTH + 1
        }
    }
    "智可生财" {
        rarity(Rarity.COMMON)
        desc("若20岁时智力>8，家境+2")
        modify {
            Attribute.ECONOMIC + 2
        }
    }
    "舔狗甚多" {
        rarity(Rarity.COMMON)
        desc("若20岁时颜值>8，快乐+2")
        modify {
            Attribute.HAPPINESS + 2
        }
    }
    "保胎丸" {
        rarity(Rarity.COMMON)
        desc("你不会胎死腹中")
    }
    "白化病" {
        rarity(Rarity.COMMON)
        desc("你不会遭遇枪击")
    }
    "佛宗" {
        rarity(Rarity.COMMON)
        desc("考上哈佛大学的几率提高")
    }
    "驻颜" {
        rarity(Rarity.COMMON)
        desc("体质>10时颜值+3")
        modify {
            Attribute.BEAUTY + 3
        }
    }
    "训练有方" {
        rarity(Rarity.COMMON)
        desc("智力>10时体质+3")
        modify {
            Attribute.STRENGTH + 3
        }
    }
    "相由心生" {
        rarity(Rarity.COMMON)
        desc("智力>10时颜值+3")
        modify {
            Attribute.BEAUTY + 3
        }
    }
    "智多鑫" {
        rarity(Rarity.COMMON)
        desc("智力>10时家境+3")
        modify {
            Attribute.ECONOMIC + 3
        }
    }
    "灵光" {
        rarity(Rarity.COMMON)
        desc("快乐>10时其他属性+1")
        modify {
            Attribute.ECONOMIC + 1
            Attribute.BEAUTY + 1
            Attribute.STRENGTH + 1
            Attribute.IQ + 1
        }
    }
    "献祭" {
        rarity(Rarity.COMMON)
        desc("初始可用属性点-2，快乐+2")
        exclude("急了急了")
        modify {
            Attribute.HAPPINESS + 2
        }
    }
    "挑战者" {
        rarity(Rarity.COMMON)
        desc("初始可用点-10")
        exclude("急了急了")
    }
    "钻石健身卡" {
        rarity(Rarity.COMMON)
        desc("家境>10时体质+3")
        modify {
            Attribute.STRENGTH + 3
        }
    }
    "身残志坚" {
        rarity(Rarity.COMMON)
        desc("体质<0时其他属性+1")
        modify {
            Attribute.HAPPINESS + 1
            Attribute.ECONOMIC + 1
            Attribute.BEAUTY + 1
            Attribute.IQ + 1
        }
    }
    "开一扇窗" {
        rarity(Rarity.COMMON)
        desc("颜值<0时其他属性+1")
        modify {
            Attribute.HAPPINESS + 1
            Attribute.ECONOMIC + 1
            Attribute.STRENGTH + 1
            Attribute.IQ + 1
        }
    }
    "大额头" {
        rarity(Rarity.COMMON)
        desc("颜值-2，智力+2")
        modify {
            Attribute.BEAUTY - 2
            Attribute.IQ + 2
        }
    }
    "痘痘脸" {
        rarity(Rarity.COMMON)
        desc("颜值-1")
        modify {
            Attribute.BEAUTY - 1
        }
    }
    "潜能" {
        rarity(Rarity.COMMON)
        desc("家境<0时其他属性+1")
        modify {
            Attribute.HAPPINESS + 1
            Attribute.BEAUTY + 1
            Attribute.STRENGTH + 1
            Attribute.IQ + 1
        }
    }
    "哀兵" {
        rarity(Rarity.COMMON)
        desc("快乐<0时其他属性+1")
        modify {
            Attribute.ECONOMIC + 1
            Attribute.BEAUTY + 1
            Attribute.STRENGTH + 1
            Attribute.IQ + 1
        }
    }
    "海的女儿" {
        rarity(Rarity.COMMON)
        desc("颜值-2，初始可用属性点+1")
        modify {
            Attribute.BEAUTY - 2
        }
    }
    "进阶" {
        rarity(Rarity.COMMON)
        desc("所有属性>5时，所有属性+1")
        modify {
            Attribute.HAPPINESS + 1
            Attribute.ECONOMIC + 1
            Attribute.BEAUTY + 1
            Attribute.STRENGTH + 1
            Attribute.IQ + 1
        }
    }
    "白色胶囊" {
        rarity(Rarity.COMMON)
        desc("你10岁时无事发生")
    }
    "健康饮食" {
        rarity(Rarity.COMMON)
        desc("你不吃洋快餐")
    }
    "不想罢了" {
        rarity(Rarity.COMMON)
        desc("你不会上清华大学")
    }
    "挑衅" {
        rarity(Rarity.COMMON)
        desc("你喜欢没事找事")
    }
    "旅行者" {
        rarity(Rarity.COMMON)
        desc("你喜欢旅游")
    }
    "水仙" {
        rarity(Rarity.COMMON)
        desc("你比较自恋")
    }
    "缺一门" {
        rarity(Rarity.COMMON)
        desc("无效果")
    }
    "宙斯" {
        rarity(Rarity.COMMON)
        desc("参加奥赛的几率提高")
    }
    "为人民服务" {
        rarity(Rarity.COMMON)
        desc("考公务员时一定能考上")
    }
    "表现良好" {
        rarity(Rarity.COMMON)
        desc("入狱会减刑")
    }
    "小吉" {
        rarity(Rarity.COMMON)
        desc("运气稍微提升")
    }
    "天秤座" {
        rarity(Rarity.COMMON)
        desc("据说做事很公平")
    }
    "万里挑一" {
        rarity(Rarity.COMMON)
        desc("你很攻")
    }
    "把握不住" {
        rarity(Rarity.COMMON)
        desc("你有强迫症")
    }
    "不离不弃" {
        rarity(Rarity.COMMON)
        desc("你不会离婚")
    }
    "足量" {
        rarity(Rarity.COMMON)
        desc("身高不矮")
    }
    "黄帝" {
        rarity(Rarity.COMMON)
        desc("种族主义者")
    }
    "左撇子" {
        rarity(Rarity.COMMON)
        desc("习惯使用左手")
    }
    "时光倒流" {
        rarity(Rarity.COMMON)
        desc("或许时间会倒流")
    }
    "生而为男" {
        rarity(Rarity.RARE)
        desc("性别一定为男")
        exclude("生而为女", "阴阳之外", "人中龙凤", "三胎人生")
    }
    "生而为女" {
        rarity(Rarity.RARE)
        desc("性别一定为女")
        exclude("生而为男", "人中龙凤", "阴阳之外")
    }
    "天赋异禀" {
        rarity(Rarity.RARE)
        desc("初始可用属性点+2")
    }
    "家中老大" {
        rarity(Rarity.RARE)
        desc("你最受父母宠爱")
    }
    "父母美貌" {
        rarity(Rarity.RARE)
        desc("颜值+2")
        modify {
            Attribute.BEAUTY + 2
        }
    }
    "斩情证道" {
        rarity(Rarity.RARE)
        desc("终生不恋爱结婚")
        exclude("三胎人生")
    }
    "平安童年" {
        rarity(Rarity.RARE)
        desc("12岁前父母都健在")
    }
    "永远的神" {
        rarity(Rarity.RARE)
        desc("电竞天才")
    }
    "丁克" {
        rarity(Rarity.RARE)
        desc("不生孩子")
        exclude("三胎人生")
    }
    "不孕不育" {
        rarity(Rarity.RARE)
        desc("你生不出孩子")
        exclude("三胎人生")
    }
    "白头偕老" {
        rarity(Rarity.RARE)
        desc("爱人至少能活到70岁")
    }
    "老当益壮" {
        rarity(Rarity.RARE)
        desc("60岁时体质+2")
        modify {
            Attribute.STRENGTH + 2
        }
    }
    "学前启蒙" {
        rarity(Rarity.RARE)
        desc("5岁时智力+2")
        modify {
            Attribute.IQ + 2
        }
    }
    "十八变" {
        rarity(Rarity.RARE)
        desc("18岁时颜值+2")
        modify {
            Attribute.BEAUTY + 2
        }
    }
    "乐天派" {
        rarity(Rarity.RARE)
        desc("快乐为0时快乐+1")
        modify {
            Attribute.HAPPINESS + 1
        }
    }
    "悟道" {
        rarity(Rarity.RARE)
        desc("智力>10时快乐+3")
        modify {
            Attribute.HAPPINESS + 3
        }
    }
    "界限突破" {
        rarity(Rarity.RARE)
        desc("体质>10时快乐+3")
        modify {
            Attribute.HAPPINESS + 3
        }
    }
    "倾城" {
        rarity(Rarity.RARE)
        desc("颜值>10时快乐+3")
        modify {
            Attribute.HAPPINESS + 3
        }
    }
    "天启" {
        rarity(Rarity.RARE)
        desc("快乐>10时其他属性+2")
        modify {
            Attribute.ECONOMIC + 2
            Attribute.BEAUTY + 2
            Attribute.STRENGTH + 2
            Attribute.IQ + 2
        }
    }
    "幸运儿" {
        rarity(Rarity.RARE)
        desc("初始可用属性点-3，快乐+5")
        exclude("急了急了")
        modify {
            Attribute.HAPPINESS + 5
        }
    }
    "你不懂" {
        rarity(Rarity.RARE)
        desc("家境>10时快乐+3")
        modify {
            Attribute.HAPPINESS + 3
        }
    }
    "活死人" {
        rarity(Rarity.RARE)
        desc("体质<0时其他属性+2")
        modify {
            Attribute.HAPPINESS + 2
            Attribute.ECONOMIC + 2
            Attribute.BEAUTY + 2
            Attribute.IQ + 2
        }
    }
    "苦痛侍僧" {
        rarity(Rarity.RARE)
        desc("快乐<-1时其他属性+2")
        modify {
            Attribute.ECONOMIC + 2
            Attribute.BEAUTY + 2
            Attribute.STRENGTH + 2
            Attribute.IQ + 2
        }
    }
    "觉醒" {
        rarity(Rarity.RARE)
        desc("家境<-1时其他属性+2")
        modify {
            Attribute.HAPPINESS + 2
            Attribute.BEAUTY + 2
            Attribute.STRENGTH + 2
            Attribute.IQ + 2
        }
    }
    "蓝色胶囊" {
        rarity(Rarity.RARE)
        desc("你20、30岁时无事发生")
    }
    "三胎人生" {
        rarity(Rarity.RARE)
        desc("你尽可能生三胎")
        exclude("生而为男", "阴阳之外", "彩虹之下", "斩情证道", "丁克", "不孕不育")
    }
    "急了急了" {
        rarity(Rarity.RARE)
        desc("赶着投胎，不要初始属性了")
        exclude("献祭", "幸运儿", "挑战者")
    }
    "不连续存在" {
        rarity(Rarity.RARE)
        desc("你还拥有其他人格")
    }
    "返老还童" {
        rarity(Rarity.RARE)
        desc("可能会回到年轻的时候")
    }
    "动漫高手" {
        rarity(Rarity.EPIC)
        desc("入宅的可能性翻6倍")
    }
    "天龙人" {
        rarity(Rarity.EPIC)
        desc("你拥有北京户口")
        exclude("乡间微风", "城中高楼", "美籍华人")
        modify {
            Attribute.ECONOMIC + 1
        }
    }
    "美籍华人" {
        rarity(Rarity.EPIC)
        desc("你有美国国籍")
        exclude("天龙人", "乡间微风", "城中高楼")
        modify {
            Attribute.ECONOMIC + 1
        }
    }
    "人类进化" {
        rarity(Rarity.EPIC)
        desc("所有属性+1")
        modify {
            Attribute.HAPPINESS + 1
            Attribute.ECONOMIC + 1
            Attribute.BEAUTY + 1
            Attribute.STRENGTH + 1
            Attribute.IQ + 1
        }
    }
    "超凡" {
        rarity(Rarity.EPIC)
        desc("初始可用属性点+4")
    }
    "人中龙凤" {
        rarity(Rarity.EPIC)
        desc("天生双重性别")
        exclude("生而为男", "生而为女", "阴阳之外")
    }
    "阴阳之外" {
        rarity(Rarity.EPIC)
        desc("天生无性别")
        exclude("生而为男", "人中龙凤", "生而为女", "三胎人生")
    }
    "神谕" {
        rarity(Rarity.EPIC)
        desc("快乐>10时其他属性+3")
        modify {
            Attribute.ECONOMIC + 3
            Attribute.BEAUTY + 3
            Attribute.STRENGTH + 3
            Attribute.IQ + 3
        }
    }
    "紫色胶囊" {
        rarity(Rarity.EPIC)
        desc("跳过你的40~50岁")
    }
    "异界来客" {
        rarity(Rarity.EPIC)
        desc("你可能听到一些绝密消息")
    }
    "克苏鲁" {
        rarity(Rarity.EPIC)
        desc("&▓▓▓◆▓▓▓￥#▓@■.◆")
    }
    "魔法棒" {
        rarity(Rarity.EPIC)
        desc("不知道有什么用……")
    }
    "转世重修" {
        rarity(Rarity.EPIC)
        desc("渡劫失败重生")
    }
    "半神" {
        rarity(Rarity.LEGENDARY)
        desc("所有属性+2")
        modify {
            Attribute.HAPPINESS + 2
            Attribute.ECONOMIC + 2
            Attribute.BEAUTY + 2
            Attribute.STRENGTH + 2
            Attribute.IQ + 2
        }
    }
    "神秘的小盒子" {
        rarity(Rarity.LEGENDARY)
        desc("100岁时才能开启")
    }
    "天命" {
        rarity(Rarity.LEGENDARY)
        desc("初始可用属性点+8")
    }
    "橙色胶囊" {
        rarity(Rarity.LEGENDARY)
        desc("跳过你的60~90岁")
    }
    "轮回之外" {
        rarity(Rarity.LEGENDARY)
        desc("死后可能灵魂离体")
    }
}

