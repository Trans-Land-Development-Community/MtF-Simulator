package me.xiaro.mtfsim

import kotlinx.browser.localStorage
import org.w3c.dom.get
import org.w3c.dom.set
import kotlin.js.Date
import kotlin.random.Random

object SaveData {
    val hwid: Long
        get() = localStorage["hwid"]?.toLongOrNull() ?: kotlin.run {
            val random = Random(Date.now().toLong()).nextLong()
            localStorage["hwid"] = random.toString()
            random
        }
    var playedTimes: Int
        get() = localStorage["playedTimes"]?.toIntOrNull() ?: 0
        set(value) {
            localStorage["playedTimes"] = value.toString()
        }
}