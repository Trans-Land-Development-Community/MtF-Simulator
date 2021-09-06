package me.xiaro.mtfsim.ui

import kotlinx.css.fontSize
import kotlinx.css.rem
import me.xiaro.mtfsim.Simulation
import react.Props
import react.RBuilder
import react.RComponent
import react.setState
import styled.css
import styled.styledDiv

class App : RComponent<Props, AppState>() {
    override fun AppState.init() {
        page = Page.MENU
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                fontSize = 1.rem
            }
            when (state.page) {
                Page.MENU -> {
                    child(MenuPage::class) {
                        attrs {
                            nextPage = {
                                setState {
                                    page = Page.ATTRIB
                                }
                            }
                        }
                    }
                }
                Page.ATTRIB -> {
                    child(AttribPage::class) {
                        attrs {
                            onExit = {
                                setState {
                                    page = Page.MENU
                                }
                            }
                            onStart = {
                                setState {
                                    attributeMap = it
                                    page = Page.PLAY
                                }
                            }
                        }
                    }
                }
                Page.PLAY -> {
                    child(PlayPage::class) {
                        attrs {
                            simulation = Simulation(state.attributeMap)
                            prevPage = {
                                setState {
                                    page = Page.MENU
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    enum class Page {
        MENU, ATTRIB, PLAY
    }
}