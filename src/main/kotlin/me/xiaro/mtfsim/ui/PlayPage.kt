package me.xiaro.mtfsim.ui

import kotlinx.css.*
import kotlinx.css.properties.transform
import kotlinx.css.properties.translate
import kotlinx.html.js.onClickFunction
import org.w3c.dom.Element
import react.*
import react.dom.attrs
import react.dom.div
import react.dom.li
import styled.css
import styled.styledButton
import styled.styledUl

class PlayPage : RComponent<PlayProp, PlayState>() {
    override fun PlayState.init() {
        results = ArrayList()
    }

    override fun RBuilder.render() {
        child(fc {
            styledUl {
                css {
                    position = Position.fixed
                    left = 1.rem
                    right = 1.rem
                    top = 1.rem
                    bottom = 4.rem
                    overflow = Overflow.auto
                    fontSize = 1.5.rem
                }
                attrs {
                    onClickFunction = {
                        println("grow!")
                        val result = props.simulation.grow()
                        setState {
                            results.add("${props.simulation.age}岁: ${result.message}")
                        }
                    }
                }

                state.results.forEach {
                    li {
                        +it
                    }
                }

                val dummyDiv = useRef<Element>()

                div {
                    ref = dummyDiv
                }

                useEffect {
                    dummyDiv.current?.scrollIntoView()
                }
            }
        })

        styledButton {
            css {
                position = Position.fixed
                left = 50.pct
                bottom = 1.rem
                transform {
                    translate((-50).pct, 0.pct)
                }
                fontSize = 2.rem
            }

            attrs {
                onClickFunction = {
                    props.prevPage.invoke()
                }
            }

            +"退出游戏"
        }
    }
}