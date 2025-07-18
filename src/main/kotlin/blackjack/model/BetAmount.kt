package blackjack.model

@JvmInline
value class Bet private constructor(val amount: Int) {
    companion object {
        fun from(amount: Int): Bet {
            require(amount > 0 && amount % 1000 == 0) {
                "Bet must be a positive number and a multiple of 1000."
            }
            return Bet(amount)
        }
    }
}
