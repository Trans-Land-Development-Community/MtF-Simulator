package me.xiaro.mtfsim.ui

import kotlinx.css.fontSize
import kotlinx.css.rem
import me.xiaro.mtfsim.Simulation
import me.xiaro.mtfsim.trait.Rarity
import me.xiaro.mtfsim.trait.Trait
import me.xiaro.mtfsim.trait.TraitManager
import me.xiaro.mtfsim.utils.BitSet
import react.Props
import react.RBuilder
import react.RComponent
import react.setState
import styled.css
import styled.styledDiv
import kotlin.random.Random

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
                                    page = Page.TRAIT
                                }
                            }
                        }
                    }
                }
                Page.TRAIT -> {
                    child(TraitPage::class) {
                        attrs {
                            availableTraits = getRandomTraits()
                            onNext = {
                                setState {
                                    traits = it
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
                            simulation = Simulation(state.attributeMap, state.traits)
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

    private fun getRandomTraits(): List<Trait> {
        val set = BitSet()
        val list = ArrayList<Trait>()

        while (list.size < 10) {
            var rarity = Rarity.COMMON
            var random = Random.nextInt(100)

            for (r in Rarity.values()) {
                when {
                    random < r.weight -> {
                        rarity = r
                        break
                    }
                    else -> {
                        random -= r.weight
                    }
                }
            }

            val trait = TraitManager.rarityTraitsMap[rarity].random()
            if (set.contains(trait.id)) continue
            if (!trait.check(list)) continue

            set.add(trait.id)
            list.add(trait)
        }

        return list.toList()
    }

    enum class Page {
        MENU, TRAIT, ATTRIB, PLAY
    }
}