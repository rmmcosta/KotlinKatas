package hyperskill

class Triangle : Shape {
    override val angles: Int = 3
    override val scale: Double = 1.0

    override fun draw() {
        /* ... */
    }
}

class Rectangle : Shape {
    override val angles: Int = 4
    override val scale: Double = 1.0

    override fun draw() {
        /* ... */
    }
}

class Hexagon : Shape {
    override val angles: Int = 6
    override val scale: Double = 1.0

    override fun draw() {
        /* ... */
    }
}

interface Shape {
    val angles: Int
    val scale: Double
    fun draw()
}