package blackjack.model

@JvmInline
value class Bet(val amount: Int) {
    init {
        require(amount > 0 && amount % 1000 == 0) {
            "Bet must be a positive number and a multiple of 1000."
        }
    }

    companion object {
        fun from(amount: Int): Bet = Bet(amount)
    }
}
