package blackjack.model

data class Bet(val amount: Int) {
    init {
        require(amount > 0) { "Bet must be positive" }
    }
}
