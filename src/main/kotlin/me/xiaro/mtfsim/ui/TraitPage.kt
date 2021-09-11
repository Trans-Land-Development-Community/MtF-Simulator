package me.xiaro.mtfsim.ui

import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.transform
import kotlinx.css.properties.translate
import me.xiaro.mtfsim.trait.Trait
import me.xiaro.mtfsim.utils.MutableNonNullMap
import react.*
import react.dom.attrs
import react.dom.onClick
import styled.css
import styled.styledDiv
import styled.styledLi
import styled.styledUl

class TraitPage : RComponent<TraitProp, TraitState>() {
    override fun TraitState.init() {
        points = 3
        traitMap = MutableNonNullMap(HashMap()) {
            false
        }
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                position = Position.fixed
                left = 50.pct
                top = 2.rem
                transform {
                    translate((-50).pct, 0.pct)
                }
                fontSize = 2.rem
                whiteSpace = WhiteSpace.nowrap
                textAlign = TextAlign.center
            }

            +"天赋十选三"
        }

        styledUl {
            css {
                position = Position.fixed
                left = 50.pct
                top = 5.rem
                bottom = 5.rem
                transform {
                    translate((-50).pct, 0.pct)
                }
                width = 90.pct
                fontSize = 1.2.rem
                listStyleType = ListStyleType.none
                overflow = Overflow.auto
                textAlign = TextAlign.center
                padding(0.px)
            }

            props.availableTraits.forEach {
                child(traitButton(it, state.traitMap[it]))
            }
        }

        centeredButton {
            css {
                bottom = 2.rem
            }

            attrs {
                disabled = state.points != 0
                onClick = {
                    props.onNext.invoke(state.traitMap.mapNotNull { entry -> entry.key.takeIf { entry.value } })
                }
            }

            +"开始游戏"
        }
    }

    private fun traitButton(trait: Trait, selected: Boolean) = fc<Props> {
        styledLi {
            css {
                marginBottom = 0.2.rem

                position = Position.relative
                display = Display.inlineBlock
                width = 90.pct
                height = 1.7.rem
                cursor = Cursor.pointer

                if (selected) color = Color("#fff")
                if (selected) boxShadow(trait.rarity.color.darken(5), blurRadius = 4.px, spreadRadius = 0.px)

                backgroundColor = if (selected) trait.rarity.color.darken(10) else trait.rarity.color
                borderRadius = 0.25.rem
                borderColor = Color("#c5c5c5")
                borderWidth = 2.px
                borderStyle = BorderStyle.solid
                padding(0.rem)

                hover {
                    backgroundColor = trait.rarity.color.darken(20)
                }
            }

            attrs {
                onClick = {
                    if (selected) {
                        setState {
                            traitMap[trait] = false
                            points++
                        }
                    } else if (state.points > 0) {
                        setState {
                            traitMap[trait] = true
                            points--
                        }
                    }
                }
            }

            +"  ${trait.name}（${trait.description}）"
        }
    }
}