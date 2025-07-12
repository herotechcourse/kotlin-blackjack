package blackjack.model

class Dealer(name: String = "Dealer") : Participant(name) {
    val deck = Deck()
    private var showAllCards = false

    fun dealCard(): Card {
        return deck.drawCard()
    }

    fun shouldNotStand(): Boolean = hand.getScore() <= DEALER_STAND

    fun showAllCards() {
        showAllCards = true
    }

    fun setResultFor(player: Player) {
        val playerScore = player.getScore()
        val dealerScore = this.getScore()
        when {
            player.isBusts() -> {
                player.setLose()
                this.setWin()
            }

            this.isBusts() -> {
                player.setWin()
                this.setLose()
            }

            player.hasBlackJack() && !this.hasBlackJack() -> {
                player.setWin()
                this.setLose()
            }

            playerScore == dealerScore -> {
                player.setTie()
                this.setTie()
            }

            playerScore < dealerScore -> {
                player.setLose()
                this.setWin()
            }

            else -> {
                player.setWin()
                this.setLose()
            }
        }
    }

    override fun toString(): String =
        when {
            showAllCards -> "$name's cards: $hand"
            hand.dealtCards.isEmpty() -> "$name has no cards yet."
            else -> "$name: ${hand.dealtCards[0]}"
        }

    companion object {
        const val DEALER_STAND = 16
    }
}
