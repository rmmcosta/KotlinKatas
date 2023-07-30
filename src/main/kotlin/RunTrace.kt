fun main() {
    trace("xpto")
    trace("abc")
    trace(45)
    trace eq """
        xpto
        abc
        45
    """
}