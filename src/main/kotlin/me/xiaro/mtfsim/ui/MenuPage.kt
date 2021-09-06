package me.xiaro.mtfsim.ui

import kotlinx.css.*
import kotlinx.css.properties.transform
import kotlinx.css.properties.translate
import react.RBuilder
import react.RComponent
import react.State
import react.dom.attrs
import react.dom.onClick
import styled.css
import styled.styledDiv

class MenuPage : RComponent<PageProp, State>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                position = Position.fixed
                left = 50.pct
                top = 38.pct
                transform {
                    translate((-50).pct, (-50).pct)
                }
                fontSize = 3.rem
                fontWeight = FontWeight.bold
                whiteSpace = WhiteSpace.nowrap
                textAlign = TextAlign.center
            }

            +"小药娘模拟器"

            styledDiv {
                css {
                    fontSize = 2.rem
                    fontWeight = FontWeight.normal
                }

                +"做一个女孩子"
            }
        }

        centeredButton {
            css {
                bottom = 2.rem
            }

            attrs {
                onClick = {
                    props.nextPage.invoke()
                }
            }

            +"▶ 开始游戏"
        }
    }
}