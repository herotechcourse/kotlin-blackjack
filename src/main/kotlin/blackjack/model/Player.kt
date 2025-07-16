package blackjack.model

class Player(
    val name: String,
) : Participant() {
    val wallet = PlayerWallet()

    fun placeBet(amount: Int) {
        wallet.placeBet(amount)
    }

    fun receiveEarningsOrLoses(amount: Int) {
        wallet.updateBalance(amount)
    }
}
