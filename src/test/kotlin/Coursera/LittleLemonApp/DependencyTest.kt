package Coursera.LittleLemonApp

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DependencyTest {
    private val dependency = Dependency()
    @Test
    fun doSomething() {

        val doThisInTheDependency = { println("dependency got executed") }

        dependency.doSomethingElse(doThisInTheDependency)

    }
}

class Me() {
    companion object Companion {
        val myBrother = "Michael"
    }
}

fun main() {
    Me.Companion.myBrother

    listOf(1,2,34).forEach {  }
}