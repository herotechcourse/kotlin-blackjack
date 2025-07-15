package blackjack.model

@JvmInline
value class Chips(val value: Double) {
    fun lose(): Chips = -this

    fun blackjack(): Chips = this * BLACKJACK_MULTIPLIER

    operator fun unaryMinus(): Chips = Chips(-value)

    operator fun times(multiplier: Double): Chips = Chips(value * multiplier)

    operator fun plus(other: Chips): Chips = Chips(value + other.value)

    operator fun minus(other: Chips): Chips = Chips(value - other.value)

    companion object {
        private const val BLACKJACK_MULTIPLIER = 1.5
        private const val ZERO = 0.0
        val zero: Chips
            get() = Chips(ZERO)
    }
}
