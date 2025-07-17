package blackjack.model

class Player(
    val name: String,
) : Participant() {
    val wallet = PlayerWallet()
    var state: PlayerState = Playing

    override fun drawCard(card: Card) {
        super.drawCard(card)

        state = when {
            cardsInHand.hasBlackJack() -> Blackjack
            cardsInHand.isBustHand() -> Busted
            else -> Playing
        }
    }

    fun canHit(): Boolean = state.canHit()

    fun placeBet(amount: Int) {
        wallet.placeBet(amount)
    }

    fun receiveEarningsOrLoses(amount: Int) {
        wallet.updateBalance(amount)
    }
}
