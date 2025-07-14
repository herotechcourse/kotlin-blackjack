package blackjack.model

class Dealer(name: String = "Dealer") : Participant(name) {
    val deck = Deck()
    private var showAllCards = false

    fun dealCard(): Card {
        return deck.drawCard()
    }

    fun shouldNotStand(): Boolean = !isFinished() && getScore() <= DEALER_STAND

    fun showAllCards() {
        showAllCards = true
    }

    fun isShowingAllCards() = showAllCards

    fun getDealtCards() = handState.hand.cards.toList()

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
