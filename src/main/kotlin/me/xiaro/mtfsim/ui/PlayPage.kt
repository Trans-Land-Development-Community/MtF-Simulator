package me.xiaro.mtfsim.ui

import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.Element
import react.*
import react.dom.attrs
import react.dom.div
import react.dom.li
import react.dom.onClick
import styled.css
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
                }
                attrs {
                    onClickFunction = {
                        if (!props.simulation.dead) {
                            setState {
                                val result = props.simulation.grow()
                                results.add("${props.simulation.age}岁: ${result.message}")
                            }
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


        centeredButton {
            css {
                bottom = 2.rem
            }

            attrs {
                onClick = {
                    props.prevPage.invoke()
                }
            }

            +"退出游戏"
        }
    }
}