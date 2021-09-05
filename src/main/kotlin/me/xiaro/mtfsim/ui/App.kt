package me.xiaro.mtfsim.ui

import me.xiaro.mtfsim.Simulation
import me.xiaro.mtfsim.attribute.Attribute
import me.xiaro.mtfsim.attribute.AttributeMap
import react.RBuilder
import react.RComponent
import react.setState

class App : RComponent<PageProp, AppState>() {
    override fun AppState.init() {
        page = EnumPage.MENU
    }

    override fun RBuilder.render() {
        when(state.page) {
            EnumPage.MENU -> {
                child(MenuPage::class) {
                    attrs {
                        nextPage = {
                            setState {
                                page = EnumPage.PLAY
                            }
                        }
                    }
                }
            }
            EnumPage.PLAY -> {
                child(PlayPage::class) {
                    attrs {
                        simulation = newSimulation()
                        prevPage = {
                            setState {
                                page = EnumPage.MENU
                            }
                        }
                    }
                }
            }
        }
    }

    private fun newSimulation(): Simulation {
        return Simulation(
            AttributeMap().apply {
                set(Attribute.BEAUTY, 20)
                set(Attribute.IQ, 20)
                set(Attribute.STRENGTH, 20)
                set(Attribute.ECONOMIC, 20)

                set(Attribute.HAPPINESS, 10)
                set(Attribute.HEALTH, 10)
                set(Attribute.FEMININITY, 0)
            }
        )
    }
}