import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InfixSampleKtTest {
    @Test
    fun shouldReturnPredicateTrueForEqual() {
        assertEquals("predicate true", "5" eq "5")
    }

    @Test
    fun shouldReturnEqNotEqual() {
        assertEquals("5 != 6", "5" eq "6")
    }

    @Test
    fun shouldReturnPredicateTrueForNotEqual() {
        assertEquals("predicate true", "5" neq "6")
    }

    @Test
    fun shouldReturnNotEqualEqual() {
        assertEquals("5 == 5", "5" neq "5")
    }

}