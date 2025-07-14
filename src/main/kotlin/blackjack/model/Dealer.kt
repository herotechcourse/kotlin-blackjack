package blackjack.model

class Dealer(name: String = "Dealer") : Participant(name) {
    val deck = Deck()
    private var showAllCards = false
    var finalEarnings: Double = 0.0

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

        val playerRawProfit = player.handState.profit(player.bettingAmount)
        
        when {
            player.isBusts() -> {
                player.setLose()
                this.setWin()
                player.finalEarnings = -player.bettingAmount.toDouble()
                this.finalEarnings += player.bettingAmount
            }

            this.isBusts() -> {
                player.setWin()
                this.setLose()

                player.finalEarnings = playerRawProfit - player.bettingAmount
                finalEarnings -= playerRawProfit
            }

            player.hasBlackJack() && !this.hasBlackJack() -> {
                player.setWin()
                this.setLose()
                player.finalEarnings = playerRawProfit - player.bettingAmount
                finalEarnings -= playerRawProfit
            }

            playerScore == dealerScore -> {
                player.setTie()
                this.setTie()
                player.finalEarnings = 0.0
            }

            playerScore < dealerScore -> {
                player.setLose()
                this.setWin()
                player.finalEarnings = -player.bettingAmount.toDouble()
                this.finalEarnings += player.bettingAmount
            }

            else -> {
                player.setWin()
                this.setLose()
                player.finalEarnings = player.bettingAmount.toDouble()
                this.finalEarnings -= playerRawProfit
            }
        }
    }

    companion object {
        const val DEALER_STAND = 16
    }
}
