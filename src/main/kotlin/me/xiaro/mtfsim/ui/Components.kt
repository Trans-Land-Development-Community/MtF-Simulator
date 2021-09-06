package me.xiaro.mtfsim.ui

import kotlinx.css.*
import kotlinx.css.properties.transform
import kotlinx.css.properties.translate
import kotlinx.html.BUTTON
import react.RBuilder
import styled.StyledDOMBuilder
import styled.css
import styled.styledButton

fun RBuilder.centeredButton(
    block: StyledDOMBuilder<BUTTON>.() -> Unit
) = styledButton {
    css {
        position = Position.fixed
        left = 50.pct
        transform {
            translate((-50).pct, 0.pct)
        }
        fontSize = 1.5.rem
        padding(0.25.rem, 1.0.rem)
    }

    block.invoke(this)
}