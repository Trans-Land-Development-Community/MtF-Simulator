import me.xiaro.mtfsim.Simulation
import me.xiaro.mtfsim.attribute.Attribute
import me.xiaro.mtfsim.attribute.AttributeMap
import kotlin.test.Test

class TestClient {
    @Test
    fun simulationTest() {
        val simulation = try {
            Simulation(
                AttributeMap().apply {
                    set(Attribute.BEAUTY, 10)
                    set(Attribute.IQ, 10)
                    set(Attribute.STRENGTH, 10)
                    set(Attribute.ECONOMIC, 10)

                    set(Attribute.HAPPINESS, 10)
                    set(Attribute.HEALTH, 10)
                    set(Attribute.FEMININITY, 0)
                }
            )
        } catch (e: Exception) {
            console.error("Simulation initialization failed!")
            e.printStackTrace()
            throw e
        }

        try {
            while (!simulation.dead) {
                val result = simulation.grow()
                console.info(result.toString())
            }
        } catch (e: Exception) {
            console.error("Simulation failed at age ${simulation.age}!")
            e.printStackTrace()
            throw e
        }
    }

    @Test
    fun simulationMassiveTest() {
        repeat(100) {
            val simulation = try {
                Simulation(
                    AttributeMap().apply {
                        set(Attribute.BEAUTY, 10)
                        set(Attribute.IQ, 10)
                        set(Attribute.STRENGTH, 10)
                        set(Attribute.ECONOMIC, 10)

                        set(Attribute.HAPPINESS, 10)
                        set(Attribute.HEALTH, 10)
                        set(Attribute.FEMININITY, 0)
                    }
                )
            } catch (e: Exception) {
                console.error("$it: Simulation initialization failed!")
                e.printStackTrace()
                throw e
            }

            try {
                while (!simulation.dead) {
                    simulation.grow()
                }
            } catch (e: Exception) {
                console.error("$it: Simulation failed at age ${simulation.age}!")
                e.printStackTrace()
                throw e
            }
        }
    }
} 