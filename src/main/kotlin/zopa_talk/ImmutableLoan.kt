package zopa_talk

import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.util.UUID

//use algebraic data types for the loan and it's properties
//a type of types
//use value classes for each property

//here things are private but won't be in a real scenario

@JvmInline
value class LoanId(val id: UUID)

val MAX_LOAN_AMOUNT = 60_000.toBigDecimal()

@JvmInline
private value class OutstandingBalance(val value: BigDecimal) {
    init {
        require(value <= MAX_LOAN_AMOUNT) { "Outstanding balance cannot be greater than $MAX_LOAN_AMOUNT" }
        require(value >= ZERO) { "Outstanding balance cannot be negative" }
    }
}

@JvmInline
value class Payment(val value: BigDecimal) {
    init {
        require(value >= ZERO) { "Payment cannot be negative" }
    }
}

private data class ImmutableLoan(val loanId: LoanId, val outstandingBalance: OutstandingBalance)

private fun ImmutableLoan.applyPayment(payment: Payment): ImmutableLoan {
    val newBalance = OutstandingBalance(outstandingBalance.value - payment.value)//keep the same loan id
    return copy(outstandingBalance = newBalance)
}

fun main() {
    val loan = ImmutableLoan(LoanId(UUID.randomUUID()), OutstandingBalance(100.toBigDecimal()))
    val payment = Payment(50.toBigDecimal())
    val newLoan = loan.applyPayment(payment)
    println(newLoan)
}
