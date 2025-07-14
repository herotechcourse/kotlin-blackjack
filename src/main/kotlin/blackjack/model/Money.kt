package blackjack.model

@JvmInline
value class Money(val value: Double) {
    fun lose(): Money = -this
    fun blackjack(): Money = this * BLACKJACK_MULTIPLIER

    operator fun unaryMinus(): Money = Money(-value)
    operator fun times(multiplier: Double): Money = Money(value * multiplier)
    operator fun plus(other: Money): Money = Money(value + other.value)

    companion object {
        private const val BLACKJACK_MULTIPLIER = 1.5
        private const val ZERO = 0.0
        fun zero(): Money = Money(ZERO)
    }
}