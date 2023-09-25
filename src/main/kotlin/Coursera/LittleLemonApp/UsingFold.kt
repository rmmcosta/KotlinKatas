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

fun main() {
    val recipeBook = RecipeBook()
    println(recipeBook.allIngredients())
    println(recipeBook.allIngredients2())
}
