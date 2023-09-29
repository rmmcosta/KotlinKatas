package Coursera.LittleLemonApp

class RecipeBook {
    private val dishes = listOf(
        Dish("Chicken Soup", setOf("Chicken", "Water", "Salt")),
        Dish("Quinoa Salad", setOf("Quinoa", "Tomato", "Salt", "Olive Oil")),
        Dish("Thai Curry", setOf("Chicken", "Salt", "Curry Paste", "Coconut Milk")),
        Dish("Jambalaya", setOf("Chicken", "Salt", "Rice", "Tomato", "Onion", "Pepper")),
        Dish("Pad Thai", setOf("Chicken", "Salt", "Rice Noodles", "Egg", "Peanuts", "Soy Sauce")),
        Dish("Lasagna", setOf("Chicken", "Salt", "Pasta", "Tomato", "Onion", "Pepper", "Cheese")),
    )

    fun allIngredients(): Set<String> =
        dishes.flatMap { it.ingredients }.sorted().toSet()

    fun allIngredients2(): Set<String> =
        dishes.fold<Dish, MutableSet<String>>(mutableSetOf()) { acc, dish ->
            acc.apply {
                addAll(dish.ingredients)
            }
        }.sorted().toSet()
}

data class Dish(
    val name: String,
    val ingredients: Set<String>,
)

class Balance(private val balance: Double) {
    fun balanceAfterOrders(orders: Collection<OrderItem>): Double = orders.fold(balance) { acc, orderItem ->
        acc + orderItem.price
    }

    fun balanceAfterOrders2(orders: Collection<OrderItem>): Double = balance + orders.sumOf { it.price }
}

fun main() {
    val recipeBook = RecipeBook()
    println(recipeBook.allIngredients())
    println(recipeBook.allIngredients2())

    val orders = listOf(
        OrderItem("Chicken Soup", 5.0),
        OrderItem("Quinoa Salad", 7.0),
        OrderItem("Thai Curry", 10.0),
        OrderItem("Jambalaya", 12.0),
        OrderItem("Pad Thai", 8.0),
    )

    val balance = Balance(100.0)
    println(balance.balanceAfterOrders(orders))
    println(balance.balanceAfterOrders2(orders))
}
