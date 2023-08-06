val isEven = { it: Int -> it % 2 == 0 }
val isOdd = { it: Int -> it % 2 != 0 }
val isEven2: (Int) -> Boolean = { it % 2 == 0 }
val isOdd2: (Int) -> Boolean = { it % 2 != 0 }

fun main() {
    2.assertTrue("Is 2 even", isEven)
    2.assertTrue("Is 2 even2", isEven2)
    2.assertTrue("Is 2 odd", isOdd)
    2.assertTrue("Is 2 odd2", isOdd2)
    3.assertTrue("Is 3 even", isEven)
    3.assertTrue("Is 3 even2", isEven2)
    3.assertTrue("Is 3 odd", isOdd)
    3.assertTrue("Is 3 odd2", isOdd2)
    17.assertTrue("Is 17 even", isEven)
    17.assertTrue("Is 17 even2", isEven2)
    17.assertTrue("Is 17 odd", isOdd)
    17.assertTrue("Is 17 odd2", isOdd2)
    24.assertTrue("Is 24 even", isEven)
    24.assertTrue("Is 24 even2", isEven2)
    24.assertTrue("Is 24 odd", isOdd)
    24.assertTrue("Is 24 odd2", isOdd2)
}

fun Int.assertTrue(message: String, predicate: (Int) -> Boolean) {
    println("$message eq ${predicate(this)}")
}