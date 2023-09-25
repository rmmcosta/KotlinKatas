package Coursera.LittleLemonApp


class Dependency {
    fun doSomethingElse(doThisInTheDependency: () -> Unit) {
        doThisInTheDependency()
    }

}