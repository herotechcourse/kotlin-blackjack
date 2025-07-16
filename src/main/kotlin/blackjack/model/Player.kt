package blackjack.model

class Player(
    val name: String,
) : Participant() {
    var bet: Int = 0
        private set

    fun setBet(bet: Int) {
        require(bet > 0) { "Bet must be a positive number." }
        this.bet = bet
    }
}
