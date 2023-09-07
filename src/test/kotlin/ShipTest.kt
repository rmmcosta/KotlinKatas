import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ShipTest {
    @Test
    fun shouldBeAbleToLandIfFieldIsNotFull() {
        val ship1 = Ship(1)
        val battlefield = Battlefield(5, 5)
        assertTrue(ship1.land(battlefield))
    }

    @Test
    fun shouldReturnNotFlyingAndLandedIfIsLanded() {
        val ship1 = Ship(1)
        val battlefield = Battlefield(5, 5)
        assertTrue(ship1.land(battlefield))
        assertTrue(ship1.isLanded())
        assertFalse(ship1.isFlying())
    }

    @Test
    fun shouldReturnFlyingIfIsNotLanded() {
        val ship1 = Ship(1)
        assertTrue(ship1.isFlying())
    }

    @Test
    fun shouldReturnFlyingAfterLiftingOff() {
        val ship1 = Ship(1)
        val battlefield = Battlefield(5, 5)
        ship1.land(battlefield)
        ship1.liftOff()
        assertTrue(ship1.isFlying())
    }

    @Test
    fun shouldGetAValidPositionAfterLanding() {
        val ship1 = Ship(1)
        val battlefield = Battlefield(5, 5)
        ship1.land(battlefield)
        val (x, y) = ship1.getPosition()
        assertTrue(x in 0..4)
        assertTrue(y in 0..4)
    }
}