package blackjack.model

@JvmInline
value class Chips(val value: Double) {
    fun lose(): Chips = -this
    fun blackjack(): Chips = this * BLACKJACK_MULTIPLIER

    operator fun unaryMinus(): Chips = Chips(-value)
    operator fun times(multiplier: Double): Chips = Chips(value * multiplier)
    operator fun plus(other: Chips): Chips = Chips(value + other.value)

    override fun toString(): String = "${value.toInt()}"

    companion object {
        private const val BLACKJACK_MULTIPLIER = 1.5
        private const val ZERO = 0.0
        fun zero(): Chips = Chips(ZERO)
    }
}