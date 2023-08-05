interface Graphic {
    val exporter: Exporter2
    fun draw()
    fun export() = exporter.export()
    fun accept(visitor: Visitor)
}

interface Visitor {
    fun visitShape(shape: Shape)
    fun visitDot(dot: Dot)
    fun visitCircle(circle: Circle)
    fun visitRectangle(rectangle: Rectangle)
    fun visitCompoundGraphic(compoundGraphic: CompoundGraphic)
}

open class Shape(val id: String, override val exporter: Exporter2) : Graphic {
    override fun draw() {
        println("Draw shape $id")
    }

    override fun accept(visitor: Visitor) {
        visitor.visitShape(this)
    }
}

open class Dot(id: String, protected val x: Int, protected val y: Int, exporter: Exporter2) : Shape(id, exporter) {
    override fun draw() {
        println("Draw dot $id in the point ($x,$y)")
    }

    override fun accept(visitor: Visitor) {
        visitor.visitDot(this)
    }
}

class Circle(id: String, x: Int, y: Int, private val radius: Double, exporter: Exporter2) : Dot(id, x, y, exporter) {
    override fun draw() {
        println("Draw circle $id in the point ($x,$y) with a radius of $radius")
    }

    override fun accept(visitor: Visitor) {
        visitor.visitCircle(this)
    }
}

class Rectangle(id: String, private val width: Int, private val height: Int, exporter: Exporter2) :
    Shape(id, exporter) {
    override fun draw() {
        println("Draw rectangle $id of dimensions $width x $height")
    }

    override fun accept(visitor: Visitor) {
        visitor.visitRectangle(this)
    }
}

class CompoundGraphic(val children: List<Shape>, override val exporter: Exporter2) : Graphic {
    override fun draw() {
        children.forEach { it.draw() }
    }

    override fun accept(visitor: Visitor) {
        visitor.visitCompoundGraphic(this)
    }
}

class XMLExportVisitor : Visitor {
    override fun visitShape(shape: Shape) {
        println("<shape><name>${shape.id}</name></shape>")
    }

    override fun visitDot(dot: Dot) {
        println("<dot><name>${dot.id}</name></dot>")
    }

    override fun visitCircle(circle: Circle) {
        println("<circle><name>${circle.id}</name></circle>")
    }

    override fun visitRectangle(rectangle: Rectangle) {
        println("<rectangle><name>${rectangle.id}</name></rectangle>")
    }

    override fun visitCompoundGraphic(compoundGraphic: CompoundGraphic) {
        val childXml = StringBuilder()
        compoundGraphic.children.forEach { childXml.append("<child>${it.id}</child>") }
        println("<compoundGraphic>$childXml</compoundGraphic>")
    }

}

class Exporter {
    fun export(s: Shape) = println("Exporting Shape")
    fun export(s: Dot) = println("Exporting Dot")
    fun export(s: Circle) = println("Exporting Circle")
    fun export(s: Rectangle) = println("Exporting Rectangle")
    fun export(s: CompoundGraphic) = println("Exporting CompoundGraphic")
}

interface Exporter2 {
    fun export()
}

class ShapeExporter : Exporter2 {
    override fun export() = println("Exporting Shape")
}

class DotExporter : Exporter2 {
    override fun export() = println("Exporting Dot")
}

class CircleExporter : Exporter2 {
    override fun export() = println("Exporting Circle")
}

class RectangleExporter : Exporter2 {
    override fun export() = println("Exporting Rectangle")
}

class CompoundGraphicExporter : Exporter2 {
    override fun export() = println("Exporting CompoundGraphic")
}

class App {
    fun export(shape: Shape) = Exporter().export(shape)
}

class App2 {
    fun export(shape: Shape) = shape.export()
}

class App3(private val allShapes: List<Graphic>) {
    fun export() = allShapes.forEach { it.accept(XMLExportVisitor()) }
}

fun main() {
    val circle = Circle("circle 1", 5, 5, 10.0, CircleExporter())
    val dot = Dot("dot 1", 2, 3, DotExporter())
    val rectangle = Rectangle("rectangle 1", 13, 8, RectangleExporter())
    App().export(circle)
    App2().export(circle)
    val compoundGraphic = CompoundGraphic(
        listOf(
            dot,
            rectangle
        ), CompoundGraphicExporter()
    )
    compoundGraphic.draw()
    App3(listOf(circle, dot, rectangle, compoundGraphic)).export()
}

