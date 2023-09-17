package zopa_talk

import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.BigDecimal.ZERO
import java.time.LocalDateTime
import java.util.*

sealed interface OutStandingBalanceResult {
    object FailedToCreateOutStandingBalance : OutStandingBalanceResult
    data class SuccessfullyCreatedOutStandingBalance(val outStandingBalance: OutStandingBalance) :
        OutStandingBalanceResult
}

@JvmInline
value class OutStandingBalance private constructor(val value: BigDecimal) {
    //    factory creator method
//    allows to return different types according to passed value
    companion object {
        @JvmStatic
        fun of(value: BigDecimal): OutStandingBalanceResult {
            return if (value <= MAX_LOAN_AMOUNT && value >= ZERO) {
                OutStandingBalanceResult.SuccessfullyCreatedOutStandingBalance(OutStandingBalance(value))
            } else {
                OutStandingBalanceResult.FailedToCreateOutStandingBalance
            }
        }
    }
}

data class Loan(val loanId: LoanId, val outstandingBalance: OutStandingBalance)


sealed interface PaymentResult {
    object FailedToApplyPayment : PaymentResult
    data class SuccessfullyAppliedPayment(val loan: Loan) : PaymentResult
}

@JvmInline
value class PaymentDate(val value: LocalDateTime)

fun Loan.applyPayment(payment: Payment, paymentDate: PaymentDate): PaymentResult {
    return when (val outStandingBalanceResult = OutStandingBalance.of(outstandingBalance.value - payment.value)) {
        is OutStandingBalanceResult.SuccessfullyCreatedOutStandingBalance -> PaymentResult.SuccessfullyAppliedPayment(
            this
        )

        is OutStandingBalanceResult.FailedToCreateOutStandingBalance -> PaymentResult.FailedToApplyPayment
    }
}

//could be better to use something like the Arrow library, which provides either types
//sealed class Either<out A, out B>
//uses type parameters to provide a generic version of the union patter we are using here
//we no longer need to specify a sealed type for each function
//example -> https://medium.com/android-news/real-world-functional-programming-with-kotlin-arrow-b5a98e72f5e3

fun main() {
    when (val outStandingBalanceResult = OutStandingBalance.of(100.toBigDecimal())) {
        is OutStandingBalanceResult.SuccessfullyCreatedOutStandingBalance -> println(outStandingBalanceResult.outStandingBalance)
        is OutStandingBalanceResult.FailedToCreateOutStandingBalance -> println("Failed to create out standing balance")
    }
    when (val outStandingBalanceResult = OutStandingBalance.of(MAX_LOAN_AMOUNT + ONE)) {
        is OutStandingBalanceResult.SuccessfullyCreatedOutStandingBalance -> println(outStandingBalanceResult.outStandingBalance)
        is OutStandingBalanceResult.FailedToCreateOutStandingBalance -> println("Failed to create out standing balance")
    }

    val loan1 = Loan(LoanId(UUID.randomUUID()), OutStandingBalance.of(100.toBigDecimal()).let {
        when (it) {
            is OutStandingBalanceResult.SuccessfullyCreatedOutStandingBalance -> it.outStandingBalance
            is OutStandingBalanceResult.FailedToCreateOutStandingBalance -> throw IllegalStateException("Failed to create out standing balance")
        }
    })

    println(loan1)
}