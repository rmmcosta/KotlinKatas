package hyperskill

// Do not change the code below!

interface IMovable {
    val externalEffect: Boolean
    val selfEffect: Boolean
}

interface IMassive {
    val mass: Int
    var massMultiplier: Int
}

interface IControllable {
    var currentlyControlled: Boolean
    var controllers: Int
}

interface ISimulated : IMovable, IMassive {
    var isSimulating: Boolean
}

interface Entity : ISimulated {
    val entityId: Int
}

// Do not change the code above!

class PlayerAsMembers(
    //Your code here
) : Entity, IControllable {
    override val externalEffect: Boolean
        get() = TODO("Not yet implemented")
    override val selfEffect: Boolean
        get() = TODO("Not yet implemented")
    override val mass: Int
        get() = TODO("Not yet implemented")
    override var massMultiplier: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var currentlyControlled: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override var controllers: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var isSimulating: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override val entityId: Int
        get() = TODO("Not yet implemented")
}

class PlayerAsConstructorParameters(
    override val externalEffect: Boolean,
    override val selfEffect: Boolean,
    override val mass: Int,
    override var massMultiplier: Int,
    override var currentlyControlled: Boolean,
    override var controllers: Int,
    override var isSimulating: Boolean,
    override val entityId: Int
    //Your code here
) : Entity, IControllable {

}