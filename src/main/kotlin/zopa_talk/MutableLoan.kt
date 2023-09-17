package zopa_talk

import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.util.*

data class MutableLoan(val id: UUID, var outstandingBalance: BigDecimal) {
    fun applyPayment(payment: BigDecimal) {
        if (payment < ZERO) {
            throw IllegalArgumentException("Payment cannot be negative")
        }
        if (payment > outstandingBalance) {
            throw  IllegalArgumentException("Payment cannot be greater than outstanding balance")
        }
        outstandingBalance-=payment
    }
}