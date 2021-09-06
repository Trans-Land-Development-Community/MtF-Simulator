package me.xiaro.mtfsim.ui

import kotlinx.css.*
import kotlinx.css.properties.transform
import kotlinx.css.properties.translate
import me.xiaro.mtfsim.attribute.Attribute
import me.xiaro.mtfsim.attribute.AttributeMap
import org.w3c.dom.HTMLElement
import react.*
import react.dom.attrs
import react.dom.onClick
import react.dom.onFocus
import styled.*
import kotlin.math.min

class AttribPage : RComponent<AttribProp, AttribState>() {
    override fun AttribState.init() {
        points = totalPoints
        attributeMap = defaultAttributeMap
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

            +"分配初始属性"

            styledDiv {
                css {
                    fontSize = 1.rem
                }

                +"可用属性点: ${state.points}"
            }
        }

        styledUl {
            css {
                position = Position.fixed
                left = 50.pct
                top = 7.rem
                transform {
                    translate((-50).pct, 0.pct)
                }
                fontSize = 1.5.rem
                listStyleType = ListStyleType.none
                padding(0.px)
            }

            Attribute.startUpAttributes.forEach {
                child(attributeAllocation(it))
            }
        }

        centeredButton {
            css {
                bottom = 6.rem
            }

            attrs {
                onClick = {
                    setState {
                        attributeMap = defaultAttributeMap
                        points = totalPoints

                        while (points > 0) {
                            val attribute = Attribute.startUpAttributes.random()
                            val prev = attributeMap[attribute]
                            if (prev >= 20) continue
                            attributeMap[attribute] = prev + 2
                            points -= 2
                        }
                    }
                }
            }

            +"随机分配"
        }

        centeredButton {
            css {
                bottom = 2.rem
            }

            attrs {
                disabled = state.points != 0
                onClick = {
                    props.onStart.invoke(state.attributeMap)
                }
            }

            +"开始游戏"
        }
    }

    private fun attributeAllocation(attribute: Attribute) = fc<Props> {
        styledLi {
            css {
                marginTop = 0.5.rem
                marginBottom = 0.5.rem
                display = Display.listItem
            }

            +"${attribute.displayName}      "

            attribButton("-") {
                updateAttrib(attribute, state.attributeMap[attribute] - 2)
            }
            styledInput {
                css {
                    width = 2.rem
                    height = 2.rem
                    textAlign = TextAlign.center
                    fontSize = 1.5.rem
                    marginLeft = 0.5.rem
                    marginRight = 0.5.rem
                }
                attrs {
                    value = state.attributeMap[attribute].toString()
                    readonly = true
                    onFocus = {
                        it.preventDefault()
                        if (it.relatedTarget != null) {
                            (it.relatedTarget as HTMLElement).focus()
                        } else {
                            (it.currentTarget as HTMLElement).blur()
                        }
                    }
                }
            }

            attribButton("+") {
                updateAttrib(attribute, state.attributeMap[attribute] + 2)
            }
        }
    }

    private fun RBuilder.attribButton(text: String, action: () -> Unit) {
        styledButton {
            css {
                width = 1.8.rem
                height = 1.8.rem
                fontSize = 1.5.rem
            }

            +text

            attrs {
                onClick = {
                    action.invoke()
                }
            }
        }
    }

    private fun updateAttrib(attribute: Attribute, value: Int) {
        setState {
            val prev = attributeMap[attribute]
            attributeMap[attribute] = value.coerceIn(0, min(points + prev, 20))
            points = totalPoints - attributeMap.startupValues.sum()
        }
    }

    private val defaultAttributeMap: AttributeMap
        get() = AttributeMap().apply {
            set(Attribute.BEAUTY, 0)
            set(Attribute.IQ, 0)
            set(Attribute.STRENGTH, 0)
            set(Attribute.ECONOMIC, 0)

            set(Attribute.HAPPINESS, 10)
            set(Attribute.HEALTH, 10)
            set(Attribute.FEMININITY, 0)
        }


    private companion object {
        const val totalPoints = 40
    }
}