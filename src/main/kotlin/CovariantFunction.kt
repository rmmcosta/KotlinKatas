import java.time.LocalDate

fun main() {

}

open class Person(name: String, birthDate: LocalDate)
data class Engineer(val name: String, val birthDate: LocalDate, val speciality: String) : Person(name, birthDate)

/*
In Kotlin, we can declare a type parameter as either "invariant," "covariant," or "contravariant":

Invariant: When you don't specify any variance annotation, it means the type parameter is invariant.
It means you can't use a type Array<A> as a subtype or supertype of Array<B> even if A is a subtype or supertype of B.
In other words, you cannot substitute Array<A> for Array<B> or vice versa.

Covariant: If you declare a type parameter as covariant, denoted by out, it means you can use a subtype as a replacement for the supertype.
For example, if you have a List<out A>, you can use a List<B> as a subtype of List<A> if B is a subtype of A.

Contravariant: If you declare a type parameter as contravariant, denoted by in, it means you can use a supertype as a replacement for the subtype.
For example, if you have a Comparator<in A>, you can use a Comparator<B> as a subtype of Comparator<A> if B is a supertype of A.
 */