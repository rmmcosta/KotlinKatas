package Coursera.LittleLemonApp

class TaxCalculator {
    companion object {
        private const val TAX_AMOUNT_PERCENTAGE = 15.0
        fun calculateOrderTaxAmount(orderItems: List<OrderItem>): Double =
            orderItems.sumOf { it.price } * TAX_AMOUNT_PERCENTAGE / 100.0
    }
}


