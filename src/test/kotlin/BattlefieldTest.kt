import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BattlefieldTest {

    @Test
    fun shouldBeAbleToPlaceShipsUntilTheFieldIsFull() {
        val battlefield = Battlefield()
        val freeCells = battlefield.countFreeCells()
        for (i in 1..freeCells) {
            assertNotNull(battlefield.placeShip(Ship(i)))
        }
        assertEquals(0, battlefield.countFreeCells())
    }

    @Test
    fun shouldReturnNullIfFieldIsFull() {
        val battlefield = Battlefield(0, 0)
        assertEquals(0, battlefield.countFreeCells())
        assertNull(battlefield.placeShip(Ship(1)))
    }

    @Test
    fun shouldBeAbleToGetTheShipPositionAfterPlacingIt() {
        val battlefield = Battlefield(5, 5)
        val givenCell = battlefield.placeShip(Ship(1))
        givenCell!!
        assertTrue(givenCell.x >= 0 && givenCell.y >= 0)
        assertTrue(givenCell.x < 5 && givenCell.y < 5)
    }
}