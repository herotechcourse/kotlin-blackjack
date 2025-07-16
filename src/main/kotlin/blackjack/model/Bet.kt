package blackjack.model

@JvmInline
value class Bet(val amount: Int) {
    init {
        require(amount > 0) { "Bet must be positive" }
    }
}
