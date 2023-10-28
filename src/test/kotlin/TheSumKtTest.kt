import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TheSumKtTest {

    @Test
    fun `given two positive numbers, when the sum function is called for both of them, then they are summed`() {
        val num1 = 1
        val num2 = 2
        val expectedSum = 3
        assertEquals(expectedSum, sumTwoNumbers(num1, num2))
    }
}