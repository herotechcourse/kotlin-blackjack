package blackjack.model

class Player(
    val name: String,
) : Participant() {
    var bet: Int = 0
        private set

    fun setBet(bet: Int) {
        this.bet = bet
    }
}
