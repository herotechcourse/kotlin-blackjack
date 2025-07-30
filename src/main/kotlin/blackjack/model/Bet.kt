package blackjack.model

@JvmInline
value class Bet private constructor(val amount: Int) {
    companion object {
        private const val MIN_BET = 5
        private const val MAX_BET = 100

        fun of(amount: Int): Bet {
            require(amount in MIN_BET..MAX_BET) {
                "Bet has to be in range of $MIN_BET and $MAX_BET ($amount)."
            }
            return Bet(amount)
        }

        val UNPLACED = Bet(0)
    }
}
