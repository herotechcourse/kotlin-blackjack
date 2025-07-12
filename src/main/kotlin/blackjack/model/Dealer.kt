package blackjack.model

class Dealer(name: String = "Dealer", private val deck: Deck = Deck.generateADeck()) :
    Participant(name, DealerResultTracker()) {
    private var showAllCards = false

    fun shuffleDeck() = deck.shuffle()
    fun dealCard(): Card = deck.drawCard()
    fun shouldNotStand(): Boolean = hand.getScore() <= DEALER_STAND
    fun showAllCards() { showAllCards = true }

    fun setResultFor(player: Player) {
        val result = evaluateResult(player)
        player.recordResult(result)
        recordResult(inverse(result))
    }

    override fun resultSummary(): String {
        return "$name: $resultTracker"
    }

    override fun toString(): String =
        when {
            showAllCards -> "$name's cards: $hand"
            hand.cards.isEmpty() -> "$name has no cards yet."
            else -> "$name: ${hand.cards[0]}"
        }

    private fun evaluateResult(player: Player): Result = when {
            player.isBusts() -> Result.LOSE
            this.isBusts() -> Result.WIN
            player.hasBlackJack() && !this.hasBlackJack() -> Result.WIN
            player.getScore() > this.getScore() -> Result.WIN
            player.getScore() < this.getScore() -> Result.LOSE
            else -> Result.TIE
        }

    private fun inverse(result: Result): Result = when (result) {
        Result.WIN -> Result.LOSE
        Result.LOSE -> Result.WIN
        Result.TIE -> Result.TIE
    }


    companion object {
        const val DEALER_STAND = 16
    }
}
