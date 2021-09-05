package me.xiaro.mtfsim.ui

import kotlinx.css.*
import kotlinx.css.properties.transform
import kotlinx.css.properties.translate
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.attrs
import styled.css
import styled.styledButton
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
                fontSize = 4.rem
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

        styledButton {
            css {
                position = Position.fixed
                left = 50.pct
                top = 62.pct
                transform {
                    translate((-50).pct, (-50).pct)
                }
                fontSize = 2.rem
            }

            attrs {
                onClickFunction = {
                    props.nextPage.invoke()
                }
            }

            +"▶ 开始游戏"
        }
    }
}