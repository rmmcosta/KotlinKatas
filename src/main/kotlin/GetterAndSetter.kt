class GetterAndSetter {
    var getAndSet: String = ""
        get() {
            println("I can be get")
            return field
        }
        set(value) {
            println("I can be set")
            field = value
        }
}