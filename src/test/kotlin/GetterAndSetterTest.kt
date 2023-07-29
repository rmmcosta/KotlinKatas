import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GetterAndSetterTest {
    @Test
    fun shouldBeAbleToGetAndSet() {
        val getAndSet = GetterAndSetter()
        assertEquals("", getAndSet.getAndSet)
        getAndSet.getAndSet = "Xpto"
        assertEquals("Xpto", getAndSet.getAndSet)
    }
}