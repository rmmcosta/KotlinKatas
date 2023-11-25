import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class CalculatorTest {

    private val calculator = Calculator

    @Test
    fun add() {
        assertEquals(2, calculator.add(1, 1))
    }

    @Test
    fun subtract() {
        assertEquals(0, calculator.subtract(1, 1))
    }

    @Test
    fun multiply() {
        assertEquals(30, calculator.multiply(5, 6))
    }

    @Test
    fun divide() {
        assertEquals(5, calculator.divide(30, 6))
    }

    @Test
    fun divideByZero() {
        assertThrows(IllegalArgumentException::class.java) {
            calculator.divide(1, 0)
        }
    }

    @Test
    fun isEven() {
        assertTrue(calculator.isEven(2))
        assertFalse(calculator.isEven(3))
    }
}