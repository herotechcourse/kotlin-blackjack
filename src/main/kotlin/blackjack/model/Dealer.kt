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

    fun isShowingAllCards() = showAllCards

    fun getDealtCards() = hand.dealtCards.toList()

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

    companion object {
        const val DEALER_STAND = 16
    }
}
