import me.xiaro.mtfsim.Simulation
import kotlin.test.Test

class TestClient {
    @Test
    fun simulationTest() {
        val simulation = try {
            Simulation()
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
                Simulation()
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